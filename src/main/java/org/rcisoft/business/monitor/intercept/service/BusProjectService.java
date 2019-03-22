package org.rcisoft.business.monitor.intercept.service;

import org.rcisoft.business.monitor.intercept.entity.DeviceInfo;
import org.rcisoft.business.monitor.intercept.entity.DeviceParam;

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
}
