package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据系统类型查询设备信息
     */
    @Override
    public List<BusDevice> queryDeviceInfo(BusDevice busDevice){
        return busDeviceDao.queryDeviceInfo(busDevice);
    }

}
