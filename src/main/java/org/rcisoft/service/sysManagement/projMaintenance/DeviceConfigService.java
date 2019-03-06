package org.rcisoft.service.sysManagement.projMaintenance;

import org.rcisoft.entity.BusDevice;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:12
 **/

public interface DeviceConfigService {

    /**
     * 新增设备配置信息
     */
    int addDeviceConfigInfo(BusDevice busDevice);
}
