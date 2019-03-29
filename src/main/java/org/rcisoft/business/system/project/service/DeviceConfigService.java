package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.ParamFirstContainSecond;
import org.rcisoft.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:12
 **/

public interface DeviceConfigService {

    /**
     * 新增设备配置信息
     */
    int addDeviceConfigInfo(BusDevice busDevice);

    /**
     * 删除设备信息(谨慎!)
     */
    int deleteDevice(String deviceId);

    /**
     * 修改设备信息
     */
    int updateDevice(BusDevice busDevice);

    /**
     * 根据设备ID查询设备信息
     */
    List<BusDevice> queryDeviceInfo(String deviceId);

    /**
     * 查询设备简要信息（设备配置）
     */
    List<DeviceBriefInfo> queryDeviceBriefInfo(String systemId,String projectId);

    /**
     * 查询系统类型
     */
    List<SysSystem> querySystem();

    /**
     * 查询参数来源
     */
    List<SysSource> querySource();

    /**
     * 查询能耗分类
     */
    List<EnergyType> queryEnergyType();

    /**
     * 处理一、二级设备类型下拉菜单级联格式
     */
    List<Map<String,Object>> processTypeFormat(String systemId);

    /**
     * 删除一级参数信息
     */
    int deleteParamFirst(String paramFirstId);

    /**
     * 删除二级参数信息
     */
    int deleteParamSecond(String paramSecondId);

    /**
     * 修改一级参数信息
     */
    int updateParamFirst(BusParamFirst busParamFirst);

    /**
     * 修改二级参数信息
     */
    int updateParamSecond(BusParamSecond busParamSecond);

    /**
     * 新增一二级参数信息
     */
    int addParamInfo(BusParamFirst busParamFirst,BusParamSecond busParamSecond);

    /**
     * 新增二级参数信息
     */
    int addParamSecond(BusParamSecond busParamSecond);

    /**
     * 查询一二级参数信息
     */
    List<ParamFirstContainSecond> queryParamInfo(String deviceId);

}
