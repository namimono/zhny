package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/19.
 * 水or电or气 能耗
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergyStatisticsSplit {

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 水or电or气 能耗
     */
    private BigDecimal energy;

}
