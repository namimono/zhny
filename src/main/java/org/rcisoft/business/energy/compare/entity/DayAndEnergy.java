package org.rcisoft.business.energy.compare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DayAndEnergy {

    /**
     * 水电气能耗
     */
    private BigDecimal energy;

    /**
     * 日期
     */
    private Integer day;

}
