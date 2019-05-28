package org.rcisoft.business.operation.saving.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author GaoLiwei
 * @date 2019/5/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanCostAndRealCost {

    /**
     * 计划费用
     */
    private BigDecimal planCost;

    /**
     * 实际金额
     */
    private BigDecimal realCost;

    /**
     * 小时（0-23）
     */
    private int hour;


}
