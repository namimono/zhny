package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningRecordService;
import org.rcisoft.dao.EnergyPlanningRecordDao;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class EnergyPlanningRecordServiceImpl implements EnergyPlanningRecordService {
    @Autowired
    private EnergyPlanningRecordDao energyPlanningRecordDao;

    @Override
    public Integer updateEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord) {
        return energyPlanningRecordDao.updateByPrimaryKeySelective(energyPlanningRecord);
    }

    @Override
    public Integer saveEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord) {
        energyPlanningRecord.setId(UuidUtil.create32());
        return energyPlanningRecordDao.insert(energyPlanningRecord);
    }

    @Override
    public Integer deleteEnergyPlanningRecordById(ConditionDto conditionDto) {
        return energyPlanningRecordDao.deleteByPrimaryKey(conditionDto.getEnergyPlanningRecordId());
    }
}
