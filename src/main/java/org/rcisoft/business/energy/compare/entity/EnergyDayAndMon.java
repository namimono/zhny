package org.rcisoft.business.energy.compare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/20.
 * 能耗管理--用能比较--能耗概况
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergyDayAndMon {

    /**
     * 今日
     * 水电气
     */
    private BigDecimal dayWater, dayElec, dayGas;

    /**
     * 本月
     * 水电气
     */
    private BigDecimal monWater, monElec, monGas;


}
