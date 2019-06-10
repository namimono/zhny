package org.rcisoft.business.energy.emission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/25.
 * 返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmissionResult {

    // 排放量
    private BigDecimal
    // 减排量
        reductionCarbon,
    // 今日
        todayCarbon,
    // 昨日
        yestodayCarbon,
    // 本月
        thisMonthCarbon,
    // 本年
        thisYearCarbon,
    // 日同比
        dayCompareCarbon,
    // 日环比
        dayRingCarbon,
    // 月同比
        monthCompareCarbon,
    // 月环比
        monthRingCarbon;
    private String
    // 日同比 百分比
        dayComparePercent,
    // 日环比 百分比
        dayRingPercent,
    // 月同比 百分比
        monthComparePercent,
    // 月环比 百分比
        monthRingPercent
    ;

}
