package org.rcisoft.business.equipment.device.service;

import com.github.pagehelper.PageInfo;
import org.rcisoft.business.equipment.device.entity.InspectionResult;
import org.rcisoft.business.equipment.device.entity.DeviceResult;

import java.util.List;

/**
 * Created by JiChao on 2019/3/29.
 * 设备维护--设备管理
 */
public interface DeviceService {

    /**
     * 查询设备列表
     * @param projectId
     * @param deviceTypeId
     * @return
     */
    List<DeviceResult> queryDevices(String projectId, String deviceTypeId);

    /**
     * 分页查询巡检记录
     * @param deviceId
     * @param year
     * @param month
     * @return
     */
    PageInfo<InspectionResult> queryInspectionForPage(String deviceId, Integer year, Integer month);

}
