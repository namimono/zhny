package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.*;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningService;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.dao.EnergyPlanningDeviceDao;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.entity.EnergyPlanningDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<PlanningDeviceInformation> listDevicePlanningRecord(ProIdAndDate proIdAndDate) {
        //要返回的数据
        List<PlanningDeviceInformation> returnPlanningDeviceInformationList = new ArrayList<>();

        //得到当前时间，当前项目的计划编制设备
        EnergyPlanningDevice energyPlanningDevice = new EnergyPlanningDevice();
        energyPlanningDevice.setCreateTime(proIdAndDate.getDate());
        energyPlanningDevice.setProjectId(proIdAndDate.getProId());
        List<EnergyPlanningDevice> energyPlanningDeviceList = energyPlanningDeviceDao.select(energyPlanningDevice);

        //得到当前时间，当前项目的计划编制的信息
        List<DevicePlanningFromDb> devicePlanningFromDbList = devicePlanningRepository.listDevicePlanningFromDb(proIdAndDate);

        //得到项目下设备的名称
        BusDevice busDevice = new BusDevice();
        busDevice.setProjectId(proIdAndDate.getProId());
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
                //当这个设备有详细的计划编制记录的时候，则正常处理，封装信
                // 息
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
                        if (dev.getId().equals(device.getDeviceId())){
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
    public List<PlanList> listPlanList(String deviceId) {
        return devicePlanningRepository.listPlanList(deviceId);
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
