package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.DeviceParamIdAndSeq;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningRecordService;
import org.rcisoft.dao.EnergyPlanningRecordDao;
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
public class EnergyPlanningRecordServiceImpl implements EnergyPlanningRecordService {
    @Autowired
    private EnergyPlanningRecordDao energyPlanningRecordDao;
    @Autowired
    private DevicePlanningRepository devicePlanningRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord) {
        return energyPlanningRecordDao.updateByPrimaryKeySelective(energyPlanningRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord) {
        List<DeviceParamIdAndSeq> deviceParamIdAndSeqList = devicePlanningRepository.listDeviceParamIdAndSeqByDevId(energyPlanningRecord.getDeviceId());
        if (deviceParamIdAndSeqList.size() > 0){
            for (DeviceParamIdAndSeq deviceParamIdAndSeq : deviceParamIdAndSeqList){
                switch (deviceParamIdAndSeq.getSequence()){
                    case 1:
                        energyPlanningRecord.setMainFirstId(deviceParamIdAndSeq.getParamFirstId());
                        energyPlanningRecord.setMainSecondId(deviceParamIdAndSeq.getParamSecondId());
                        break;
                    case 2:
                        energyPlanningRecord.setMainFirstId2(deviceParamIdAndSeq.getParamFirstId());
                        energyPlanningRecord.setMainSecondId2(deviceParamIdAndSeq.getParamSecondId());
                        break;
                    default:
                        break;
                }
            }
        }
        energyPlanningRecord.setId(UuidUtil.create32());
        return energyPlanningRecordDao.insert(energyPlanningRecord);
    }

    @Override
    public Integer deleteEnergyPlanningRecordById(ConditionDto conditionDto) {
        return energyPlanningRecordDao.deleteByPrimaryKey(conditionDto.getEnergyPlanningRecordId());
    }
}
