package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.base.util.ZhnyUtils;
import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.*;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningService;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.dao.EnergyPlanningCostDao;
import org.rcisoft.dao.EnergyPlanningDeviceDao;
import org.rcisoft.dao.EnergyPlanningRecordDao;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.entity.EnergyPlanningCost;
import org.rcisoft.entity.EnergyPlanningDevice;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Service
public class EnergyPlanningServiceImpl implements EnergyPlanningService {
    @Autowired
    private EnergyPlanningDeviceDao energyPlanningDeviceDao;
    @Autowired
    private DevicePlanningRepository devicePlanningRepository;
    @Autowired
    private BusDeviceDao busDeviceDao;
    @Autowired
    private EnergyPlanningRecordDao energyPlanningRecordDao;
    @Autowired
    private EnergyPlanningCostDao energyPlanningCostDao;

    @Override
    public List<PlanningDeviceInformation> listDevicePlanningRecord(ConditionDto conditionDto) {
        //要返回的数据
        List<PlanningDeviceInformation> returnPlanningDeviceInformationList = new ArrayList<>();

        //得到当前时间，当前项目的计划编制设备
        List<EnergyPlanningDevice> energyPlanningDeviceList = this.listEnergyPlanningDeviceByDateAndProId(conditionDto, 1);

        //得到当前时间，当前项目的计划编制的信息
        List<DevicePlanningFromDb> devicePlanningFromDbList = devicePlanningRepository.listDevicePlanningFromDb(conditionDto);

        //得到项目下设备的名称
        BusDevice busDevice = new BusDevice();
        busDevice.setProjectId(conditionDto.getProId());
        List<BusDevice> busDeviceList = busDeviceDao.select(busDevice);

        //根据设备Id,对查出来的计划编制信息进行分组
        Map<String, List<DevicePlanningFromDb>> groupDevicePlanningFromDb = groupDevicePlanningFromDb(devicePlanningFromDbList);

        if (energyPlanningDeviceList.size() > 0) {
            //循环计划编制设备，分别对每个设备当天计划进行处理
            for (EnergyPlanningDevice device : energyPlanningDeviceList) {

                //接受返回值的对象
                PlanningDeviceInformation planningDeviceInformation = new PlanningDeviceInformation();
                planningDeviceInformation.setDeviceId(device.getDeviceId());
                List<DeviceRecordInformation> deviceRecordInformationList = new ArrayList<>();

                //得到这个设备当天的计划编制记录
                List<DevicePlanningFromDb> listDevicePlanningFromDb = groupDevicePlanningFromDb.get(device.getDeviceId());
                //当这个设备有详细的计划编制记录的时候，则正常处理，封装信息
                if (null != listDevicePlanningFromDb && listDevicePlanningFromDb.size() > 0) {
                    //对每个时间段的计划编制记录进行处理
                    for (DevicePlanningFromDb record : listDevicePlanningFromDb) {

                        //将要用到的信息封装到PlanningDeviceInformation中,因这些值都相同，多次覆盖无影响
                        planningDeviceInformation.setDeviceName(record.getDeviceName());
                        planningDeviceInformation.setMainName(record.getMainName());
                        planningDeviceInformation.setMainName2(record.getMainName2());

                        //将要用到信息封装到DeviceRecordInformation中
                        DeviceRecordInformation deviceRecordInformation = new DeviceRecordInformation();
                        deviceRecordInformation.setStartTime(record.getStartTime());
                        deviceRecordInformation.setEndTime(record.getEndTime());
                        deviceRecordInformation.setMainValue(record.getMainValue());
                        deviceRecordInformation.setMainValue2(record.getMainValue2());

                        //将对象放到List集合中
                        deviceRecordInformationList.add(deviceRecordInformation);
//                //开始时间毫秒
//                Long startTimeMilliSecond = record.getStartTime().getTime();
//                //结束时间毫秒
//                Long endTimeMilliSecond = record.getEndTime().getTime();
//
//                //当开始时间小于结束时间的时候
//                while (startTimeMilliSecond <= endTimeMilliSecond){
//                    //将要用到信息封装到DeviceRecordInformation中
//                    DeviceRecordInformation deviceRecordInformation = new DeviceRecordInformation();
//                    deviceRecordInformation.setStartTime(startTime);
//                    deviceRecordInformation.setEndTime(endTime);
//                    deviceRecordInformation.setMainValue(record.getMainValue());
//                    deviceRecordInformation.setMainValue2(record.getMainValue2());
//                    //如果是开始时间，则将标志位设置为1，表示这是开始时间，并且设置其他部分信息
//                    if (startTimeMilliSecond == startTime.getTime()){
//                        deviceRecordInformation.setStartEngFlag(1);
//                    }
//                    //如果是结束时间，则将标志位设置为2，表示这是结束时间
//                    if (startTimeMilliSecond == endTime.getTime()){
//                        deviceRecordInformation.setStartEngFlag(2);
//                    }
//                    //时间增加10分钟 1*60*1000*10
//                    startTimeMilliSecond += 600000;
//                }
                    }
                } else {//如果这个设备没有计划编制记录，则特殊处理，拿到设备名称
                    for (BusDevice dev : busDeviceList) {
                        if (dev.getId().equals(device.getDeviceId())) {
                            planningDeviceInformation.setDeviceName(dev.getName());
                        }
                    }
                }

                planningDeviceInformation.setDeviceRecordInformationList(deviceRecordInformationList);
                //放到待返回的list集合中
                returnPlanningDeviceInformationList.add(planningDeviceInformation);
            }
        }
        return returnPlanningDeviceInformationList;
    }

    @Override
    public List<PlanList> listPlanList(ConditionDto conditionDto) {
        return devicePlanningRepository.listPlanList(conditionDto);
    }

    @Override
    public List<DevicePlanningFromDb> listDevicePlanningByDevIdAndRecId(ConditionDto conditionDto) {
        return devicePlanningRepository.listDevicePlanningByDevIdAndRecId(conditionDto);
    }

    @Override
    public List<ParameterNameId> listDevicePlanningByDevId(ConditionDto conditionDto) {
        return devicePlanningRepository.listDevicePlanningByDevId(conditionDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deletePlanTheDayByProIdAndDate(ConditionDto conditionDto) {
        return this.deletePlan(conditionDto, 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer copyPlanning(ConditionDto conditionDto) {
        //得到当前时间的计划能耗花费
        List<EnergyPlanningCost> energyPlanningCostList = this.listEnergyPlanningCostByDateAndProId(conditionDto, 1);
        //得到当前时间的计划编制设备
        List<EnergyPlanningDevice> energyPlanningDeviceList = this.listEnergyPlanningDeviceByDateAndProId(conditionDto, 1);
        //得到当前时间的计划编制记录
        List<EnergyPlanningRecord> energyPlanningRecordList = this.listEnergyPlanningRecordByDateAndProId(conditionDto, 1);
        //删除要复制的那天的数据
        this.deletePlan(conditionDto, 2);

        //为计划能耗花费替换主键
        Map<String,Object> map = new HashMap<>(1);
        map.put("createTime",conditionDto.getCopyToDate());
        List<EnergyPlanningCost> newEnergyPlanningCostList = (List<EnergyPlanningCost>) ZhnyUtils.replaceId(energyPlanningCostList, "id", map);
        //为计划编制设备替换主键
        List<EnergyPlanningDevice> newEnergyPlanningDeviceList = (List<EnergyPlanningDevice>) ZhnyUtils.replaceId(energyPlanningDeviceList, "id", map);
        //为计划编制记录替换主键
        List<EnergyPlanningRecord> newEnergyPlanningRecordList = (List<EnergyPlanningRecord>) ZhnyUtils.replaceId(energyPlanningRecordList, "id", map);

        Integer newEnergyPlanningCostListFlag = energyPlanningCostDao.saveListEnergyPlanningCost(newEnergyPlanningCostList);
        Integer newEnergyPlanningDeviceListFlag = energyPlanningDeviceDao.saveListEnergyPlanningDevice(newEnergyPlanningDeviceList);
        Integer newEnergyPlanningRecordListFlag = energyPlanningRecordDao.saveListEnergyPlanningRecord(newEnergyPlanningRecordList);

        if (newEnergyPlanningCostListFlag != 0 || newEnergyPlanningDeviceListFlag != 0 || newEnergyPlanningRecordListFlag != 0){
            return 1;
        }
        return 0;
    }


    /**
     * 根据时间与项目Id,删除某一天的计划编制信息
     * flag是1的时候，表示时间为指定的某个时间，为2的时候表示复制当天计划的时候要复制到的某个时间
     *
     * @author GaoLiWei
     * @date 10:13 2019/3/20
     **/
    private Integer deletePlan(ConditionDto conditionDto, int flag) {

        //flag是1，则时间为指定时间，即为date属性
        if (flag == 1) {
            //删除计划编制设备
            EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
            energyPlanningRecord.setCreateTime(conditionDto.getDate());
            energyPlanningRecord.setProjectId(conditionDto.getProId());
            int deleteEnergyPlanningRecord = energyPlanningRecordDao.delete(energyPlanningRecord);
            //删除计划编制记录
            EnergyPlanningDevice energyPlanningDevice = new EnergyPlanningDevice();
            energyPlanningDevice.setCreateTime(conditionDto.getDate());
            energyPlanningDevice.setProjectId(conditionDto.getProId());
            int deleteEnergyPlanningDevice = energyPlanningDeviceDao.delete(energyPlanningDevice);
            //删除计划能耗花费
            EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
            energyPlanningCost.setCreateTime(conditionDto.getDate());
            energyPlanningCost.setProjectId(conditionDto.getProId());
            int deleteEnergyPlanningCostDao = energyPlanningCostDao.delete(energyPlanningCost);
            if (0 != deleteEnergyPlanningRecord || 0 != deleteEnergyPlanningDevice || 0 != deleteEnergyPlanningCostDao) {
                return 1;
            }
            return 0;
        }
        //为2的时候表示复制当天计划的时候要复制到的某个时间，即为copyToDate属性
        //删除计划编制设备
        EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
        energyPlanningRecord.setCreateTime(conditionDto.getCopyToDate());
        energyPlanningRecord.setProjectId(conditionDto.getProId());
        int deleteEnergyPlanningRecord = energyPlanningRecordDao.delete(energyPlanningRecord);
        //删除计划编制记录
        EnergyPlanningDevice energyPlanningDevice = new EnergyPlanningDevice();
        energyPlanningDevice.setCreateTime(conditionDto.getCopyToDate());
        energyPlanningDevice.setProjectId(conditionDto.getProId());
        int deleteEnergyPlanningDevice = energyPlanningDeviceDao.delete(energyPlanningDevice);
        //删除计划能耗花费
        EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
        energyPlanningCost.setCreateTime(conditionDto.getCopyToDate());
        energyPlanningCost.setProjectId(conditionDto.getProId());
        int deleteEnergyPlanningCostDao = energyPlanningCostDao.delete(energyPlanningCost);
        if (0 != deleteEnergyPlanningRecord || 0 != deleteEnergyPlanningDevice || 0 != deleteEnergyPlanningCostDao) {
            return 1;
        }
        return 0;

    }


    /**
     * 根据时间与项目Id,查出某一天的计划编制设备
     * flag是1的时候，表示时间为指定的某个时间，为2的时候表示复制当天计划的时候要复制到的某个时间
     *
     * @author GaoLiWei
     * @date 10:18 2019/3/20
     **/
    private List<EnergyPlanningDevice> listEnergyPlanningDeviceByDateAndProId(ConditionDto conditionDto, int flag) {
        //flag是1，则时间为指定时间，即为date属性
        if (flag == 1) {
            EnergyPlanningDevice energyPlanningDevice = new EnergyPlanningDevice();
            energyPlanningDevice.setCreateTime(conditionDto.getDate());
            energyPlanningDevice.setProjectId(conditionDto.getProId());
            return energyPlanningDeviceDao.select(energyPlanningDevice);
        }
        //为2的时候表示复制当天计划的时候要复制到的某个时间，即为copyToDate属性
        EnergyPlanningDevice energyPlanningDevice = new EnergyPlanningDevice();
        energyPlanningDevice.setCreateTime(conditionDto.getCopyToDate());
        energyPlanningDevice.setProjectId(conditionDto.getProId());
        return energyPlanningDeviceDao.select(energyPlanningDevice);
    }

    /**
     * 根据时间与项目Id,查出某一天的计划编制记录
     * flag是1的时候，表示时间为指定的某个时间，为2的时候表示复制当天计划的时候要复制到的某个时间
     *
     * @author GaoLiWei
     * @date 10:18 2019/3/20
     **/
    private List<EnergyPlanningRecord> listEnergyPlanningRecordByDateAndProId(ConditionDto conditionDto, int flag) {
        //flag是1，则时间为指定时间，即为date属性
        if (flag == 1) {
            EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
            energyPlanningRecord.setProjectId(conditionDto.getProId());
            energyPlanningRecord.setCreateTime(conditionDto.getDate());
            return energyPlanningRecordDao.select(energyPlanningRecord);
        }
        //为2的时候表示复制当天计划的时候要复制到的某个时间，即为copyToDate属性
        EnergyPlanningRecord energyPlanningRecord = new EnergyPlanningRecord();
        energyPlanningRecord.setProjectId(conditionDto.getProId());
        energyPlanningRecord.setCreateTime(conditionDto.getCopyToDate());
        return energyPlanningRecordDao.select(energyPlanningRecord);
    }


    /**
     * 根据时间与项目Id,查出某一天的计划能耗花费
     * flag是1的时候，表示时间为指定的某个时间，为2的时候表示复制当天计划的时候要复制到的某个时间
     *
     * @author GaoLiWei
     * @date 11:29 2019/3/20
     **/
    private List<EnergyPlanningCost> listEnergyPlanningCostByDateAndProId(ConditionDto conditionDto, int flag) {
        //flag是1，则时间为指定时间，即为date属性
        if (flag == 1) {
            EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
            energyPlanningCost.setProjectId(conditionDto.getProId());
            energyPlanningCost.setCreateTime(conditionDto.getDate());
            return energyPlanningCostDao.select(energyPlanningCost);
        }
        //为2的时候表示复制当天计划的时候要复制到的某个时间，即为copyToDate属性
        EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
        energyPlanningCost.setProjectId(conditionDto.getProId());
        energyPlanningCost.setCreateTime(conditionDto.getCopyToDate());
        return energyPlanningCostDao.select(energyPlanningCost);

    }


    /**
     * 得到当天的开始时间
     *
     * @author GaoLiWei
     * @date 9:35 2019/3/18
     **/
    private static Date getDayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 得到当天的结束时间
     *
     * @author GaoLiWei
     * @date 9:35 2019/3/18
     **/
    private static Date getDayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 将查出的所有EnergyPlanningRecord数据，根据他的设备Id进行分组
     *
     * @author GaoLiWei
     * @date 10:57 2019/3/8
     **/
    private Map<String, List<DevicePlanningFromDb>> groupDevicePlanningFromDb(List<DevicePlanningFromDb> energyPlanningDeviceList) {
        Map<String, List<DevicePlanningFromDb>> resultMap = new HashMap<>();
        if (energyPlanningDeviceList.size() > 0) {
            for (DevicePlanningFromDb energyPlanningDevice : energyPlanningDeviceList) {
                //如果map中已经存在了key，则直接将这个数据归到这个分组下
                if (resultMap.containsKey(energyPlanningDevice.getDeviceId())) {
                    resultMap.get(energyPlanningDevice.getDeviceId()).add(energyPlanningDevice);
                } else {//如果这个设备ID不再map的key中，则说明没有这个设备id的分组，新建一个分组，将数据放进去
                    List<DevicePlanningFromDb> list = new ArrayList<>();
                    list.add(energyPlanningDevice);
                    resultMap.put(energyPlanningDevice.getDeviceId(), list);
                }
            }
        }
        return resultMap;
    }
}
