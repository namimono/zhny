package org.rcisoft.business.operation.execution.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.rcisoft.base.util.ZhnyUtils;
import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.DevicePlanningFromDb;
import org.rcisoft.business.operation.establishment.entity.DeviceRecordInformation;
import org.rcisoft.business.operation.establishment.entity.PlanningDeviceInformation;
import org.rcisoft.business.operation.execution.dao.PlanExecutionRepository;
import org.rcisoft.business.operation.execution.entity.DateAndNum;
import org.rcisoft.business.operation.execution.entity.MoneySum;
import org.rcisoft.business.operation.execution.service.PlanExecutionService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.rcisoft.base.util.ZhnyUtils.getDayEndTime;
import static org.rcisoft.base.util.ZhnyUtils.getDayStartTime;

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
    @Autowired
    private EnergyPlanningRecordDao energyPlanningRecordDao;
    @Autowired
    private EnergyStatisticsDao energyStatisticsDao;


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
        if (energyPlanningDeviceList.size() > 0) {
            //查出所有一级参数信息
            List<BusParamFirst> busParamFirstList = busParamFirstDao.selectAll();
            //查出所有二级参数信息
            List<BusParamSecond> busParamSecondList = busParamSecondDao.selectAll();

            //项目当天的所有参数原始信息，10分钟一次
            List<SysData> sysDataList = sysDataDao.listSysDataByProIdAndDate(conditionDto);
            Long time = 600000L;
            Map<Long, String> statusMap = ZhnyUtils.groupSysDataByTime(conditionDto.getDate(), sysDataList, time);

            //查出项目当天的计划编制信息
            List<DevicePlanningFromDb> devicePlanningFromDbList = devicePlanningRepository.listDevicePlanningFromDb(conditionDto);

            //对计划编制设备进行遍历，按设备进行区分处理
            for (EnergyPlanningDevice device : energyPlanningDeviceList) {
                //每个设备的公共信息
                PlanningDeviceInformation resultPlanningDeviceInformation = new PlanningDeviceInformation();
                resultPlanningDeviceInformation.setDeviceId(device.getDeviceId());

                //每个设备的详细计划编制信息
                List<DeviceRecordInformation> resultDeviceRecordInformationList = new ArrayList<>();

                //如果当天有详细的计划编制信息，则进行处理
                if (devicePlanningFromDbList.size() > 0) {

                    //把得到的计划编制信息按照设备进行分组
                    Map<Object, List> groupDevicePlanningFromDb = ZhnyUtils.groupListByName(devicePlanningFromDbList, "deviceId");

                    //拿到某个设备的计划编制信息
                    List<DevicePlanningFromDb> devicePlanning = groupDevicePlanningFromDb.get(device.getDeviceId());

                    //如果这个设备存在计划编制信息，则处理
                    if (null != devicePlanning && devicePlanning.size() > 0) {

                        //得到这个设备的第一个主参数一级参数Id,二级Id(一个设备中的这些值都相同，且一定存在)
                        String mainFirstId = devicePlanning.get(0).getMainFirstId();
                        String mainSecondId = devicePlanning.get(0).getMainSecondId();
                        //得到这个设备的第二个主参数一级参数Id,二级Id(一个设备中的这些值都相同，且一定存在)
                        String mainFirstId2 = devicePlanning.get(0).getMainFirstId2();
                        String mainSecondId2 = devicePlanning.get(0).getMainSecondId2();

                        //这个设备的第一个主参数的以及参数Code,二级参数code
                        String mainFirstCode = getFirstCode(busParamFirstList, mainFirstId);
                        String mainSecondCode = getSecondCode(busParamSecondList, mainSecondId);
                        //这个设备的第二个主参数的以及参数Code,二级参数code
                        String mainFirstCode2 = getFirstCode(busParamFirstList, mainFirstId2);
                        String mainSecondCode2 = getSecondCode(busParamSecondList, mainSecondId2);

                        //封装设备的公共信息
                        resultPlanningDeviceInformation.setDeviceName(devicePlanning.get(0).getDeviceName());
                        resultPlanningDeviceInformation.setMainName(devicePlanning.get(0).getMainName());
                        resultPlanningDeviceInformation.setMainName2(devicePlanning.get(0).getMainName2());

                        //当天结束时间
                        long dayEndTime = getDayEndTime(conditionDto.getDate()).getTime();
                        //1天24小时按照10分钟一次对这个设备的数据进行处理
                        for (long dayStartTime = getDayStartTime(conditionDto.getDate()).getTime(); dayStartTime <= dayEndTime; dayStartTime += 600000) {
                            //每个设备每10分钟的详细信息
                            DeviceRecordInformation deviceRecordInformation = new DeviceRecordInformation();

                            for (DevicePlanningFromDb devicePlanningFromDb : devicePlanning) {
                                //当前时间在项目的某个计划编制的时间段内
                                if (dayStartTime >= devicePlanningFromDb.getStartTime().getTime() && dayStartTime < devicePlanningFromDb.getEndTime().getTime()) {
                                    //每10分钟进行一次数据封装
                                    deviceRecordInformation.setMainValue(devicePlanningFromDb.getMainValue());
                                    deviceRecordInformation.setMainValue2(devicePlanningFromDb.getMainValue2());
                                    deviceRecordInformation.setStartTime(devicePlanningFromDb.getStartTime());
                                    deviceRecordInformation.setEndTime(devicePlanningFromDb.getEndTime());
                                    //处理状态
                                    //拿到这个设备当前时间段的原始数据
                                    String dataStr = statusMap.get(dayStartTime);
                                    if (null != dataStr && !"".equals(dataStr)) {
                                        JSONObject dataJson = JSON.parseObject(dataStr);
                                        //第一个主参数的值
                                        String mainParamValue = getParamValue(dataJson, mainFirstCode, mainSecondCode);
                                        //第一个主参数的状态
                                        Integer mainParamStatus = getParamStatus(dataJson, mainFirstCode);
                                        //第二个主参数的值
                                        String mainParamValue2 = getParamValue(dataJson, mainFirstCode2, mainSecondCode2);
                                        ;
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
                    } else {//如果这个设备没有计划编制记录，则特殊处理，拿到设备名称
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

    @Override
    public List<DateAndNum> listMonthPlanNum(ConditionDto conditionDto) {
        return planExecutionRepository.listMonthPlanNum(conditionDto);
    }

    @Override
    public MoneySum getMoneySum(ConditionDto conditionDto) {

        //查出这个项目指定天数的实际能耗
        EnergyStatistics useEnergyStatistics = new EnergyStatistics();
        useEnergyStatistics.setProjectId(conditionDto.getProId());
        //得到当前的年 月 日
        Calendar cal = Calendar.getInstance();
        cal.setTime(conditionDto.getDate());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        useEnergyStatistics.setTimeYear(year);
        useEnergyStatistics.setTimeMonth(month);
        useEnergyStatistics.setTimeDay(day);
        List<EnergyStatistics> energyStatisticsList = energyStatisticsDao.select(useEnergyStatistics);

        if (energyStatisticsList.size() > 0) {
            //实际电费用
            BigDecimal sumMoneyElec = new BigDecimal(0);
            //实际气费用
            BigDecimal sumMoneyGas = new BigDecimal(0);

            //查出项目当天的的计划
            List<EnergyPlanningRecord> energyPlanningRecordList = energyPlanningRecordDao.listEnergyPlanningRecordDaoByProIdAndData(
                    conditionDto.getProId(), new SimpleDateFormat("yyyy-MM-dd").format(conditionDto.getDate()));

            //对今天的计划进行遍历，根据哪段时间有计划，计算出实际的金额
            for (EnergyPlanningRecord energyPlanningRecord : energyPlanningRecordList) {

                //获得开始时间
                Long startTime = energyPlanningRecord.getStartTime().getTime();
                //获得结束时间
                Long endTime = energyPlanningRecord.getEndTime().getTime();

                for (EnergyStatistics energyStatistics : energyStatisticsList){
                    if (energyStatistics.getCreateTime().getTime() >= startTime && energyStatistics.getCreateTime().getTime() < endTime){
                        sumMoneyElec = sumMoneyElec.add(energyStatistics.getMoneyElec());
                        sumMoneyGas = sumMoneyGas.add(energyStatistics.getMoneyGas());
                    }
                }
            }
            MoneySum moneySum = new MoneySum();
            moneySum.setSumMoneyElec(sumMoneyElec);
            moneySum.setSumMoneyGas(sumMoneyGas);
            return moneySum;
        }
        return null;
    }




    /**
     * 得到参数状态
     *
     * @author GaoLiWei
     * @date 10:16 2019/3/22
     **/
    private Integer getStatus(DevicePlanningFromDb devicePlanningFromDb, String mainParamValue, Integer mainParamStatus, String mainParamValue2, Integer mainParamStatus2) {
        //第一个主参数是否存在
        boolean mainFirstFlag = null != mainParamValue && !"".equals(mainParamValue);
        //第二个主参数是否存在
        boolean mainSecondFlag = null != mainParamValue2 && !"".equals(mainParamValue2);

        //只有第一个主参数存在
        if (mainFirstFlag && !mainSecondFlag) {
            //判断参数的状态是否为1，如果是1，则进行参数值对比，如果不是1，则直接返回3
            if (mainParamStatus == 1) {
                //如果原始数据参数值与当前计划参数值相等,则返回1
                if (new BigDecimal(mainParamValue).compareTo(devicePlanningFromDb.getMainValue()) == 0) {
                    return 1;
                }
                //如果原始数据参数值与当前计划参数值不相等,则返回2
                return 2;
            }
            //如果主参数的状态不是1，则返回3
            return 3;

        }

        //两个主参数都存在
        if (mainFirstFlag && mainSecondFlag) {
            //如果两个主参数的状态都是1，则进行参数值对比
            if (mainParamStatus == 1 && mainParamStatus2 == 1) {
                //如果两个主参数的参数值都与计划参数值相同，则返回1
                if (new BigDecimal(mainParamValue).compareTo(devicePlanningFromDb.getMainValue()) == 0 && new BigDecimal(mainParamValue2).compareTo(devicePlanningFromDb.getMainValue2()) == 0) {
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
     *
     * @author GaoLiWei
     * @date 10:16 2019/3/22
     **/
    private String getParamValue(JSONObject dataJson, String paramFirstCode, String paramSecondCode) {
        if (null != paramFirstCode && !"".equals(paramFirstCode) && null != paramSecondCode && !"".equals(paramSecondCode)) {
            return String.valueOf(dataJson.getJSONObject(paramFirstCode).getJSONObject("REG_VAL").get(paramSecondCode));
        }
        return null;
    }


    /**
     * 根据一级参数参数Code,得到参数状态
     *
     * @author GaoLiWei
     * @date 10:16 2019/3/22
     **/
    private Integer getParamStatus(JSONObject dataJson, String paramFirstCode) {
        if (null != paramFirstCode && !"".equals(paramFirstCode)) {
            return (Integer) dataJson.getJSONObject(paramFirstCode).get("status");
        }
        return null;
    }

    /**
     * 根据一级参数Id,查出以及参数code
     *
     * @author GaoLiWei
     * @date 9:34 2019/3/22
     **/
    private String getFirstCode(List<BusParamFirst> paramFirstList, String firstParamId) {

        if (paramFirstList.size() > 0 && null != firstParamId && !"".equals(firstParamId)) {
            for (BusParamFirst busParamFirst : paramFirstList) {
                if (firstParamId.equals(busParamFirst.getId())) {
                    return busParamFirst.getCoding();
                }
            }
        }
        return null;
    }

    /**
     * 根据二级参数Id,查出以及参数code
     *
     * @author GaoLiWei
     * @date 9:34 2019/3/22
     **/
    private String getSecondCode(List<BusParamSecond> busParamSecondList, String secondParamId) {
        if (busParamSecondList.size() > 0 && null != secondParamId && !"".equals(secondParamId)) {
            for (BusParamSecond busParamSecond : busParamSecondList) {
                if (secondParamId.equals(busParamSecond.getId())) {
                    return busParamSecond.getCoding();
                }
            }
        }
        return null;
    }

}
