package org.rcisoft.business.operation.establishment.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.DevicePlanningFromDb;
import org.rcisoft.business.operation.establishment.entity.ParameterNameId;
import org.rcisoft.business.operation.establishment.entity.PlanList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/18
 */
@Repository
public interface DevicePlanningRepository {

    /**
     * 根据时间与项目Id查出当前项目的计划编制需要用到的信息
     *
     * @param conditionDto
     * @return
     */
    @Select("<script>SELECT epr.device_id as deviceId, bd.`name` as deviceName, bps.`name` as mainName, bps1.`name` as mainName2, " +
            " epr.start_time, epr.end_time, epr.main_value, epr.main_value2\n" +
            "FROM energy_planning_record epr\n" +
            "LEFT JOIN bus_param_second bps ON epr.main_second_id = bps.id\n" +
            "LEFT JOIN bus_param_second bps1 ON epr.main_second_id2 = bps1.id\n" +
            "LEFT JOIN bus_device bd ON epr.device_id = bd.id\n" +
            "WHERE epr.create_time = #{conditionDto.date} AND epr.project_id = #{conditionDto.proId}</script>")
    @ResultType(DevicePlanningFromDb.class)
    List<DevicePlanningFromDb> listDevicePlanningFromDb(@Param("conditionDto") ConditionDto conditionDto);

    /**
     * 根据设备Id查出设备的计划编制信息
     *
     * @param conditionDto
     * @return
     */
    @Select("<script>SELECT epr.id as energyPlanningRecordId, epr.device_id, bp.`name` as deviceName, epr.start_time, epr.end_time  " +
            "  FROM energy_planning_record epr \n" +
            "LEFT JOIN bus_device bp ON epr.device_id = bp.id WHERE epr.device_id = #{conditionDto.devId} AND epr.create_time = #{conditionDto.date} </script>")
    @ResultType(PlanList.class)
    List<PlanList> listPlanList(@Param("conditionDto") ConditionDto conditionDto);


    /**
     * 根据设备Id,与计划编制记录表Id，查出编辑计划列表时用到的信息
     *
     * @param conditionDto
     * @return
     */
    @Select("<script> SELECT epr.start_time, epr.end_time, epr.main_value, epr.main_value2, epr.param_value, epr.param_value2, epr.id AS energyPlanningRecordId \n" +
            " (SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 1 AND bpl.device_id = #{conditionDto.devId} ) AS mainName,\n" +
            " (SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 2 AND bpl.device_id = #{conditionDto.devId} ) AS mainName2,\n" +
            " (SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 3 AND bpl.device_id = #{conditionDto.devId} ) AS paramName,\n" +
            " (SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 4 AND bpl.device_id = #{conditionDto.devId} ) AS paramName2\n" +
            " FROM energy_planning_record epr WHERE epr.id = #{conditionDto.energyPlanningRecordId}</script>")
    @ResultType(DevicePlanningFromDb.class)
    List<DevicePlanningFromDb> listDevicePlanningByDevIdAndRecId(@Param("conditionDto") ConditionDto conditionDto);


    /**
     * 根据设备Id,查出参数的Id，参数的名称
     *
     * @param conditionDto
     * @return
     */
    @Select("<script>SELECT \n" +
            "(SELECT bpl.param_first_id FROM bus_param_library bpl WHERE bpl.sequence = 1 AND bpl.device_id = #{conditionDto.devId} ) AS mainFirstId,\n" +
            "(SELECT bpl.param_second_id FROM bus_param_library bpl WHERE bpl.sequence = 1 AND bpl.device_id = #{conditionDto.devId} ) AS mainSecondId,\n" +
            "(SELECT bpl.param_first_id FROM bus_param_library bpl WHERE bpl.sequence = 2 AND bpl.device_id = #{conditionDto.devId} ) AS mainFirstId2,\n" +
            "(SELECT bpl.param_second_id FROM bus_param_library bpl WHERE bpl.sequence = 2 AND bpl.device_id = #{conditionDto.devId} ) AS mainSecondId2,\n" +
            "(SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 1 AND bpl.device_id = #{conditionDto.devId} ) AS mainName,\n" +
            "(SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 2 AND bpl.device_id = #{conditionDto.devId} ) AS mainName2,\n" +
            "(SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 3 AND bpl.device_id = #{conditionDto.devId} ) AS paramName,\n" +
            "(SELECT bps.`name` FROM bus_param_library bpl LEFT JOIN bus_param_second bps ON bpl.param_second_id = bps.id WHERE bpl.sequence = 4 AND bpl.device_id = #{conditionDto.devId} ) AS paramName2</script>")
    @ResultType(ParameterNameId.class)
    List<ParameterNameId> listDevicePlanningByDevId(@Param("conditionDto") ConditionDto conditionDto);


}
