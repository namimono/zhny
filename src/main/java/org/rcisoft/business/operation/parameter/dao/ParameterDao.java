package org.rcisoft.business.operation.parameter.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/2 16:46
 **/
@Repository
public interface ParameterDao{

    /**
     * 查询设备简要信息（根据系统ID）
     */
    @Select("SELECT a.id as 'deviceId',a.name as 'deviceName',a.info,a.location,a.model,a.install_time as 'installTime',\n" +
            "b.name AS 'type',c.name AS 'factoryName',d.name as 'systemName'\n" +
            "FROM bus_device a,bus_type_first b,bus_factory c,sys_system d\n" +
            "where a.system_id = #{systemId} AND a.project_id = #{projectId} and a.type_first_id = #{typeFirstId}\n" +
            "AND a.type_first_id = b.id AND a.factory_id = c.id and a.system_id = d.id")
    @ResultType(DeviceBriefInfo.class)
    List<DeviceBriefInfo> queryDeviceBriefBySys(@Param("systemId") String systemId,@Param("projectId") String projectId,@Param("typeFirstId") String typeFirstId);

    /**
     * 查询设备简要信息（根据系统ID）
     */
    @Select("SELECT a.id as 'deviceId',a.name as 'deviceName',a.info,a.location,a.model,a.install_time as 'installTime',\n" +
            "b.name AS 'type',c.name AS 'factoryName',d.name as 'systemName'\n" +
            "FROM bus_device a,bus_type_first b,bus_factory c,sys_system d\n" +
            "where a.project_id = #{projectId} and a.type_first_id = #{typeFirstId}\n" +
            "AND a.type_first_id = b.id AND a.factory_id = c.id and a.system_id = d.id")
    @ResultType(DeviceBriefInfo.class)
    List<DeviceBriefInfo> queryDeviceBriefByType(@Param("projectId") String projectId,@Param("typeFirstId") String typeFirstId);

}
