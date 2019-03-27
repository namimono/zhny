package org.rcisoft.business.energy.overview.service.impl;

import org.rcisoft.business.energy.overview.dao.OverviewDao;
import org.rcisoft.business.energy.overview.entity.*;
import org.rcisoft.business.energy.overview.service.OverviewService;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.dao.BusTypeFirstDao;
import org.rcisoft.dao.EnergyPriceDao;
import org.rcisoft.dao.EnergyStatisticsDao;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by JiChao on 2019/3/15.
 */
@Service
public class OverviewServiceImpl implements OverviewService {

    @Autowired
    EnergyPriceDao energyPriceDao;
    @Autowired
    EnergyStatisticsDao energyStatisticsDao;
    @Autowired
    BusTypeFirstDao busTypeFirstDao;
    @Autowired
    OverviewDao overviewDao;
    @Autowired
    BusDeviceDao busDeviceDao;

    @Override
    public PricePerHour queryPricePerHour(String projectId) {
        // 查询条件
        OverviewParam o = new OverviewParam(null, null, null, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), projectId);
        PricePerHour pricePerHour = new PricePerHour();
        List<EnergyPrice> list = energyPriceDao.queryPricePerHour(o);
        list.forEach(energyPrice -> {
            BigDecimal price = energyPrice.getPrice();
            switch (energyPrice.getEnergyTypeId()) {
                case 1: // 水
                    pricePerHour.setWaterPrice(price);
                    break;
                case 2: // 电
                    pricePerHour.setElecPrice(price);
                    break;
                case 3: // 气
                    pricePerHour.setGasPrice(price);
                    break;
            }
        });
        return pricePerHour;
    }

    @Override
    public PriceAndRank queryPriceAndRank(String projectId) {
        // 返回值
        PriceAndRank result = new PriceAndRank();
        // 时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // 查询条件
        OverviewParam o = new OverviewParam(year, month, day, hour, projectId);
        // 当日
        EnergyStatistics dayEnergy = energyStatisticsDao.queryEnergyForDay(o);
        if (dayEnergy != null) {
            BigDecimal dayWater = dayEnergy.getMoneyWater();
            BigDecimal dayElec = dayEnergy.getMoneyElec();
            BigDecimal dayGas = dayEnergy.getMoneyGas();
            result.setDayWater(dayWater);
            result.setDayElec(dayElec);
            result.setDayGas(dayGas);
            if (dayWater != null && dayElec != null && dayGas != null) {
                result.setDayTotal(dayWater.add(dayElec).add(dayGas));
            }
        }
        // 当月
        EnergyStatistics monEnergy = energyStatisticsDao.queryEnergyForMonth(o);
        if (monEnergy != null) {
            BigDecimal monWater = monEnergy.getMoneyWater();
            BigDecimal monElec = monEnergy.getMoneyElec();
            BigDecimal monGas = monEnergy.getMoneyGas();
            result.setMonWater(monWater);
            result.setMonElec(monElec);
            result.setMonGas(monGas);
            if (monWater != null && monElec != null && monGas != null) {
                result.setMonTotal(monWater.add(monElec).add(monGas));
            }
        }
        // 排行
        List<EnergyStatistics> list = energyStatisticsDao.queryRank(o);
        // 对list进行排序，取得今天在list中的位置，作为排名的顺序
        Collections.sort(list, (e1, e2) -> {
            BigDecimal money1 = e1.getMoneyWater().add(e1.getMoneyElec()).add(e1.getMoneyGas());
            BigDecimal money2 = e2.getMoneyWater().add(e2.getMoneyElec()).add(e2.getMoneyGas());
            BigDecimal diff = money1.subtract(money2);
            if (diff.compareTo(new BigDecimal(0)) >= 0) {
                return -1;
            } else {
                return 1;
            }
        });
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTimeDay() == day) {
                result.setRank(i + 1);
                break;
            }
        }
        return result;
    }

    @Override
    public PriceForDay queryPriceForDay(String projectId, Integer energyType) {
        // 返回值
        PriceForDay result = new PriceForDay();
        Integer[] dayArray = {0, -1};
        for (Integer dayTime : dayArray) {
            // 取得时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, dayTime);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            // 查询条件
            OverviewParam o = new OverviewParam(year, month, day, null, projectId);
            List<EnergyStatistics> list = energyStatisticsDao.queryPriceForDay(o);
            // 初始化list
            List<BigDecimal> resultList = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                resultList.add(new BigDecimal(0));
            }
            // 放入数据
            for (int i = 0; i < list.size(); i++) {
                EnergyStatistics energyStatistics = list.get(i);
                // 取得小时
                Integer hour = energyStatistics.getTimeHour();
                BigDecimal resultPrice = new BigDecimal(0);
                // 判断水电气
                switch (energyType) {
                    case 1:// 水
                        resultPrice = resultPrice.add(energyStatistics.getMoneyWater());
                        break;
                    case 2:// 电
                        resultPrice = resultPrice.add(energyStatistics.getMoneyElec());
                        break;
                    case 3:// 气
                        resultPrice = resultPrice.add(energyStatistics.getMoneyGas());
                        break;
                    default:// 总和
                        resultPrice = resultPrice.add(energyStatistics.getMoneyWater())
                                                 .add(energyStatistics.getMoneyElec())
                                                 .add(energyStatistics.getMoneyGas());
                        break;
                }
                // 根据小时放入值
                resultList.set(hour, resultPrice);
            }
            // 判断是今日数据还是昨日数据
            if (dayTime == 0) {
                result.setListToday(resultList);
            } else if (dayTime == -1) {
                result.setListYestoday(resultList);
            }
        }
        return result;
    }

    @Override
    public EnergySplitResult queryEnergySplit(String projectId, Integer energyType) {
        // 返回值
        EnergySplitResult result = new EnergySplitResult();
        // 时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 查询条件
        OverviewParam o = new OverviewParam(year, month, day, null, projectId);
        // 查询项目下的设备
        List<BusDevice> deviceList = busDeviceDao.queryDeviceByProjectId(projectId);
        // 查询设备的能耗
        String column = "";
        switch (energyType) {
            case 1:
                column = "energy_water";
                break;
            case 2:
                column = "energy_elec";
                break;
            case 3:
                column = "energy_gas";
                break;
        }
        List<EnergyStatisticsSplit> energyList = overviewDao.queryEnergySplit(o, column);
        // 循环设备，循环能耗，将对应的数据添加至返回结果集
        for (BusDevice busDevice : deviceList) {
            String name = busDevice.getName();
            // 放入设备标题
            result.getTitle().add(name);
            for (EnergyStatisticsSplit energyStatisticsSplit : energyList) {
                // 如果设备id一致，在返回值新增一条记录
                if (energyStatisticsSplit.getDeviceId().equals(busDevice.getId())) {
                    EnergySplit energySplit = new EnergySplit(name, energyStatisticsSplit.getEnergy(), "0%");
                    result.getList().add(energySplit);
                    break;
                }
            }
        }
        // 循环能耗结果集，得到能耗总值
        BigDecimal totalEnergy = new BigDecimal(0);
        for (EnergyStatisticsSplit energyStatisticsSplit : energyList) {
            totalEnergy = totalEnergy.add(energyStatisticsSplit.getEnergy());
        }
        // 循环结果集，计算百分比
        if (totalEnergy.compareTo(new BigDecimal(0)) != 0) {
            for (EnergySplit energySplit : result.getList()) {
                BigDecimal value = energySplit.getValue();
                BigDecimal percent = value.multiply(new BigDecimal(100)).divide(totalEnergy, 2, BigDecimal.ROUND_HALF_UP);
                energySplit.setPercent(percent.toString() + "%");
            }
        }
        return result;
    }

}
