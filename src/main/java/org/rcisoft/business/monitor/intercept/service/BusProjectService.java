package org.rcisoft.business.monitor.intercept.service;

import org.rcisoft.business.monitor.intercept.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:01 2019/3/18
 */
public interface BusProjectService {
    Map<String,Object> queryPhones(String id);
    List<Map<String, Object>> queryParam(String id);
    List<DeviceParam> queryDeviceParam(String deviceId);
    List<String> queryDeviceTitle();
    List<DeviceInfo> queryDeviceInfo(String typeFirstId);
    List<String> queryModelName();
    Map<String,Object> EnergyEchart(String titleId);
    List<EnergyParam> queryEnergyParam(String deviceId);
    List<DeviceFixValue> queryDeviceFixValue(String deviceId);
    List<DeviceDetail> queryDeviceDetail(String deviceId);
    List<String> queryJsonByProId(String ProId);
}