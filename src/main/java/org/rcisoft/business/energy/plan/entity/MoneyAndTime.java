package org.rcisoft.business.energy.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/22.
 * 花费和日期
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MoneyAndTime {

    /**
     * 电气花费
     */
    private BigDecimal moneyElec, moneyGas;

    /**
     * 时间，日 or 月 or 小时
     */
    private Integer time;

}
