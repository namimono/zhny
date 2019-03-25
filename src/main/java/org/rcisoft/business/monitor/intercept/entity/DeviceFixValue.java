package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:27 2019/3/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceFixValue {
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数编码
     */
    private String coding;

    /**
     * 单位
     */
    private String unit;

    /**
     * 固定参数
     */
    private BigDecimal fixValue;
}
