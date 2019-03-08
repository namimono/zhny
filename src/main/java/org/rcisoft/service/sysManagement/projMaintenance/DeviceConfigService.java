package org.rcisoft.service.sysManagement.projMaintenance;

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
    List<Map<String,String>> queryDeviceInfo(BusDevice busDevice);
}
