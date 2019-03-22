package org.rcisoft.business.operation.execution.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.DevicePlanningFromDb;
import org.rcisoft.business.operation.establishment.entity.DeviceRecordInformation;
import org.rcisoft.business.operation.establishment.entity.PlanningDeviceInformation;
import org.rcisoft.business.operation.execution.dao.PlanExecutionRepository;
import org.rcisoft.business.operation.execution.service.PlanExecutionService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static org.rcisoft.business.operation.establishment.service.impl.EnergyPlanningServiceImpl.groupDevicePlanningFromDb;

/**
 * @author GaoLiwei
 * @date 2019/3/21
 */
@Service
public class PlanExecutionServiceImpl implements PlanExecutionService {
    @Autowired
    private PlanExecutionRepository planExecutionRepository;
    @Autowired
    private DevicePlanningRepository devicePlanningRepository;
    @Autowired
    private SysDataDao sysDataDao;
    @Autowired
    private BusDeviceDao busDeviceDao;
    @Autowired
    private EnergyPlanningDeviceDao energyPlanningDeviceDao;
    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;


    @Override
    public List<PlanningDeviceInformation> listDevicePlanningInfoAndStatus(ConditionDto conditionDto) {
        //要返回的信息
        List<PlanningDeviceInformation> resultPlanningDeviceInformationList = new ArrayList<>();

        //得到当天计划编制设备
        EnergyPlanningDevice energyPlanningDevice = new EnergyPlanningDevice();
        energyPlanningDevice.setCreateTime(conditionDto.getDate());
        energyPlanningDevice.setProjectId(conditionDto.getProId());
        List<EnergyPlanningDevice> energyPlanningDeviceList = energyPlanningDeviceDao.select(energyPlanningDevice);

        //如果当天有进行计划编制的设备，则进行处理
        if (energyPlanningDeviceList.size() > 0){
            //查出所有一级参数信息
            List<BusParamFirst> busParamFirstList = busParamFirstDao.selectAll();
            //查出所有二级参数信息
            List<BusParamSecond> busParamSecondList = busParamSecondDao.selectAll();

            //项目当天的所有参数原始信息，10分钟一次
            List<SysData> sysDataList = sysDataDao.listSysDataByProIdAndDate(conditionDto);
            Map<Long,String> statusMap = new HashMap<>(144);
            if (sysDataList.size() >0){
                //当天结束时间
                long dayEndTime = getDayEndTime(conditionDto).getTime();
                //1天24小时按照10分钟一次对这个设备当天的原始数据进行处理
                for (long dayStartTime = getDayStartTime(conditionDto).getTime(); dayStartTime<=dayEndTime; dayStartTime+=600000){
                    Map<Long,String> map = new HashMap(1);
                    map.put(dayStartTime,null);
                    for (SysData sysData : sysDataList){
                        if (sysData.getCreateTime().getTime() == dayStartTime){
                            map.put(dayStartTime,sysData.getJson());
                            break;
                        }
                    }
                    statusMap.putAll(map);
                }
            }

            //查出项目当天的计划编制信息
            List<DevicePlanningFromDb> devicePlanningFromDbList = devicePlanningRepository.listDevicePlanningFromDb(conditionDto);

            //对计划编制设备进行遍历，按设备进行区分处理
            for (EnergyPlanningDevice device : energyPlanningDeviceList){
                //每个设备的公共信息
                PlanningDeviceInformation resultPlanningDeviceInformation = new PlanningDeviceInformation();
                resultPlanningDeviceInformation.setDeviceId(device.getDeviceId());

                //每个设备的详细计划编制信息
                List<DeviceRecordInformation> resultDeviceRecordInformationList = new ArrayList<>();

                //如果当天有详细的计划编制信息，则进行处理
                if (devicePlanningFromDbList.size() > 0) {

                    //把得到的计划编制信息按照设备进行分组
                    Map<String, List<DevicePlanningFromDb>> groupDevicePlanningFromDb = groupDevicePlanningFromDb(devicePlanningFromDbList);

                    //拿到某个设备的计划编制信息
                    List<DevicePlanningFromDb> devicePlanning = groupDevicePlanningFromDb.get(device.getDeviceId());

                    //如果这个设备存在计划编制信息，则处理
                    if (null != devicePlanning && devicePlanning.size() >0){

                        //得到这个设备的第一个主参数一级参数Id,二级Id(一个设备中的这些值都相同，且一定存在)
                        String mainFirstId = devicePlanning.get(0).getMainFirstId();
                        String mainSecondId = devicePlanning.get(0).getMainSecondId();
                        //得到这个设备的第二个主参数一级参数Id,二级Id(一个设备中的这些值都相同，且一定存在)
                        String mainFirstId2 = devicePlanning.get(0).getMainFirstId2();
                        String mainSecondId2 = devicePlanning.get(0).getMainSecondId2();

                        //这个设备的第一个主参数的以及参数Code,二级参数code
                        String mainFirstCode = getFirstCode(busParamFirstList,mainFirstId);
                        String mainSecondCode = getSecondCode(busParamSecondList,mainSecondId);
                        //这个设备的第二个主参数的以及参数Code,二级参数code
                        String mainFirstCode2 = getFirstCode(busParamFirstList,mainFirstId2);
                        String mainSecondCode2 = getSecondCode(busParamSecondList,mainSecondId2);

                        //封装设备的公共信息
                        resultPlanningDeviceInformation.setDeviceName(devicePlanning.get(0).getDeviceName());
                        resultPlanningDeviceInformation.setMainName(devicePlanning.get(0).getMainName());
                        resultPlanningDeviceInformation.setMainName2(devicePlanning.get(0).getMainName2());

                        //当天结束时间
                        long dayEndTime = getDayEndTime(conditionDto).getTime();
                        //1天24小时按照10分钟一次对这个设备的数据进行处理
                        for (long dayStartTime = getDayStartTime(conditionDto).getTime(); dayStartTime<=dayEndTime; dayStartTime+=600000){
                            //每个设备每10分钟的详细信息
                            DeviceRecordInformation deviceRecordInformation = new DeviceRecordInformation();

                            for (DevicePlanningFromDb devicePlanningFromDb : devicePlanning) {
                                //当前时间在项目的某个计划编制的时间段内
                                if (dayStartTime >= devicePlanningFromDb.getStartTime().getTime() && dayStartTime < devicePlanningFromDb.getEndTime().getTime()){
                                    //每10分钟进行一次数据封装
                                    deviceRecordInformation.setMainValue(devicePlanningFromDb.getMainValue());
                                    deviceRecordInformation.setMainValue2(devicePlanningFromDb.getMainValue2());
                                    deviceRecordInformation.setStartTime(devicePlanningFromDb.getStartTime());
                                    deviceRecordInformation.setEndTime(devicePlanningFromDb.getEndTime());
                                    //处理状态
                                    //拿到这个设备当前时间段的原始数据
                                    String dataStr = statusMap.get(dayStartTime);
                                    if (null != dataStr && !"".equals(dataStr)){
                                        JSONObject dataJson = JSON.parseObject(dataStr);
                                        //第一个主参数的值
                                        String mainParamValue = getParamValue(dataJson, mainFirstCode, mainSecondCode);
                                        //第一个主参数的状态
                                        Integer mainParamStatus = getParamStatus(dataJson, mainFirstCode);
                                        //第二个主参数的值
                                        String mainParamValue2 = getParamValue(dataJson, mainFirstCode2, mainSecondCode2);;
                                        //第二个主参数的状态
                                        Integer mainParamStatus2 = getParamStatus(dataJson, mainFirstCode2);
                                        deviceRecordInformation.setStatus(getStatus(devicePlanningFromDb, mainParamValue, mainParamStatus, mainParamValue2, mainParamStatus2));
                                    }
                                    break;
                                }
                            }
                            //将封装好的数据放回待返回的数据中
                            resultDeviceRecordInformationList.add(deviceRecordInformation);
                        }
                    }else {//如果这个设备没有计划编制记录，则特殊处理，拿到设备名称
                        //得到项目下设备的名称
                        BusDevice busDevice = new BusDevice();
                        busDevice.setProjectId(conditionDto.getProId());
                        List<BusDevice> busDeviceList = busDeviceDao.select(busDevice);

                        for (BusDevice dev : busDeviceList) {
                            if (dev.getId().equals(device.getDeviceId())) {
                                resultPlanningDeviceInformation.setDeviceName(dev.getName());
                            }
                        }
                    }
                }
                resultPlanningDeviceInformation.setDeviceRecordInformationList(resultDeviceRecordInformationList);
                resultPlanningDeviceInformationList.add(resultPlanningDeviceInformation);
            }
        }

        return resultPlanningDeviceInformationList;
    }


    /**
     * 得到参数状态
     * @author GaoLiWei
     * @date 10:16 2019/3/22
     **/
    private Integer getStatus(DevicePlanningFromDb devicePlanningFromDb, String mainParamValue, Integer mainParamStatus, String mainParamValue2, Integer mainParamStatus2){
        //第一个主参数是否存在
        boolean mainFirstFlag = null != mainParamValue && !"".equals(mainParamValue);
        //第二个主参数是否存在
        boolean mainSecondFlag = null != mainParamValue2 && !"".equals(mainParamValue2);

        //只有第一个主参数存在
        if (mainFirstFlag && !mainSecondFlag){
            //判断参数的状态是否为1，如果是1，则进行参数值对比，如果不是1，则直接返回3
            if (mainParamStatus == 1){
                //如果原始数据参数值与当前计划参数值相等,则返回1
                if (new BigDecimal(mainParamValue).compareTo(devicePlanningFromDb.getMainValue()) == 0){
                    return 1;
                }
                //如果原始数据参数值与当前计划参数值不相等,则返回2
                return 2;
            }
            //如果主参数的状态不是1，则返回3
            return 3;

        }

        //两个主参数都存在
        if (mainFirstFlag && mainSecondFlag){
            //如果两个主参数的状态都是1，则进行参数值对比
            if (mainParamStatus ==1 && mainParamStatus2 == 1){
                //如果两个主参数的参数值都与计划参数值相同，则返回1
                if (new BigDecimal(mainParamValue).compareTo(devicePlanningFromDb.getMainValue()) == 0 && new BigDecimal(mainParamValue2).compareTo(devicePlanningFromDb.getMainValue2()) == 0){
                    return 1;
                }
                //如果两个主参数的参数值都与计划参数值任意一个不同，则返回2
                return 2;
            }
            //如果两个主参数的状态任意一个不是1，则返回3
            return 3;
        }
        return null;
    }

    /**
     * 根据一级参数参数Code，二级参数Code,得到参数值
     * @author GaoLiWei
     * @date 10:16 2019/3/22
     **/
    private String getParamValue(JSONObject dataJson, String paramFirstCode, String paramSecondCode){
        if (null != paramFirstCode && !"".equals(paramFirstCode) && null != paramSecondCode && !"".equals(paramSecondCode)){
           return String.valueOf(dataJson.getJSONObject(paramFirstCode).getJSONObject("REG_VAL").get(paramSecondCode));
        }
        return null;
    }


    /**
     * 根据一级参数参数Code,得到参数状态
     * @author GaoLiWei
     * @date 10:16 2019/3/22
     **/
    private Integer getParamStatus(JSONObject dataJson, String paramFirstCode){
        if (null != paramFirstCode && !"".equals(paramFirstCode) ){
            return (Integer) dataJson.getJSONObject(paramFirstCode).get("status");
        }
        return null;
    }

    /**
     * 根据一级参数Id,查出以及参数code
     * @author GaoLiWei
     * @date 9:34 2019/3/22
     **/
    private String getFirstCode(List<BusParamFirst> paramFirstList, String firstParamId){

        if (paramFirstList.size() > 0 && null != firstParamId && !"".equals(firstParamId)){
            for (BusParamFirst busParamFirst : paramFirstList){
                if (firstParamId.equals(busParamFirst.getId())){
                    return busParamFirst.getCoding();
                }
            }
        }
        return null;
    }

    /**
     * 根据二级参数Id,查出以及参数code
     * @author GaoLiWei
     * @date 9:34 2019/3/22
     **/
    private String getSecondCode(List<BusParamSecond> busParamSecondList, String secondParamId){
        if (busParamSecondList.size() > 0 && null != secondParamId && !"".equals(secondParamId)){
            for (BusParamSecond busParamSecond : busParamSecondList){
                if (secondParamId.equals(busParamSecond.getId())){
                    return busParamSecond.getCoding();
                }
            }
        }
        return null;
    }


    /**
     * 得到当天的开始时间
     *
     * @author GaoLiWei
     * @date 9:35 2019/3/18
     **/
    private static Date getDayStartTime(ConditionDto conditionDto) {
        Calendar dayStart = Calendar.getInstance();
        dayStart.setTime(conditionDto.getDate());
        dayStart.set(Calendar.HOUR, 0);
        dayStart.set(Calendar.MINUTE, 0);
        dayStart.set(Calendar.SECOND, 0);
        dayStart.set(Calendar.MILLISECOND, 0);
        return dayStart.getTime();
    }

    /**
     * 得到当天的结束时间
     *
     * @author GaoLiWei
     * @date 9:35 2019/3/18
     **/
    private static Date getDayEndTime(ConditionDto conditionDto) {
        Calendar dayEnd = Calendar.getInstance();
        dayEnd.setTime(conditionDto.getDate());
        dayEnd.set(Calendar.HOUR, 23);
        dayEnd.set(Calendar.MINUTE, 59);
        dayEnd.set(Calendar.SECOND, 59);
        dayEnd.set(Calendar.MILLISECOND, 999);
        return dayEnd.getTime();
    }
}
