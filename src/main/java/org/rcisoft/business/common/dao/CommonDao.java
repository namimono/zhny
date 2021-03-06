package org.rcisoft.business.common.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.common.entity.DeviceParam;
import org.rcisoft.business.common.entity.FirstParam;
import org.rcisoft.business.common.entity.SecondParam;
import org.rcisoft.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/26.
 * 公共接口
 */
@Repository
public interface CommonDao {

    /**
     * 查询本项目中所有存在的一级设备类型
     * @param projectId
     * @return
     */
    @Select("<script>select t.* from bus_device d, bus_device_type t where d.device_type_id = t.id and d.project_id = #{projectId}" +
            "<if test = \"systemId != null\"> and d.system_id = #{systemId}</if>" +
            " group by t.id</script>")
    @ResultType(BusDeviceType.class)
    List<BusDeviceType> queryDeviceType(@Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 查询项目中的子系统字段
     * @param projectId
     * @return
     */
    @Select("<script>select system_ids from bus_project where id = #{projectId}</script>")
    @ResultType(String.class)
    String querySystemIds(@Param("projectId") String projectId);

    /**
     * 查询项目下的所有设备
     * @param projectId
     * @param systemId
     * @param deviceTypeId
     * @return
     */
    @Select("<script>select id, name from bus_device where project_id = #{projectId}<if test = \"systemId != null\"> and system_id = #{systemId}</if><if test = \"deviceTypeId != null\"> and device_type_id = #{deviceTypeId}</if></script>")
    @ResultType(BusDevice.class)
    List<BusDevice> queryDevices(@Param("projectId") String projectId, @Param("systemId") String systemId, @Param("deviceTypeId") String deviceTypeId);

    /**
     * 查询设备下所有的一级参数
     * @param deviceId
     * @return
     */
    @Select("<script>select id, name, coding from bus_param_first where device_id = #{deviceId} <if test = \"sourceId != null\">and source_id = #{sourceId}</if> order by source_id asc</script>")
    @ResultType(BusParamFirst.class)
    List<BusParamFirst> queryParamFirsts(@Param("deviceId") String deviceId, @Param("sourceId") String sourceId);

    /**
     * 查询一级参数的二级参数
     * @param paramFirstId
     * @return
     */
    @Select("<script>select id, name, coding from bus_param_second where param_first_id = #{paramFirstId}</script>")
    @ResultType(BusParamSecond.class)
    List<BusParamSecond> queryParamSeconds(@Param("paramFirstId") String paramFirstId);

    /**
     * 根据项目id，系统id查询所有设备
     * @param projectId
     * @param systemId
     * @return
     */
    @Select("<script>select id deviceId, name deviceName from bus_device where project_id = #{projectId}<if test=\"systemId != null\"> and system_id = #{systemId}</if></script>")
    @ResultType(DeviceParam.class)
    List<DeviceParam> queryDeviceParam(@Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 根据项目id，系统id查询所有一级参数
     * @param projectId
     * @param systemId
     * @return
     */
    @Select("<script>select id paramFirstId, name paramFirstName, coding paramFirstCode, device_id deviceId from bus_param_first where project_id = #{projectId}<if test=\"systemId != null\"> and system_id = #{systemId}</if></script>")
    @ResultType(FirstParam.class)
    List<FirstParam> queryFirstParam(@Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 根据项目id，系统id查询所有二级参数
     * @param projectId
     * @param systemId
     * @return
     */
    @Select("<script>select id paramSecondId, name paramSecondName, coding paramSecondCode, param_first_id paramFirstId from bus_param_second where project_id = #{projectId}<if test=\"systemId != null\"> and system_id = #{systemId}</if></script>")
    @ResultType(SecondParam.class)
    List<SecondParam> querySecondParam(@Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 根据设备id删除相关表信息
     * @param deviceId
     * @return
     */
    @Delete("<script>" +
            "delete from energy_param_library where device_id = #{deviceId};" +
            "delete from energy_planning_cost where device_id = #{deviceId};" +
            "delete from energy_planning_record where device_id = #{deviceId};" +
            "delete from energy_planning_device where device_id = #{deviceId};" +
            "</script>")
    int deleteParamByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 根据一级参数id删除相关表
     * @param paramFirstIds
     * @return
     */
    @Delete("<script>" +
            "<foreach item=\"item\" collection=\"array\">" +
            "delete from bus_indoor_param where param_first_id = #{item};" +
            "delete from bus_outdoor where param_first_id = #{item};" +
            "delete from bus_title_param where param_first_id = #{item};" +
            "delete from bus_variable where param_first_id = #{item};" +
            "</foreach>" +
            "</script>")
    int deleteParamByFirstId(String[] paramFirstIds);

    /**
     * 根据二级参数id删除相关表
     * @param paramSecondIds
     * @return
     */
    @Delete("<script>" +
            "<foreach item=\"item\" collection=\"array\">" +
            "delete from bus_indoor_param where param_second_id = #{item};" +
            "delete from bus_outdoor where param_second_id = #{item};" +
            "delete from bus_title_param where param_second_id = #{item};" +
            "delete from bus_variable where param_second_id = #{item};" +
            "</foreach>" +
            "</script>")
    int deleteParamBySecondId(String[] paramSecondIds);

//    /**
//     * 删除公式表相关数据
//     * @param paramFirstIds
//     * @return
//     */
//    @Delete("<script>" +
//            "<foreach item=\"item\" collection=\"array\">" +
//            "delete from bus_formula " +
//            "where id = (select formula from bus_variable where " +
//            "param_first_id = #{item});" +
//            "</foreach>" +
//            "</script>")
//    int deleteFormulaByFirstId(String[] paramFirstIds);
//
//    @Delete("<script>" +
//            "<foreach item=\"item\" collection=\"array\">" +
//            "delete from bus_formula " +
//            "where id = (select formula from bus_variable where " +
//            "param_second_id = #{item});" +
//            "</foreach>" +
//            "</script>")
//    int deleteFormulaBySecondId(String[] paramSecondIds);
}
