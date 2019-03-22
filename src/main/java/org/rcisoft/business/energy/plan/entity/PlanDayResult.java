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
 * 今日用能计划 返回值
 */
@Data
@AllArgsConstructor
@Entity
public class PlanDayResult {

    /**
     * 初始化
     */
    public PlanDayResult() {
        for (int i = 0; i < 24; i++) {
            realList.add(new BigDecimal(0));
            planList.add(new BigDecimal(0));
        }
    }

    /**
     * 实际24小时值，默认0
     */
    private List<BigDecimal> realList = new ArrayList<>(24);

    /**
     * 计划24小时值，默认0
     */
    private List<BigDecimal> planList = new ArrayList<>(24);

}
