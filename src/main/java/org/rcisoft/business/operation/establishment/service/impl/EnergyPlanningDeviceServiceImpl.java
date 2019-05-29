package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningDeviceService;
import org.rcisoft.dao.EnergyPlanningCostDao;
import org.rcisoft.dao.EnergyPlanningDeviceDao;
import org.rcisoft.dao.EnergyPlanningRecordDao;
import org.rcisoft.entity.EnergyPlanningCost;
import org.rcisoft.entity.EnergyPlanningDevice;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class EnergyPlanningDeviceServiceImpl implements EnergyPlanningDeviceService {

    @Autowired
    private EnergyPlanningDeviceDao energyPlanningDeviceDao;
    @Autowired
    private EnergyPlanningCostDao energyPlanningCostDao;
    @Autowired
    private EnergyPlanningRecordDao energyPlanningRecordDao;

    @Override
    public Integer saveEnergyPlanningDevice(EnergyPlanningDevice energyPlanningDevice) {
        //查出某一天所有已经添加到计划编制的设备
        EnergyPlanningDevice planningDevice = new EnergyPlanningDevice();
        planningDevice.setCreateTime(energyPlanningDevice.getCreateTime());
        planningDevice.setDeviceId(energyPlanningDevice.getDeviceId());
        List<EnergyPlanningDevice> energyPlanningDeviceList = energyPlanningDeviceDao.select(planningDevice);
        //如果要添加的设备Id已经添加过，则返回，不可以重复添加
        if (energyPlanningDeviceList.size() >0){
            return 0;
        }
        energyPlanningDevice.setId(UuidUtil.create32());
        return energyPlanningDeviceDao.insert(energyPlanningDevice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteEnergyPlanningDevice(EnergyPlanningDevice energyPlanningDevice) {
        //删除计划编制设备表
        int flagDev = energyPlanningDeviceDao.delete(energyPlanningDevice);
        //删除计划编制花费表
        EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
        energyPlanningCost.setDeviceId(energyPlanningDevice.getDeviceId());
        energyPlanningCost.setCreateTime(energyPlanningDevice.getCreateTime());
        int flagCost = energyPlanningCostDao.delete(energyPlanningCost);
        //删除计划编制记录表
        EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
        energyPlanningRecord.setDeviceId(energyPlanningDevice.getDeviceId());
        energyPlanningRecord.setCreateTime(energyPlanningDevice.getCreateTime());
        int flagRecord = energyPlanningRecordDao.delete(energyPlanningRecord);
        if (flagDev == 0 && flagCost == 0 && flagRecord == 0){
            return 0;
        }
        return 1;
    }
}
