package org.rcisoft.business.operation.saving.service.impl;

import org.rcisoft.base.util.ZhnyUtils;
import org.rcisoft.business.operation.saving.entity.PlanCostAndRealCost;
import org.rcisoft.business.operation.saving.service.EnergySavingService;
import org.rcisoft.dao.EnergyPlanningRecordDao;
import org.rcisoft.dao.EnergyStatisticsDao;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.rcisoft.entity.EnergyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GaoLiwei
 * @date 2019/5/28
 */
@Service
public class EnergySavingServiceImpl implements EnergySavingService {

    @Autowired
    private EnergyPlanningRecordDao energyPlanningRecordDao;
    @Autowired
    private EnergyStatisticsDao energyStatisticsDao;

    @Override
    public List<PlanCostAndRealCost> listRunningEnergyCost(String projectId) {

        //查出这个项目今天所有的计划能耗
        List<EnergyPlanningRecord> energyPlanningRecordList = energyPlanningRecordDao.listEnergyPlanningRecordDaoByProIdAndData(
                projectId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        //查出这个项目今天的实际能耗
        EnergyStatistics useEnergyStatistics = new EnergyStatistics();
        useEnergyStatistics.setProjectId(projectId);
        //得到当前的年 月 日
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        useEnergyStatistics.setTimeYear(year);
        useEnergyStatistics.setTimeMonth(month);
        useEnergyStatistics.setTimeDay(day);
        List<EnergyStatistics> energyStatisticsList = energyStatisticsDao.select(useEnergyStatistics);
        //将查出来的数据，按每小时进行分组
        Map<Object, List> energyStatisticsListGroup = ZhnyUtils.groupListByName(energyStatisticsList, "timeHour");

        //得到当天结束时间
        Long dayEndTime = ZhnyUtils.getDayEndTime(new Date()).getTime();
        //表示每小时循环一次
        int timeFlag = 3600000;
        //要返回的数据
        List<PlanCostAndRealCost> returnPlanCostAndRealCostList = new ArrayList<>();

        //每小时循环一次，对数据进行处理
        for (Long dayStartTime = ZhnyUtils.getDayStartTime(new Date()).getTime(); dayStartTime <= dayEndTime; dayStartTime += timeFlag) {

            //获得当前开始时间是哪个小时
            Date date = new Date(dayStartTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            //获得这个小时的计划金额
            BigDecimal planCost = new BigDecimal(0);
            if (energyPlanningRecordList.size() > 0) {
                for (EnergyPlanningRecord energyPlanningRecord : energyPlanningRecordList) {
                    //当前时间在项目的某个计划编制的时间段内
                    if (dayStartTime >= energyPlanningRecord.getStartTime().getTime() && dayStartTime < energyPlanningRecord.getEndTime().getTime()) {
                        //算出这个计划编制在这个小时内运行了多久
                        //如果这段计划的结束时间大于等于这个小时的时间，则证明这一个小时都在计划内
                        if (energyPlanningRecord.getEndTime().getTime() >= dayStartTime + timeFlag) {
                            planCost = planCost.add(energyPlanningRecord.getMoneyElec());
                            planCost = planCost.add(energyPlanningRecord.getMoneyGas());
                        } else {//如果这段计划的结束时间小于这个小时的时间，则证明这一个小时只有某一段时间在计划内，需要计算
                            //计算出相差了多少小时
                            double diffHour = (double) (energyPlanningRecord.getEndTime().getTime() - dayStartTime) / timeFlag;
                            planCost = planCost.add(energyPlanningRecord.getMoneyElec().multiply(new BigDecimal(diffHour)));
                            planCost = planCost.add(energyPlanningRecord.getMoneyGas().multiply(new BigDecimal(diffHour)));
                        }
                    }
                }
            }

            //获得这个小时的实际金额
            BigDecimal realCost = new BigDecimal(0);
            if (energyStatisticsList.size() > 0) {
                List<EnergyStatistics> list = energyStatisticsListGroup.get(hour);
                if (null != list) {
                    for (EnergyStatistics energyStatistics : list) {
                        realCost = realCost.add(energyStatistics.getMoneyElec());
                        realCost = realCost.add(energyStatistics.getMoneyGas());
                    }
                }
            }

            //封装数据
            PlanCostAndRealCost planCostAndRealCost = new PlanCostAndRealCost();
            planCostAndRealCost.setHour(hour);
            planCostAndRealCost.setPlanCost(planCost);
            planCostAndRealCost.setRealCost(realCost);
            returnPlanCostAndRealCostList.add(planCostAndRealCost);
        }

        return returnPlanCostAndRealCostList;
    }
}
