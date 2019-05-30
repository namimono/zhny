package org.rcisoft.business.wechat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GaoLiwei
 * @date 2019/5/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDetailed {

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型名称
     */
    private String deviceTypeName;

    /**
     * 项目Id
     */
    private String projectId;

    /**
     * 设备Id
     */
    private String deviceId;
}
