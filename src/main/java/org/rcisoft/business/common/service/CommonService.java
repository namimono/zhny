package org.rcisoft.business.common.service;

import org.rcisoft.business.common.entity.DeviceParam;
import org.rcisoft.entity.*;

import java.util.List;

/**
 * Created by JiChao on 2019/3/26.
 * 公共接口
 */
public interface CommonService {

    /**
     * 根据项目id，查询当前项目下所有的设备类型
     * @param projectId
     * @return
     */
    List<BusDeviceType> queryDeviceType(String projectId, String systemId);

    /**
     * 查询项目拥有的系统类型
     * @param projectId
     * @return
     */
    List<SysSystem> querySystemForProject(String projectId);

    /**
     * 查询项目下所有设备
     * @param projectId
     * @param deviceTypeId
     * @param systemId
     * @return
     */
    List<BusDevice> queryDevices(String projectId, String systemId, String deviceTypeId);

    /**
     * 查询设备所有的一级参数
     * @param deviceId
     * @param sourceId
     * @return
     */
    List<BusParamFirst> queryParamFirsts(String deviceId, String sourceId);

    /**
     * 查询一级参数的二级参数
     * @param paramFirstId
     * @return
     */
    List<BusParamSecond> queryParamSeconds(String paramFirstId);

    /**
     * 查询设备、一级、二级
     * @param projectId
     * @param systemId
     * @return
     */
    List<DeviceParam> queryDeviceAndParam(String projectId, String systemId);

    /**
     * 删除一二级参数相关表的记录
     * @param paramFirstIds
     * @param paramSecondIds
     * @param deviceId
     * @return
     */
    void deleteFirstAndSecondTable(String paramFirstIds, String paramSecondIds, String deviceId);

}
