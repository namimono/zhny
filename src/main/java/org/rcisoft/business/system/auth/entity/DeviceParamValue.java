package org.rcisoft.business.system.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 设备的参数库参数的值,对应XYZ轴
 * @author GaoLiwei
 * @date 2019/5/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceParamValue {


    /**
     * x参数值
     */
    BigDecimal xzValue;

    /**
     * y参数值
     */
    BigDecimal yzValue;

    /**
     * z参数值
     */
    BigDecimal zzValue;
}
