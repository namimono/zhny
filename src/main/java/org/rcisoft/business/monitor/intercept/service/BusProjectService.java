package org.rcisoft.business.monitor.intercept.service;

import com.alibaba.fastjson.JSONArray;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.business.monitor.intercept.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:01 2019/3/18
 */
public interface BusProjectService {


    /**
     * 查出某个项目下某个设备类型的设备信息
     * @param typeFirstId
     * @param projectId
     * @return List<DeviceInfo>
     */
    List<DeviceInfo> queryDeviceInfo(String typeFirstId,String projectId,String systemId);

    Map<String,Object> EnergyEchart(String titleId);

    /**
     * 查询每个设备中表示电量的参数code
     * @param projectId
     * @param systemId
     * @return
     */
    List<ParamElec> queryDeviceElec(String projectId, String systemId);

    /**
     * 拓扑图，单个设备查询部分参数
     * @param deviceId
     * @param count
     * @return
     */
    ServiceResult queryParams(String deviceId, Integer count);

    /**
     * 查询设备下所有参数信息
     * @param deviceId
     * @return
     */
    ParamsResult queryParamsAll(String deviceId);

    /**
     * 查询项目所属的网关数据
     * @param projectId
     * @return
     */
    JSONArray queryCacheData(String projectId);

}
