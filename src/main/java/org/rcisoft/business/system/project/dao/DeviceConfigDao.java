package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.StatementType;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/20 9:58
 **/
@Repository
public interface DeviceConfigDao {

    /**
     * 删除设备信息（谨慎！）
     *
     */
    @Options(statementType = StatementType.CALLABLE)
    @Select("call delete_AllByDevId(#{deviceId})")
    int deleteAllByDevId(@Param("deviceId") String deviceId);

    /**
     * 批量更新一级参数
     */
    @Update("<script><foreach collection=\"list\" item=\"list\" index=\"index\" open=\"\" close=\"\" separator=\";\">\n" +
            "UPDATE bus_param_first SET source_id = #{list.sourceId},`name` = #{list.name}," +
            "coding = #{list.coding} WHERE id = #{list.id}\n" +
            "</foreach></script>")
    int updateAllParamFirst(List<BusParamFirst> list);

    /**
     * 批量更新二级参数
     */
    @Update("<script><foreach collection=\"list\" item=\"list\" index=\"index\" open=\"\" close=\"\" separator=\";\">\n" +
            "UPDATE bus_param_second SET param_first_id = #{list.paramFirstId},project_id = #{list.projectId},\n" +
            "system_id = #{list.systemId},device_id = #{list.deviceId},source_id = #{list.sourceId},`name` = #{list.name},\n" +
            "coding = #{list.coding},unit = #{list.unit},`value` = #{list.value},sequence = #{list.sequence},\n" +
            "fault_status = #{list.faultStatus},min_value = #{list.minValue},max_value = #{list.maxValue},\n" +
            "content = #{list.content},energy_type_id = #{list.energyTypeId},elec_type = #{list.elecType},\n" +
            "first_sign = #{list.firstSign} WHERE id = #{list.id}</foreach></script>\n")
    int updateAllParamSecond(List<BusParamSecond> list);

    /**
     * 查询一级参数名称或code相同的条数
     */
    @Select("SELECT COUNT(*) FROM bus_param_first WHERE (coding = #{paramCode} OR `name` = #{paramName})\n" +
            "and project_id = #{proId} and device_id = #{deviceId}")
    int queryRepeatNum(@Param("paramName") String paramName, @Param("paramCode")String paramCode, @Param("proId")String proId, @Param("deviceId") String deviceId);
}
