package org.rcisoft.business.system.project.service;

import org.rcisoft.base.result.Result;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.business.system.project.entity.BatchParams;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.ParamFirstContainSecond;
import org.rcisoft.business.system.project.entity.ParamResult;
import org.rcisoft.entity.*;
import org.springframework.web.multipart.MultipartFile;

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
    List<DeviceBriefInfo> queryDeviceBriefInfo(String projectId, String systemId, String factoryId, String deviceTypeId);

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

//    /**
//     * 处理一、二级设备类型下拉菜单级联格式
//     */
//    List<Map<String,Object>> processTypeFormat(String systemId);

//    /**
//     * 删除一级参数信息
//     */
//    int deleteParamFirst(String paramFirstId);
//
//    /**
//     * 删除二级参数信息
//     */
//    int deleteParamSecond(String paramSecondId);

//    /**
//     * 修改一级参数信息
//     */
//    int updateParamFirst(BusParamFirst busParamFirst);

//    /**
//     * 批量更新一级参数
//     */
//    int updateAllParamFirst(List<BusParamFirst> list);

//    /**
//     * 修改二级参数信息
//     */
//    int updateParamSecond(BusParamSecond busParamSecond);

//    /**
//     * 批量更新二级参数
//     */
//    int updateAllParamSecond(List<BusParamSecond> list);

//    /**
//     * 新增一二级参数信息
//     */
//    int addParamInfo(BusParamFirst busParamFirst,BusParamSecond busParamSecond);
//
//    /**
//     * 新增二级参数信息
//     */
//    int addParamSecond(BusParamSecond busParamSecond);

    /**
     * 查询一二级参数信息
     */
    ParamResult queryParamInfo(String deviceId);

    /**
     * 批量增删改一二级参数信息
     */
    Result batchOperationParams(BatchParams batchParams);

    /**
     * 新增厂家信息
     */
    int addFactory(BusFactory busFactory);

    /**
     * 删除厂家信息
     */
    int deleteFactory(String factoryId);

    /**
     * 修改厂家信息
     */
    int updateFactory(BusFactory busFactory);

    /**
     * 查询厂家信息
     */
    List<BusFactory> queryFactory();

    /**
     * 新增设备类型
     */
    int addDeviceType(BusDeviceType busDeviceType);

    /**
     * 删除设备类型
     */
    int deleteDeviceType(String typeId);

    /**
     * 修改设备类型
     */
    int updateDeviceType(BusDeviceType busDeviceType);

    /**
     * 查询设备类型
     */
    List<BusDeviceType> queryDeviceType();

    /**
     * 新增设备图片
     */
    ServiceResult addTypeImage(MultipartFile file,String proId,String name);

    /**
     * 删除设备图片
     */
    int deleteDevicePic(String picId);

    /**
     * 修改设备图片
     */
    ServiceResult updateTypeImage(MultipartFile file,String picId,String name);

    /**
     * 查询设备图片
     */
    List<BusDevicePicture> queryDevicePic(String proId);

    /**
     * 设备重新排序
     * @param list
     * @return
     */
    Integer reOrdered(List<BusDevice> list);

}
