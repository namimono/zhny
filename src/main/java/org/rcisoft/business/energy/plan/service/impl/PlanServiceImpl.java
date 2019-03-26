package org.rcisoft.business.energy.plan.service.impl;

import org.rcisoft.business.energy.plan.dao.PlanDao;
import org.rcisoft.business.energy.plan.entity.MoneyAndTime;
import org.rcisoft.business.energy.plan.entity.PlanResult;
import org.rcisoft.business.energy.plan.entity.PlanParam;
import org.rcisoft.business.energy.plan.entity.PlanStatisticsResult;
import org.rcisoft.business.energy.plan.service.PlanService;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by JiChao on 2019/3/21.
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanDao planDao;

    @Override
    public PlanStatisticsResult queryPlanStatistics(String projectId) {
        PlanStatisticsResult result = new PlanStatisticsResult();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = year + "-" + month;
        PlanParam p = new PlanParam(projectId, year, month, null, time);
        // 每日实际
        List<MoneyAndTime> dayReal = planDao.queryMoneyRealDay(p);
        // 每日计划
        List<MoneyAndTime> dayPlan = planDao.queryMoneyPlanDay(p);
        // 每月实际
        List<MoneyAndTime> monReal = planDao.queryMoneyRealMon(p);
        // 每月计划
        List<MoneyAndTime> monPlan = planDao.queryMoneyPlanMon(p);
        // 本月运行天数
        Map<String, Object> dayMap = this.setResult(dayReal, dayPlan, day);
        BigDecimal moneyDayReal = (BigDecimal) dayMap.get("real");
        BigDecimal moneyDayPlan = (BigDecimal) dayMap.get("plan");
        Integer runDay = (Integer) dayMap.get("run");
        Integer standardDay = (Integer) dayMap.get("standard");
        // 放入值
        result.setMoneyDayReal(moneyDayReal);
        result.setMoneyDayPlan(moneyDayPlan);
        result.setRunDay(runDay);
        result.setStandardDay(standardDay);
        // 计算比例并放入返回值
        if (runDay != 0) {
            result.setPercent(standardDay * 100 / runDay + "%");
        }
        // 本年运行月数
        Map<String, Object> monMap = this.setResult(monReal, monPlan, month);
        BigDecimal moneyMonReal = (BigDecimal) monMap.get("real");
        BigDecimal moneyMonPlan = (BigDecimal) monMap.get("plan");
        Integer runMon = (Integer) monMap.get("run");
        Integer standardMon = (Integer) monMap.get("standard");
        // 放入值
        result.setMoneyMonReal(moneyMonReal);
        result.setMoneyMonPlan(moneyMonPlan);
        result.setRunMon(runMon);
        result.setStandardMon(standardMon);
        return result;
    }

    @Override
    public PlanResult queryPlanDay(String projectId) {
        // 返回值
        PlanResult result = new PlanResult();
        List<BigDecimal> resultRealList = result.getRealList();
        List<BigDecimal> resultPlanList = result.getPlanList();
        // 初始化
        for (int i = 0; i < 24; i++) {
            resultRealList.add(new BigDecimal(0));
            resultPlanList.add(new BigDecimal(0));
        }
        // 参数
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = year + "-" + month + "-" + day;
        PlanParam p = new PlanParam(projectId, year, month, day, time);
        // 查找实际值
        List<MoneyAndTime> realList = planDao.queryMoneyRealHour(p);
        // 查找计划值
        List<EnergyPlanningRecord> planList = planDao.queryMoneyPlanHour(p);
        // 实际值放入结果集
        realList.forEach(moneyAndTime -> {
            resultRealList.set(moneyAndTime.getTime(), moneyAndTime.getMoneyElec().add(moneyAndTime.getMoneyGas()));
        });
        // 计划值循环并赋值
        planList.forEach(e -> {
            Date startTime = e.getStartTime();
            Date endTime = e.getEndTime();
            BigDecimal money = e.getMoneyElec().add(e.getMoneyGas());
            // 计算范围内有多少个10分钟
            int count = Math.round((endTime.getTime() - startTime.getTime()) / 600000);
            // 得到bigdecimal类型的分母
            BigDecimal denominator = new BigDecimal(count);
            // 将时间设定为开始时间
            calendar.setTime(startTime);
            // 获得开始的小时，分钟
            int hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE);
            // 设定一个计数器，统计每个小时有多少个10分钟
            int num = 0;
            // 循环n个10分钟
            for (int i = 0; i < count; i++) {
                // 如果到达60分钟，计算出这段时间的值，存到对应位置，然后计数器清0，开始的小时+1，开始分钟清0
                if ((minute != 0 && minute % 60 == 0) || i == count - 1) {
                    BigDecimal m = money.multiply(new BigDecimal(num)).divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
                    // 在结果集里找到对应的小时位置，更新
                    resultPlanList.set(hour, resultPlanList.get(hour).add(m));
                    // 更新时间
                    hour++;
                    minute = 0;
                    num = 0;
                }
                // 计数器+1
                num++;
                // 分钟+10
                minute += 10;
            }
        });
        return result;
    }

    @Override
    public PlanResult queryPlanMonth(String projectId, Integer year, Integer month) {
        // 参数
        Calendar calendar = Calendar.getInstance();
        String time = year + "-" + month;
        PlanParam p = new PlanParam(projectId, year, month, null, time);
        // 每月天数
        int date = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (date == 28) {date = 29;} // 2月按照29天算，避免空指针
        // 返回值
        PlanResult result = new PlanResult();
        List<BigDecimal> resultRealList = result.getRealList();
        List<BigDecimal> resultPlanList = result.getPlanList();
        // 初始化
        for (int i = 0; i < date; i++) {
            resultRealList.add(new BigDecimal(0));
            resultPlanList.add(new BigDecimal(0));
        }
        // 查询实际
        List<MoneyAndTime> realList = planDao.queryMoneyRealDay(p);
        // 查询计划
        List<MoneyAndTime> planList = planDao.queryMoneyPlanDay(p);
        // 放入值
        realList.forEach(moneyAndTime -> {
            resultRealList.set(moneyAndTime.getTime() - 1, moneyAndTime.getMoneyElec().add(moneyAndTime.getMoneyGas()));
        });
        planList.forEach(moneyAndTime -> {
            resultPlanList.set(moneyAndTime.getTime() - 1, moneyAndTime.getMoneyElec().add(moneyAndTime.getMoneyGas()));
        });
        return result;
    }

    /**
     * 天数or月份
     * @param realList 实际值
     * @param planList 计划值
     * @param time 时间，日 or 月
     * return real：实际值，plan：计划值，run：运行数，standard：达标数
     */
    private Map<String, Object> setResult(List<MoneyAndTime> realList, List<MoneyAndTime> planList, Integer time) {
        Map<String, Object> result = new HashMap<>();
        // 设置默认值
        result.put("real", new BigDecimal(0));
        result.put("plan", new BigDecimal(0));
        // 运行天数
        Integer run = realList.size();
        // 达标数量，默认全部达标
        int standard = realList.size();
        for (MoneyAndTime real : realList) {
            Integer timeReal = real.getTime();
            BigDecimal moneyReal = real.getMoneyElec().add(real.getMoneyGas());
            // 判断是否是今天or当月
            if (timeReal == time) {
                result.put("real", moneyReal);
            }
            for (MoneyAndTime plan : planList) {
                Integer timePlan = plan.getTime();
                BigDecimal moneyPlan = plan.getMoneyElec().add(plan.getMoneyGas());
                // 判断是否是今天or当月
                if (timePlan == time) {
                    result.put("plan", moneyPlan);
                }
                // 筛选同一天or同月
                if (timeReal == timePlan) {
                    // 如果实际金额大于计划金额，认为不达标，达标数-1
                    if (moneyReal.subtract(moneyPlan).compareTo(new BigDecimal(0)) > 0) {
                        standard--;
                        break;
                    }
                }
            }
        }
        // 放入运行天数or月数
        result.put("run", run);
        // 放入达标天数or月数
        result.put("standard", standard);
        return result;
    }
}
