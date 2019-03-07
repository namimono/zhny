package org.rcisoft.service.sysManagement.projMaintenance.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.service.sysManagement.projMaintenance.DeviceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:13
 **/
@Service
public class DeviceConfigServiceImpl implements DeviceConfigService {

    @Autowired
    private BusDeviceDao busDeviceDao;

    /**
     * 新增设备配置信息
     */
    @Override
    public int addDeviceConfigInfo(BusDevice busDevice){
        busDevice.setId(UuidUtil.create32());
        return busDeviceDao.insertSelective(busDevice);
    }
}
