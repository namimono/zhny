package org.rcisoft.business.common.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.rcisoft.business.common.dao.CommonDao;
import org.rcisoft.business.common.entity.DeviceParam;
import org.rcisoft.business.common.entity.FirstParam;
import org.rcisoft.business.common.entity.SecondParam;
import org.rcisoft.business.common.service.CommonService;
import org.rcisoft.dao.SysSystemDao;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BusDeviceType> queryDeviceType(String projectId) {
        return commonDao.queryDeviceType(projectId);
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
        deviceList.forEach(deviceParam -> {
            String deviceId = deviceParam.getDeviceId();
            List<FirstParam> firstList1 = deviceParam.getFirstList();
            firstList.forEach(firstParam -> {
                String paramFirstId = firstParam.getParamFirstId();
                List<SecondParam> secondList1 = firstParam.getSecondList();
                secondList.forEach(secondParam -> {
                    if (StringUtils.equals(secondParam.getParamFirstId(), paramFirstId)) {
                        secondList1.add(secondParam);
                    }
                });
                if (StringUtils.equals(firstParam.getDeviceId(), deviceId)) {
                    firstList1.add(firstParam);
                }
            });
        });
        return deviceList;
    }
}
