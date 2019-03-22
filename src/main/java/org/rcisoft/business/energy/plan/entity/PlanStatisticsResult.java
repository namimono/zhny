package org.rcisoft.business.energy.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/21.
 * 用能计划统计 返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlanStatisticsResult {

    /**
     * 今日实际、计划金额
     */
    private BigDecimal moneyDayReal, moneyDayPlan;

    /**
     * 本月实际、计划金额
     */
    private BigDecimal moneyMonReal, moneyMonPlan;

    /**
     * 本月运行、达标天数
     */
    private Integer runDay, standardDay;

    /**
     * 本年运行、达标月数
     */
    private Integer runMon, standardMon;

    /**
     * 本月达标天数比例
     */
    private String percent = "0%";

}
