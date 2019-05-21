package org.rcisoft.business.equipment.device.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.device.entity.DeviceResult;
import org.rcisoft.business.equipment.device.entity.InspectionResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/29.
 * 设备维护--设备管理
 */
@Repository
public interface DeviceDao {

    /**
     * 查询设备列表
     * @param projectId
     * @param deviceTypeId
     * @return
     */
    @Select("<script>select d.*, s.name systemName, f.name factoryName, t.name deviceTypeName from bus_device d " +
            "left join sys_system s on d.system_id = s.id " +
            "left join bus_factory f on d.factory_id = f.id " +
            "left join bus_device_type t on d.device_type_id = t.id " +
            "where d.project_id = #{projectId} and d.device_type_id = #{deviceTypeId} " +
            "order by d.system_id asc</script>")
    @ResultType(DeviceResult.class)
    List<DeviceResult> queryDevices(@Param("projectId") String projectId, @Param("deviceTypeId") String deviceTypeId);

    /**
     * 查询巡检记录
     * @param deviceId
     * @param time
     * @return
     */
    @Select("<script>select i.*, s.real_name inspectorName from bus_inspection i " +
            "left join sys_inspector s on i.inspector_id = s.id " +
            "where i.device_id = #{deviceId} and date_format(i.inspector_time, \"%Y-%c\") = #{time}</script>")
    @ResultType(InspectionResult.class)
    List<InspectionResult> queryInspection(@Param("deviceId") String deviceId, @Param("time") String time);

}
