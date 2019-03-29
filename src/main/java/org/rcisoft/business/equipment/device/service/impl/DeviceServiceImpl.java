package org.rcisoft.business.equipment.device.service.impl;

import com.github.pagehelper.PageInfo;
import org.rcisoft.base.result.PageUtil;
import org.rcisoft.business.equipment.device.dao.DeviceDao;
import org.rcisoft.business.equipment.device.entity.InspectionResult;
import org.rcisoft.business.equipment.device.entity.DeviceResult;
import org.rcisoft.business.equipment.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JiChao on 2019/3/29.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    /**
     * 路径
     */
    @Value("${location.url}")
    private String url;

    /**
     * 二维码文件夹名称
     */
    @Value("${location.qrcode}")
    private String qrcode;

    @Autowired
    DeviceDao deviceDao;

    @Override
    public List<DeviceResult> queryDevices(String projectId, String typeFirstId) {
        List<DeviceResult> list = deviceDao.queryDevices(projectId, typeFirstId);
        list.forEach(deviceResult -> {
            // localhost:8080/qrcode-img/projectId/qrcode.jpg
            deviceResult.setQrcodeUrl(url + qrcode + "/" + projectId + "/" + deviceResult.getQrcodeUrl());
        });
        return list;
    }

    @Override
    public PageInfo<InspectionResult> queryInspectionForPage(String deviceId, Integer year, Integer month) {
        String time = year + "-" + month;
        return PageUtil.pageResult(deviceDao.queryInspection(deviceId, time));
    }
}
