package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
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
     * 根据系统类型查询设备信息
     */
    List<BusDevice> queryDeviceInfo(BusDevice busDevice);

    /**
     * 查询设备简要信息
     */
    List<DeviceBriefInfo> queryDeviceBriefInfo(BusDevice busDevice);

    /**
     * 根据项目ID和子系统ID查询未关联一级参数信息
     */
    List<BusParamFirst> queryParamFirstInfoBySysId(BusParamFirst busParamFirst);

    /**
     * 根据系统类型ID和一级设备类型ID查询二级设备类型信息
     */
    List<TypeFirstAndSecond> queryTypeSecondInfo(String systemId);

    /**
     * 处理一、二级设备类型下拉菜单级联格式
     */
    List<Map<String,Object>> processTypeFormat(String systemId);

    /**
     * 添加固定参数表信息
     */
    int addParamFixed(BusParamFixed busParamFixed);

    /**
     * 根据项目、设备、系统ID查询固定参数信息
     */
    List<BusParamFixed> queryParamFixed(BusParamFixed busParamFixed);

    /**
     * 修改固定参数表信息
     */
    int updateParamFixed(BusParamFixed busParamFixed);

    /**
     * 删除固定参数表信息
     */
    int deleteParamFixed(BusParamFixed busParamFixed);

    /**
     * 增加设备一级参数中间表信息
     */
    int addMidDeviceFirstInfo(MidDeviceParamFirst midDeviceParamFirst);

    /**
     * 删除设备一级参数中间表信息
     */
    int deleteMidDeviceFirstInfo(MidDeviceParamFirst midDeviceParamFirst);

    /**
     * 增加设备二级参数中间表信息
     */
    int addMidDeviceSecondInfo(MidDeviceParamSecond midDeviceParamSecond);

    /**
     * 删除设备二级参数中间表信息
     */
    int deleteMidDeviceSecondInfo(MidDeviceParamSecond midDeviceParamSecond);

    /**
     * 修改设备一级参数中间表信息
     */
    int updateMidDeviceFirstInfo(MidDeviceParamFirst midDeviceParamFirst);

    /**
     * 修改设备二级参数中间表信息
     */
    int updateMidDeviceSecondInfo(MidDeviceParamSecond midDeviceParamSecond);

    /**
     * 删除设备信息(谨慎!)
     */
    String deleteDevice(String deviceId);
}
