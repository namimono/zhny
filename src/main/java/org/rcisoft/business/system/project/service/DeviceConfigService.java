package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
import org.rcisoft.entity.BusDevice;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:12
 **/

public interface DeviceConfigService {

    /**
     * 新增设备配置信息
     */
    int addDeviceConfigInfo(BusDevice busDevice);

    /**
     * 根据系统类型查询设备信息
     */
    List<BusDevice> queryDeviceInfo(BusDevice busDevice);

    /**
     * 查询设备简要信息
     */
    List<DeviceBriefInfo> queryDeviceBriefInfo(BusDevice busDevice);

    /**
     * 根据系统类型ID和一级设备类型ID查询二级设备类型信息
     */
    List<TypeFirstAndSecond> queryTypeSecondInfo(String systemId);

    /**
     * 处理一、二级设备类型下拉菜单级联格式
     */
    List<Map<String,Object>> processTypeFormat(String systemId);


}
