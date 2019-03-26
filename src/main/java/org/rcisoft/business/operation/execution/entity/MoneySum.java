package org.rcisoft.business.operation.execution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author GaoLiwei
 * @date 2019/3/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneySum {

    /**
     * 电花费的总和
     */
    BigDecimal sumMoneyElec;

    /**
     * 气花费的总和
     */
    BigDecimal sumMoneyGas;
}
