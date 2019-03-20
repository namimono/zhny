package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.entity.EnergyPlanningRecord;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
public interface EnergyPlanningRecordService {

    /**
     *  更新计划编制记录
     * @param energyPlanningRecord
     * @return
     */
    Integer updateEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord);


    /**
     *  添加一条设备编制计划
     * @param energyPlanningRecord
     * @return
     */
    Integer saveEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord);

    /**
     *  根据Id,删除计划编制
     * @param conditionDto
     * @return
     */
    Integer deleteEnergyPlanningRecordById(ConditionDto conditionDto);
}
