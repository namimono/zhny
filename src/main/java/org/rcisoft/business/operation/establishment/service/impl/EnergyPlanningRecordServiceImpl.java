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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        int updateFlag = energyPlanningRecordDao.updateByPrimaryKeySelective(energyPlanningRecord);
        if (updateFlag > 0){
            //得到计划编制记录
            EnergyPlanningRecord planningRecord = energyPlanningRecordDao.selectByPrimaryKey(energyPlanningRecord.getId());
            //更新当天的计划能耗花费
            updateEnergyPlanningCost(planningRecord);

        }
        return updateFlag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveEnergyPlanningRecord(EnergyPlanningRecord energyPlanningRecord) throws ParseException {
        List<DeviceParamIdAndSeq> deviceParamIdAndSeqList = devicePlanningRepository.listDeviceParamIdAndSeqByDevId(energyPlanningRecord.getDeviceId());
        int insertFlag = 0;
        if (deviceParamIdAndSeqList.size() > 0){
            for (DeviceParamIdAndSeq deviceParamIdAndSeq : deviceParamIdAndSeqList){
                switch (deviceParamIdAndSeq.getFirstSign()){
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
            energyPlanningRecord.setId(UuidUtil.create32());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = sdf.format(energyPlanningRecord.getCreateTime());
            energyPlanningRecord.setCreateTime(sdf.parse(formatDate));
            insertFlag = energyPlanningRecordDao.insert(energyPlanningRecord);
            if (insertFlag >0){
                //更新当天的计划能耗花费
                updateEnergyPlanningCost(energyPlanningRecord);
            }
        }
        return insertFlag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteEnergyPlanningRecordById(ConditionDto conditionDto) {

        //得到要删除计划编制记录，为了拿设备Id
        EnergyPlanningRecord planningRecord = energyPlanningRecordDao.selectByPrimaryKey(conditionDto.getEnergyPlanningRecordId());

        int deleteFlag = energyPlanningRecordDao.deleteByPrimaryKey(conditionDto.getEnergyPlanningRecordId());
        if (deleteFlag > 0){
            //更新当天的计划能耗花费
            updateEnergyPlanningCost(planningRecord);
        }
        return deleteFlag;
    }


    /**
     * 新建，修改，删除计划编制的时候，修改计划能耗花费
     *     需要设备Id，创建时间
     * @author GaoLiWei
     * @date 17:04 2019/3/22
     **/
    private void updateEnergyPlanningCost(EnergyPlanningRecord planningRecord){

        EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
        energyPlanningCost.setDeviceId(planningRecord.getDeviceId());
        energyPlanningCost.setCreateTime(planningRecord.getCreateTime());

        //先查出旧的数据,修改的时候，有数据；新增的时候没数据；删除的时候可能有数据，可能没数据
        EnergyPlanningCost oldEnergyPlanningCost = energyPlanningCostDao.selectOne(energyPlanningCost);

       //根据设备ID，创建时间查出当天所有的计划
        EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
        energyPlanningRecord.setDeviceId(planningRecord.getDeviceId());
        energyPlanningRecord.setCreateTime(planningRecord.getCreateTime());
        List<EnergyPlanningRecord> energyPlanningRecordList = energyPlanningRecordDao.select(energyPlanningRecord);

        //新增或者修改之后，一定有计划编制记录，继续处理，
        // 删除之后可能没有计划编制记录，如果有，则继续处理
        if (energyPlanningRecordList.size() > 0){

            //电费用的总和
            BigDecimal sumMoneyElec = new BigDecimal(0);
            //电能耗的总和
            BigDecimal sumEnergyElec = new BigDecimal(0);
            //气费用的总和
            BigDecimal sumMoneyGas = new BigDecimal(0);
            //气能耗的总和
            BigDecimal sumEnergyGas = new BigDecimal(0);

            for (EnergyPlanningRecord record : energyPlanningRecordList){
                //开始时间
                long startTime = record.getStartTime().getTime();
                //结束时间
                long endTime = record.getEndTime().getTime();
                //经过了多少时间
                long useTime = endTime - startTime;
                //每个小时的毫秒
                long hourMillisecond = 60*60*1000;
                //经过了几个小时
                double hour = ((double)useTime)/hourMillisecond;
                //这个计划编制预计消耗的电费用
                BigDecimal moneyElec = record.getMoneyElec().multiply(new BigDecimal(hour)).setScale(2,BigDecimal.ROUND_HALF_UP);
                //这个计划编制预计消耗的气费用
                BigDecimal moneyGas = record.getMoneyGas().multiply(new BigDecimal(hour)).setScale(2,BigDecimal.ROUND_HALF_UP);
                //算和
                sumMoneyElec = sumMoneyElec.add(moneyElec);
                sumMoneyGas = sumMoneyGas.add(moneyGas);
                //这个计划编制预计消耗的电能耗
                BigDecimal energyElec = record.getEnergyElec().multiply(new BigDecimal(hour)).setScale(2,BigDecimal.ROUND_HALF_UP);
                //这个计划编制预计消耗的气能耗
                BigDecimal energyGas = record.getEnergyGas().multiply(new BigDecimal(hour)).setScale(2,BigDecimal.ROUND_HALF_UP);
                //算和
                sumEnergyElec = sumEnergyElec.add(energyElec);
                sumEnergyGas = sumEnergyGas.add(energyGas);

            }

            if (null != oldEnergyPlanningCost){
                //修改或者删除的时候，原来有数据，只需要更新
                oldEnergyPlanningCost.setEnergyElec(sumEnergyElec);
                oldEnergyPlanningCost.setEnergyGas(sumEnergyGas);
                oldEnergyPlanningCost.setMoneyElec(sumMoneyElec);
                oldEnergyPlanningCost.setMoneyGas(sumMoneyGas);
                energyPlanningCostDao.updateByPrimaryKeySelective(oldEnergyPlanningCost);
            }else {
                //新增的时候，原来没有数据，需要新增
                EnergyPlanningCost newEnergyPlanningCost = new EnergyPlanningCost();
                newEnergyPlanningCost.setId(UuidUtil.create32());
                newEnergyPlanningCost.setCreateTime(planningRecord.getCreateTime());
                newEnergyPlanningCost.setDeviceId(planningRecord.getDeviceId());
                newEnergyPlanningCost.setProjectId(planningRecord.getProjectId());
                newEnergyPlanningCost.setEnergyElec(sumEnergyElec);
                newEnergyPlanningCost.setEnergyGas(sumEnergyGas);
                newEnergyPlanningCost.setMoneyElec(sumMoneyElec);
                newEnergyPlanningCost.setMoneyGas(sumMoneyGas);
                energyPlanningCostDao.insert(newEnergyPlanningCost);
            }
        }else {//删除之后没有计划编制记录了，则表示当天只有那一条计划编制记录，则删除计划能耗花费
            energyPlanningCostDao.deleteByPrimaryKey(oldEnergyPlanningCost.getId());
        }

    }

}
