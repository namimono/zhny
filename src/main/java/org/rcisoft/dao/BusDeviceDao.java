package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.report.entity.Device;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.entity.BusDevice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:22
 **/
@Repository
public interface BusDeviceDao extends Mapper<BusDevice> {

    /**
     * 根据设备ID查询设备信息
     */
    @Select("SELECT * FROM bus_device where id = #{deviceId};")
    @ResultType(BusDevice.class)
    List<BusDevice> queryDeviceInfo(@Param("deviceId") String deviceId);

    /**
     * 查询设备简要信息（根据系统ID）
     */
    @Select("SELECT a.id as 'deviceId',a.name as 'deviceName',a.info,a.location,a.model,\n" +
            "a.install_time as 'installTime', a.system_id as 'systemId',b.name AS 'type',\n" +
            "c.name AS 'factoryName',d.name as 'systemName' \n" +
            "FROM bus_device a \n" +
            "LEFT JOIN (bus_device_type b,bus_factory c,sys_system d)\n" +
            "ON a.device_type_id = b.id AND a.factory_id = c.id AND a.system_id = d.id \n" +
            "where a.project_id = #{projectId} and a.system_id = #{systemId}")
    @ResultType(DeviceBriefInfo.class)
    List<DeviceBriefInfo> queryDeviceBriefInfo(@Param("systemId") String systemId,@Param("projectId") String projectId);


    /**
     * 查询设备简要信息（根据项目ID）
     */
    @Select("SELECT a.id as 'deviceId',a.name as 'deviceName',a.info,a.location,a.model,\n" +
            "a.install_time as 'installTime', a.system_id as 'systemId',b.name AS 'type',\n" +
            "c.name AS 'factoryName',d.name as 'systemName' \n" +
            "FROM bus_device a \n" +
            "LEFT JOIN (bus_device_type b,bus_factory c,sys_system d)\n" +
            "ON a.device_type_id = b.id AND a.factory_id = c.id AND a.system_id = d.id \n" +
            "where a.project_id = #{projectId}")
    @ResultType(DeviceBriefInfo.class)
    List<DeviceBriefInfo> queryDeviceBriefByProID(@Param("projectId") String projectId);


    /**
     * 根据项目id查询设备
     * @param projectId
     * @return
     */
    @Select("<script>select id, name from bus_device where project_id = #{projectId}</script>")
    @ResultType(BusDevice.class)
    List<BusDevice> queryDeviceByProjectId(@Param("projectId") String projectId);

    @Select("<script>select id, name from bus_device where project_id = #{projectId}</script>")
    @ResultType(Device.class)
    List<Device> queryDeviceByProId(@Param("projectId") String projectId);
}
