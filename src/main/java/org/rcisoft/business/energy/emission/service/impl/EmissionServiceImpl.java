package org.rcisoft.business.energy.emission.service.impl;

import org.rcisoft.business.energy.emission.dao.EmissionDao;
import org.rcisoft.business.energy.emission.entity.EmissionParam;
import org.rcisoft.business.energy.emission.entity.EmissionResult;
import org.rcisoft.business.energy.emission.entity.EmissionStatisticsResult;
import org.rcisoft.business.energy.emission.service.EmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by JiChao on 2019/3/25.
 */
@Service
public class EmissionServiceImpl implements EmissionService {

    @Autowired
    EmissionDao emissionDao;

    @Override
    public EmissionResult queryEmission(String projectId) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 参数
        EmissionParam p = new EmissionParam(projectId, year, month, day);
        // 返回值
        EmissionResult result = new EmissionResult();
        // 累计减排
        result.setReductionCarbon(this.isNull(emissionDao.queryCarbonReduction(p)));
        // 今日
        result.setTodayCarbon(this.isNull(emissionDao.queryCarbonDay(p)));
        // 本月
        result.setThisMonthCarbon(this.isNull(emissionDao.queryCarbonMonth(p)));
        // 本年
        result.setThisYearCarbon(this.isNull(emissionDao.queryCarbonYear(p)));
        // 昨日
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        p.setDay(day);
        result.setYestodayCarbon(this.isNull(emissionDao.queryCarbonDay(p)));

        // 日同比
        // 重置日期为今天
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, -1);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        p.setYear(year);
        p.setMonth(month);
        p.setDay(day);
        result.setDayCompareCarbon(this.isNull(emissionDao.queryCarbonDay(p)));
        // 月同比
        result.setMonthCompareCarbon(this.isNull(emissionDao.queryCarbonMonth(p)));

        // 日环比
        calendar.setTime(today);// 重置日期为今天
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        p.setYear(year);
        p.setMonth(month);
        p.setDay(day);
        result.setDayRingCarbon(this.isNull(emissionDao.queryCarbonDay(p)));

        // 月环比
        calendar.setTime(today);// 重置日期为今天
        calendar.add(Calendar.MONTH, -1);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        p.setYear(year);
        p.setMonth(month);
        p.setDay(day);
        result.setMonthRingCarbon(this.isNull(emissionDao.queryCarbonMonth(p)));

        // 日同比 百分比
        result.setDayComparePercent(this.percent(result.getTodayCarbon(), result.getDayCompareCarbon()));
        // 日环比 百分比
        result.setDayRingPercent(this.percent(result.getTodayCarbon(), result.getDayRingCarbon()));
        // 月同比 百分比
        result.setMonthComparePercent(this.percent(result.getThisMonthCarbon(), result.getMonthCompareCarbon()));
        // 月环比 百分比
        result.setMonthRingPercent(this.percent(result.getThisMonthCarbon(), result.getMonthRingCarbon()));
        return result;
    }

    @Override
    public List<BigDecimal> queryEmissionStatistics(EmissionParam emissionParam) {
        Calendar calendar = Calendar.getInstance();
        // 默认是24小时的返回值
        int size = 24;
        // 如果天数是null，需要查询日期的统计，重置list的大小
        Integer day = emissionParam.getDay();
        if (day == null) {
            size = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 返回的结果集
        List<BigDecimal> resultList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            resultList.add(new BigDecimal(0));
        }
        // 查询的结果集
        List<EmissionStatisticsResult> list = emissionDao.queryEmissionStatisticsDay(emissionParam);
        list.forEach(e -> {
            Integer time = e.getTime();
            // 日期统计，从1开始
            if (day == null) {
                time--;
            }
            resultList.set(time, e.getCarbon());
        });
        return resultList;
    }

    /**
     * 空值返回0
     * @param value
     * @return
     */
    private BigDecimal isNull(BigDecimal value) {
        if (value == null) {
            return new BigDecimal(0);
        } else {
            return value;
        }
    }

    /**
     * 计算百分比
     * 如果有无法对比的情况，返回空字符串
     * @param now 当前值
     * @param compare 对比值
     * @return
     */
    private String percent(BigDecimal now, BigDecimal compare) {
        if (compare.compareTo(new BigDecimal(0)) != 0 && now.compareTo(new BigDecimal(0)) != 0) {
            return now.subtract(compare).multiply(new BigDecimal(100)).divide(compare, 2, BigDecimal.ROUND_HALF_UP).toString() + "%";
        } else {
            return "";
        }
//        if (compare.compareTo(new BigDecimal(0)) != 0) {
//            if (now.compareTo(new BigDecimal(0)) != 0) {
//                return now.subtract(compare).multiply(new BigDecimal(100)).divide(compare, 2, BigDecimal.ROUND_HALF_UP).toString() + "%";
//            } else {
//                return "-100%";
//            }
//        } else {
//            if (now.compareTo(new BigDecimal(0)) != 0) {
//                return "100%";
//            } else {
//                return "0%";
//            }
//        }
    }

}
