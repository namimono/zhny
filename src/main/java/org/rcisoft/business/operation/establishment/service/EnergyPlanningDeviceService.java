package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.entity.EnergyPlanningDevice;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
public interface EnergyPlanningDeviceService {

    /**
     *  新增计划设备
     * @param energyPlanningDevice
     * @return
     */
    Integer saveEnergyPlanningDevice(EnergyPlanningDevice energyPlanningDevice);
}
