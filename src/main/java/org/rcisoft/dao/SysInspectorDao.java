package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.wechat.entity.DeviceDetailed;
import org.rcisoft.entity.SysInspector;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:24
 **/
@Repository
public interface SysInspectorDao extends Mapper<SysInspector> {

    /**
     * 获取巡查员信息
     */
    @Select("SELECT * FROM sys_inspector;")
    @ResultType(SysInspector.class)
    List<SysInspector> queryInspectorInfo();

    /**
     * 查询设备信息
     * @param deviceId
     * @return DeviceDetail
     */
    @Select("<script>" +
            "SELECT bd.`name` as deviceName, bdt.`name` as deviceTypeName, bd.project_id, bd.id as deviceId \n" +
            "FROM bus_device bd LEFT JOIN bus_device_type bdt ON bd.device_type_id = bdt.id \n" +
            "WHERE bd.id = #{deviceId}" +
            "</script>")
    DeviceDetailed getDeviceDetailById(@Param("deviceId") String deviceId);

    /**
     * 查询出巡检员
     * @param ids
     * @return List<SysInspector>
     */
    @Select("<script>" +
            "select * from sys_inspector where id in " +
            " <foreach collection=\"ids\" index=\"index\" item=\"id\" open=\"(\" separator=\",\" close=\")\"> " +
            "   #{id} " +
            "  </foreach> " +
            "</script>")
    List<SysInspector> listSysInspectorByIds(@Param("ids") List<String> ids);

}
