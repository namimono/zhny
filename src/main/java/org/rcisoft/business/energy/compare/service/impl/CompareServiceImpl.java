package org.rcisoft.business.energy.compare.service.impl;

import org.rcisoft.business.energy.compare.dao.CompareDao;
import org.rcisoft.business.energy.compare.entity.*;
import org.rcisoft.business.energy.compare.service.CompareService;
import org.rcisoft.dao.EnergyStandardDao;
import org.rcisoft.dao.EnergyStatisticsDao;
import org.rcisoft.entity.EnergyStandard;
import org.rcisoft.entity.EnergyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by JiChao on 2019/3/20.
 */
@Service
public class CompareServiceImpl implements CompareService {

    @Autowired
    EnergyStatisticsDao energyStatisticsDao;
    @Autowired
    EnergyStandardDao energyStandardDao;
    @Autowired
    CompareDao compareDao;

    @Override
    public EnergyDayAndMon queryEnergyDayAndMon(String projectId) {
        // 日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 参数
        CompareParam c = new CompareParam(year, month, null, projectId);
        // 查询本月
        EnergyStatistics dayEnergy = energyStatisticsDao.queryEnergyDayAndMon(c);
        c.setDay(day);
        // 查询当日
        EnergyStatistics monEnergy = energyStatisticsDao.queryEnergyDayAndMon(c);
        // 返回值
        EnergyDayAndMon result = new EnergyDayAndMon();
        if (dayEnergy != null) {
            result.setDayWater(dayEnergy.getEnergyWater());
            result.setDayElec(dayEnergy.getEnergyElec());
            result.setDayGas(dayEnergy.getEnergyGas());
        }
        if (monEnergy != null) {
            result.setMonWater(monEnergy.getEnergyWater());
            result.setMonElec(monEnergy.getEnergyElec());
            result.setMonGas(monEnergy.getEnergyGas());
        }
        return result;
    }

    @Override
    public EnergyStandardResult queryEnergyStandard(String projectId) {
        // 今年颜色
        String colorThisYear = "#8a87c2";
        // 去年颜色
        String colorLastYear = "#f2033b";
        // 查询标准用量
        List<EnergyStandard> standardList = energyStandardDao.queryEnergyStandard(projectId);
        // 查询今年水电气用量
        int year = Calendar.getInstance().get(Calendar.YEAR);
        EnergyAndCount energyThisYear = compareDao.queryEnergyAndCount(projectId, year);
        // 查询去年水电气用量
        EnergyAndCount energyLastYear = compareDao.queryEnergyAndCount(projectId, year - 1);
        // 将结果放入最后的返回值中
        EnergyStandardResult result = new EnergyStandardResult();
        // 先放入标准值
        standardList.forEach(energyStandard -> {
            result.getStandardSuggest().add(energyStandard.getSuggestStandard());
            result.getStandardIndustry().add(energyStandard.getIndustryStandard());
            result.getStandardCountry().add(energyStandard.getCountryStandard());
        });
        // 放入实际值
        // 今年
        this.setValueAndColor(energyThisYear, result.getStandardSuggest(), result.getStandardIndustry(), result.getStandardCountry(), result.getColorThisYear(), result.getEnergyThisYear(), colorThisYear);
        // 去年
        this.setValueAndColor(energyLastYear, result.getStandardSuggest(), result.getStandardIndustry(), result.getStandardCountry(), result.getColorLastYear(), result.getEnergyLastYear(), colorLastYear);
        return result;
    }

    @Override
    public CompareResult queryEnergyCompare(String projcetId, Integer energyType, Integer compareType, Integer year, Integer month) {
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH) + 1;
        // 查询当月
        List<DayAndEnergy> energyList = compareDao.queryEnergyCompare(projcetId, year, month, column);
        // 查询环比 or 同比 月
        switch (compareType) {
            case 1:// 同比
                calendar.add(Calendar.MONTH, -1);
                break;
            case 2:// 环比
                calendar.add(Calendar.YEAR, -1);
                break;
        }
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        List<DayAndEnergy> compareList = compareDao.queryEnergyCompare(projcetId, year, month, column);
        // 放入值
        CompareResult result = new CompareResult();
        // 初始化
        for (int i = 0; i < 31; i++) {
            result.getEnergyList().add(new BigDecimal(0));
            result.getCompareList().add(new BigDecimal(0));
            result.getDiffList().add(new BigDecimal(0));
        }
        // 放入当月
        energyList.forEach(dayAndEnergy -> {
            result.getEnergyList().set(dayAndEnergy.getDay() - 1, dayAndEnergy.getEnergy() == null ? new BigDecimal(0) : dayAndEnergy.getEnergy());
        });
        // 放入对比
        compareList.forEach(dayAndEnergy -> {
            result.getCompareList().set(dayAndEnergy.getDay() - 1, dayAndEnergy.getEnergy() == null ? new BigDecimal(0) : dayAndEnergy.getEnergy());
        });
        // 放入差值
        for (int i = 0; i < result.getDiffList().size(); i++) {
            result.getDiffList().set(i, result.getEnergyList().get(i).subtract(result.getCompareList().get(i)));
        }
        return result;
    }

    /**
     * 放入实际值，判断颜色
     * @param e 用量
     * @param standardSuggest 建议标准
     * @param standardIndustry 行业标准
     * @param standardCountry 国家标准
     * @param colorList 颜色返回值
     * @param energyList 用量返回值
     * @param color 当前正常显示的颜色
     */
    private void setValueAndColor(EnergyAndCount e, List<BigDecimal> standardSuggest, List<BigDecimal> standardIndustry, List<BigDecimal> standardCountry, List<String> colorList, List<BigDecimal> energyList, String color) {
        // 超标颜色
        String colorExcess = "#ccd4de";
        Integer countThisYear = e.getCount();
        if (countThisYear != 0) {
            BigDecimal count = new BigDecimal(countThisYear);
            // 水，这里可能还需要乘上一个系数，待确定
            BigDecimal water = e.getWater().divide(count, 2, BigDecimal.ROUND_HALF_UP);
            // 与三种标准做对比，只要有一个大于，显示为红色
            if (water.compareTo(standardSuggest.get(0)) > 0
                    && water.compareTo(standardIndustry.get(0)) > 0
                    && water.compareTo(standardCountry.get(0)) > 0) {
                colorList.add(colorExcess);
            } else {
                colorList.add(color);
            }
            energyList.add(water);
            // 电，这里可能还需要乘上一个系数，待确定
            BigDecimal elec = e.getElec().divide(count, 2, BigDecimal.ROUND_HALF_UP);
            // 与三种标准做对比，只要有一个大于，显示为红色
            if (elec.compareTo(standardSuggest.get(1)) > 0
                    && elec.compareTo(standardIndustry.get(1)) > 0
                    && elec.compareTo(standardCountry.get(1)) > 0) {
                colorList.add(colorExcess);
            } else {
                colorList.add(color);
            }
            energyList.add(elec);
            // 气，这里可能还需要乘上一个系数，待确定
            BigDecimal gas = e.getGas().divide(count, 2, BigDecimal.ROUND_HALF_UP);
            // 与三种标准做对比，只要有一个大于，显示为红色
            if (gas.compareTo(standardSuggest.get(2)) > 0
                    && gas.compareTo(standardIndustry.get(2)) > 0
                    && gas.compareTo(standardCountry.get(2)) > 0) {
                colorList.add(colorExcess);
            } else {
                colorList.add(color);
            }
            energyList.add(gas);
        } else { // 没有数据的情况补0
            for (int i = 0; i < 3; i++) {
                energyList.add(new BigDecimal(0));
                colorList.add(color);
            }
        }
    }
}
