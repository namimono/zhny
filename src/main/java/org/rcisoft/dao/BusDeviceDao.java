package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
     * 查询排序字段
     */
    @Select("<script>select max(sequence) from bus_device where project_id = #{projectId} and system_id = #{systemId}</script>")
    @ResultType(Integer.class)
    Integer querySeq(@Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 查询设备简要信息（根据系统ID）
     */
    @Select("SELECT a.id as 'deviceId',a.name as 'deviceName',a.info,a.location,a.model," +
            "a.install_time as 'installTime', a.system_id as 'systemId',b.name AS 'type'," +
            "c.name AS 'factoryName',d.name as 'systemName' " +
            "FROM bus_device a " +
            "LEFT JOIN (bus_device_type b,bus_factory c,sys_system d)" +
            "ON a.device_type_id = b.id AND a.factory_id = c.id AND a.system_id = d.id " +
            "where a.project_id = #{projectId} " +
            "<if test=\"systemId != null\">and a.system_id = #{systemId} </if>" +
            "<if test=\"factoryId != null\">and a.factory_id = #{factoryId} </if>" +
            "<if test=\"deviceTypeId != null\">and a.device_type_id = #{deviceTypeId} </if>" +
            "order by a.sequence desc")
    @ResultType(DeviceBriefInfo.class)
    List<DeviceBriefInfo> queryDeviceBriefInfo(@Param("projectId") String projectId, @Param("systemId") String systemId, @Param("factoryId") String factoryId, @Param("deviceTypeId") String deviceTypeId);

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

    /**
     * 重新排序
     * @param list
     * @return
     */
    @Update("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "update bus_device set sequence=#{item.sequence} where id = #{item.id}" +
            "</foreach>" +
            "</script>")
    @ResultType(Integer.class)
    Integer reOrdered(List<BusDevice> list);
}
