package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:42 2019/3/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDetail {
    /**
     * 一级参数
     */
    private String codingFirst;
    /**
     * 二级参数
     */
    private String codingSecond;
    /**
     * 固定参数数值
     */
    private BigDecimal value;
    /**
     * 单位
     */
    private String unit;
    /**
     * 二级参数名称
     */
    private String name;
    /**
     * 故障最小值
     */
    private BigDecimal minVal;
    /**
     * 故障最小值
     */
    private BigDecimal maxVal;
    /**
     * 故障内容
     */
    private String content;
    /**
     * 能耗分类id
     */
    private int energyTypeId;
    /**
     * 用电类型 0：电度 1：功率
     */
    private int elecType;
}
