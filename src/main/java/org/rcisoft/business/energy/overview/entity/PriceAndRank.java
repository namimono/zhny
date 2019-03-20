package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/15.
 * 今日、本月费用、排名
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PriceAndRank {

    /**
     * 日水费
     * 日电费
     * 日气费
     * 日总费
     */
    private BigDecimal dayWater, dayElec, dayGas, dayTotal;

    /**
     * 月水费
     * 月电费
     * 月气费
     * 月总费
     */
    private BigDecimal monWater, monElec, monGas, monTotal;

    /**
     * 排名
     */
    private Integer rank;

}
