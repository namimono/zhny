package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/15.
 * 每小时价格
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PricePerHour {

    /**
     * 水
     * 电
     * 气
     */
    private BigDecimal waterPrice, elecPrice, gasPrice;

}
