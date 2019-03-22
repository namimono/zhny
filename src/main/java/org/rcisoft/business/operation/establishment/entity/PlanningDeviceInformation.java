package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  保存设备信息
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningDeviceInformation {
    /**
     * 设备Id
     */
    String deviceId;

    /**
     * 设备名称
     */
    String deviceName;

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



    /**
     * 设备详细的计划编制信息
     */
    List<DeviceRecordInformation> deviceRecordInformationList;

}
