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
    public void deleteFirstAndSecondTable(String paramFirstIds, String paramSecondIds, String deviceId) {
        String[] firstArray = null;
        String[] secondArray = null;
        if (StringUtils.isNotEmpty(paramFirstIds)) {
            firstArray = paramFirstIds.split(",");
        }
        if (StringUtils.isNotEmpty(paramSecondIds)) {
            secondArray = paramSecondIds.split(",");
        }

        // 删除公式表
//        if (firstArray != null) {
//            result += commonDao.deleteFormulaByFirstId(firstArray);
//        }
//        if (secondArray != null) {
//            result += commonDao.deleteFormulaBySecondId(secondArray);
//        }
        if (StringUtils.isNotEmpty(deviceId)) {
            // 设备id不为空的时候
            commonDao.deleteParamByDeviceId(deviceId);
        } else {
            if (firstArray != null) {
                commonDao.deleteParamByFirstId(firstArray);
            }
            if (secondArray != null) {
                commonDao.deleteParamBySecondId(secondArray);
            }
        }
    }
    
}
