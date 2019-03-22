package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  设备参数名称
 * @author GaoLiwei
 * @date 2019/3/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceParamName {

    /**
     * 第一个主参数名称
     */
    String mainName;

    /**
     * 第二个主参数名称
     */
    String mainName2;

    /**
     * 第一个副参数名称
     */
    String paramName;

    /**
     * 第二个副参数名称
     */
    String paramName2;
}
