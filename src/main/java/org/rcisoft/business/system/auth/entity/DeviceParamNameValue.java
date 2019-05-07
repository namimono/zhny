package org.rcisoft.business.system.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 设备的参数库参数的名称,对应XYZ轴
 * @author GaoLiwei
 * @date 2019/5/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceParamNameValue {
    /**
     * x轴参数名称
     */
    String xzName;

    /**
     * y轴参数名称
     */
    String yzName;

    /**
     * z轴参数名称
     */
    String zzName;

    /**
     * 参数的值
     */
    List<DeviceParamValue> deviceParamValueList;


}
