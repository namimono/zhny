package org.rcisoft.business.energy.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/3/22.
 * 今日、月用能计划 返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlanResult {

    /**
     * 实际24小时值，默认0
     */
    private List<BigDecimal> realList = new ArrayList<>();

    /**
     * 计划24小时值，默认0
     */
    private List<BigDecimal> planList = new ArrayList<>();

}
