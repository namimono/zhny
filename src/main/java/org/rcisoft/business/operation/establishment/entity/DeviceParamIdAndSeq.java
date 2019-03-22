package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  设备参数Id与对应的seq标志位
 * @author GaoLiwei
 * @date 2019/3/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceParamIdAndSeq {

    /**
     * 设备参数一级Id
     */
    String paramFirstId;

    /**
     * 设备参数一级Id
     */
    String paramSecondId;

    /**
     * 表示第几个参数
     *      1代表主参数1；2代表主参数2；3代表副参数；4代表副参数2
     */
    int sequence;
}
