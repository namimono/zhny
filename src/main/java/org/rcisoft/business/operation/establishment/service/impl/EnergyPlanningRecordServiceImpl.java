package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.DeviceParamIdAndSeq;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningRecordService;
import org.rcisoft.dao.EnergyPlanningCostDao;
import org.rcisoft.dao.EnergyPlanningRecordDao;
import org.rcisoft.entity.EnergyPlanningCost;
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
    @Autowired
    private EnergyPlanningCostDao energyPlanningCostDao;

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
                    //处理第一个主要参数
                    case 1:
                        energyPlanningRecord.setMainFirstId(deviceParamIdAndSeq.getParamFirstId());
                        energyPlanningRecord.setMainSecondId(deviceParamIdAndSeq.getParamSecondId());
                        break;
                    //处理第二个主要参数
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
        int insertFlag = energyPlanningRecordDao.insert(energyPlanningRecord);
        if (insertFlag >0){
            EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
            updateEnergyPlanningCost(energyPlanningCost);
        }
        return insertFlag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteEnergyPlanningRecordById(ConditionDto conditionDto) {
        return energyPlanningRecordDao.deleteByPrimaryKey(conditionDto.getEnergyPlanningRecordId());
    }


    /**
     * 新建，修改，删除计划编制的时候，修改计划能耗花费
     *     需要设备Id，创建时间
     * @author GaoLiWei
     * @date 17:04 2019/3/22
     **/
    private Integer updateEnergyPlanningCost(EnergyPlanningCost energyPlanningCost){

        //先查出旧的数据
        EnergyPlanningCost oldEnergyPlanningCost = energyPlanningCostDao.selectOne(energyPlanningCost);

       //根据设备ID，创建时间查出当天所有的计划
        EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
        energyPlanningRecord.setCreateTime(energyPlanningCost.getCreateTime());
        energyPlanningRecord.setDeviceId(energyPlanningCost.getDeviceId());
        List<EnergyPlanningRecord> energyPlanningRecordList = energyPlanningRecordDao.select(energyPlanningRecord);

        if (energyPlanningRecordList.size() > 0){
            for (EnergyPlanningRecord record : energyPlanningRecordList){
                long startTime = record.getStartTime().getTime();

            }
        }

        return null;
    }

}
