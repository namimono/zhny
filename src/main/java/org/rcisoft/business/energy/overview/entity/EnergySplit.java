package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/19.
 * 能耗拆分
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergySplit {

    /**
     * 设备名称
     */
    private String name;

    /**
     * 水，电，气能耗
     */
    private BigDecimal value;

    /**
     * 百分比，0：默认值
     */
    private String percent;

}
