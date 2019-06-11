package org.rcisoft.business.common.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.rcisoft.business.common.dao.CommonDao;
import org.rcisoft.business.common.entity.DeviceParam;
import org.rcisoft.business.common.entity.FirstParam;
import org.rcisoft.business.common.entity.SecondParam;
import org.rcisoft.business.common.service.CommonService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/3/26.
 * 公共接口
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonDao commonDao;
    @Autowired
    SysSystemDao sysSystemDao;

    @Autowired
    BusIndoorParamDao busIndoorParamDao;
    @Autowired
    BusOutdoorDao busOutdoorDao;
    @Autowired
    BusTitleParamDao busTitleParamDao;
    @Autowired
    BusVariableDao busVariableDao;
    @Autowired
    BusFormulaDao busFormulaDao;
    @Autowired
    EnergyParamLibraryDao energyParamLibraryDao;
    @Autowired
    EnergyPlanningCostDao energyPlanningCostDao;
    @Autowired
    EnergyPlanningRecordDao energyPlanningRecordDao;
    @Autowired
    EnergyPlanningDeviceDao energyPlanningDeviceDao;

    @Override
    public List<BusDeviceType> queryDeviceType(String projectId, String systemId) {
        return commonDao.queryDeviceType(projectId,systemId);
    }

    @Override
    public List<SysSystem> querySystemForProject(String projectId) {
        List<SysSystem> resultList = new ArrayList<>();
        List<SysSystem> list = sysSystemDao.selectAll();
        String systemIds = commonDao.querySystemIds(projectId);
        String[] idArrays = systemIds.split(",");
        list.forEach(sysSystem -> {
            for (String s : idArrays) {
                if (s.equals(sysSystem.getId())) {
                    resultList.add(sysSystem);
                }
            }
        });
        return resultList;
    }

    @Override
    public List<BusDevice> queryDevices(String projectId, String systemId, String deviceTypeId) {
        return commonDao.queryDevices(projectId, systemId, deviceTypeId);
    }

    @Override
    public List<BusParamFirst> queryParamFirsts(String deviceId, String sourceId) {
        return commonDao.queryParamFirsts(deviceId, sourceId);
    }

    @Override
    public List<BusParamSecond> queryParamSeconds(String paramFirstId) {
        return commonDao.queryParamSeconds(paramFirstId);
    }

    @Override
    public List<DeviceParam> queryDeviceAndParam(String projectId, String systemId) {
        List<DeviceParam> deviceList = commonDao.queryDeviceParam(projectId, systemId);
        List<FirstParam> firstList = commonDao.queryFirstParam(projectId, systemId);
        List<SecondParam> secondList = commonDao.querySecondParam(projectId, systemId);
        // 循环一级参数，将二级参数放入
        firstList.forEach(firstParam -> {
            String paramFirstId = firstParam.getParamFirstId();
            List<SecondParam> secondList1 = firstParam.getSecondList();
            secondList.forEach(secondParam -> {
                if (StringUtils.equals(secondParam.getParamFirstId(), paramFirstId)) {
                    secondList1.add(secondParam);
                }
            });
        });
        // 循环设备，将一级参数放入
        deviceList.forEach(deviceParam -> {
            String deviceId = deviceParam.getDeviceId();
            List<FirstParam> firstList1 = deviceParam.getFirstList();
            firstList.forEach(firstParam -> {
                if (StringUtils.equals(firstParam.getDeviceId(), deviceId)) {
                    firstList1.add(firstParam);
                }
            });
        });
        return deviceList;
    }

    @Override
    public Integer deleteFirstAndSecondTable(String paramFirstId, String paramSecondId, String deviceId) {
        Integer result = 0;
        if (StringUtils.isNotEmpty(deviceId)) {
            // 根据设备id删除
            result += busIndoorParamDao.deleteByExample(this.setCondition(BusIndoorParam.class, deviceId, null, null));
            result += busOutdoorDao.deleteByExample(this.setCondition(BusOutdoor.class, deviceId, null, null));
            result += busTitleParamDao.deleteByExample(this.setCondition(BusTitleParam.class, deviceId, null, null));
            result += busVariableDao.deleteByExample(this.setCondition(BusVariable.class, deviceId, null, null));
            result += energyParamLibraryDao.deleteByExample(this.setCondition(EnergyParamLibrary.class, deviceId, null, null));
            result += energyPlanningCostDao.deleteByExample(this.setCondition(EnergyPlanningCost.class, deviceId, null, null));
            result += energyPlanningRecordDao.deleteByExample(this.setCondition(EnergyPlanningRecord.class, deviceId, null, null));
            result += energyPlanningDeviceDao.deleteByExample(this.setCondition(EnergyPlanningDevice.class, deviceId, null, null));
        }
//        else {
//            if (StringUtils.isNotEmpty(paramFirstId)) {
//                String[] p1Array = paramFirstId.split(",");
//                for (String firstId : p1Array) {
//                    result += busIndoorParamDao.deleteByExample(this.setCondition(BusIndoorParam.class, null, firstId, null));
//                    result += busOutdoorDao.deleteByExample(this.setCondition(BusOutdoor.class, null, firstId, null));
//                    result += busTitleParamDao.deleteByExample(this.setCondition(BusTitleParam.class, null, firstId, null));
//                    result += busVariableDao.deleteByExample(this.setCondition(BusVariable.class, null, firstId, null));
//                    result += energyParamLibraryDao.deleteByExample(this.setCondition(EnergyParamLibrary.class, null, firstId, null));
//                    result += energyPlanningCostDao.deleteByExample(this.setCondition(EnergyPlanningCost.class, null, firstId, null));
//                    result += energyPlanningRecordDao.deleteByExample(this.setCondition(EnergyPlanningRecord.class, null, firstId, null));
//                    result += energyPlanningDeviceDao.deleteByExample(this.setCondition(EnergyPlanningDevice.class, null, firstId, null));
//                }
//            }
//            if (StringUtils.isNotEmpty(paramSecondId)) {
//                String[] p2Array = paramSecondId.split(",");
//                for (String secondId : p2Array) {
//                    result += busIndoorParamDao.deleteByExample(this.setCondition(BusIndoorParam.class, null, null, secondId));
//                    result += busOutdoorDao.deleteByExample(this.setCondition(BusOutdoor.class, null, null, secondId));
//                    result += busTitleParamDao.deleteByExample(this.setCondition(BusTitleParam.class, null, null, secondId));
//                    result += busVariableDao.deleteByExample(this.setCondition(BusVariable.class, null, null, secondId));
//                    result += energyParamLibraryDao.deleteByExample(this.setCondition(EnergyParamLibrary.class, null, null, secondId));
//                    result += energyPlanningCostDao.deleteByExample(this.setCondition(EnergyPlanningCost.class, null, null, secondId));
//                    result += energyPlanningRecordDao.deleteByExample(this.setCondition(EnergyPlanningRecord.class, null, null, secondId));
//                    result += energyPlanningDeviceDao.deleteByExample(this.setCondition(EnergyPlanningDevice.class, null, null, secondId));
//                }
//            }
//        }
        if (StringUtils.isNotEmpty(paramFirstId)) {
            String[] p1Array = paramFirstId.split(",");
            for (String firstId : p1Array) {
                result += busFormulaDao.deleteFormula(firstId, null);
            }
        }
        if (StringUtils.isNotEmpty(paramSecondId)) {
            String[] p2Array = paramSecondId.split(",");
            for (String secondId : p2Array) {
                result += busFormulaDao.deleteFormula(null, secondId);
            }
        }
        return result;
    }
    
    private Example setCondition(Class clazz, String deviceId, String paramFirstId, String paramSecondId) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        if (deviceId != null) {
            criteria.andEqualTo("deviceId", deviceId);
        }
        if (paramFirstId != null) {
            criteria.andEqualTo("paramFirstId", paramFirstId);
        }
        if (paramSecondId != null) {
            criteria.andEqualTo("paramSecondId", paramSecondId);
        }
        return example;
    }
    
}
