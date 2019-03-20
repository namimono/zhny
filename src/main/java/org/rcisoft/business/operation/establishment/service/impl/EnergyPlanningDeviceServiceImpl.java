package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningDeviceService;
import org.rcisoft.dao.EnergyPlanningDeviceDao;
import org.rcisoft.entity.EnergyPlanningDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class EnergyPlanningDeviceServiceImpl implements EnergyPlanningDeviceService {

    @Autowired
    private EnergyPlanningDeviceDao energyPlanningDeviceDao;

    @Override
    public Integer saveEnergyPlanningDevice(EnergyPlanningDevice energyPlanningDevice) {
        //查出某一天所有已经添加到计划编制的设备
        EnergyPlanningDevice planningDevice = new EnergyPlanningDevice();
        planningDevice.setCreateTime(energyPlanningDevice.getCreateTime());
        List<EnergyPlanningDevice> energyPlanningDeviceList = energyPlanningDeviceDao.select(planningDevice);
        for (EnergyPlanningDevice device : energyPlanningDeviceList){
            //如果要添加的设备Id已经添加过，则返回，不可以重复添加
            if (device.getDeviceId().equals(energyPlanningDevice.getDeviceId())){
                return 0;
            }
        }
        energyPlanningDevice.setId(UuidUtil.create32());
        return energyPlanningDeviceDao.insert(energyPlanningDevice);
    }
}
