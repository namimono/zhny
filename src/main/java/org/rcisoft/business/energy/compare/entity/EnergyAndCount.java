package org.rcisoft.business.energy.compare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/20.
 * 水电气能耗和数量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergyAndCount {

    private BigDecimal water, elec, gas;

    /**
     * 数量（当年有记录天数的总和）
     */
    private Integer count;

}
