package org.rcisoft.business.operation.establishment.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.operation.establishment.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/18
 */
@Repository
public interface DevicePlanningRepository {

    /**
     * 根据时间与项目Id查出当前项目的计划编制需要用到的信息，
     *     包括设备名字，两个主参数的名字，设备编制表所有信息
     *
     * @param conditionDto
     * @return
     */
    @Select("<script>SELECT epr.*, bd.`name` as deviceName, bps.`name` as mainName, bps1.`name` as mainName2 " +
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
     *  根据设备Id，查出该设备的计划参数的名称，以及是第几个参数
     * @param conditionDto
     * @return
     */
    @Select("<script>SELECT bps.`name` AS devParamName, bps.first_sign  FROM " +
            " bus_param_second bps  WHERE bps.device_id = #{conditionDto.devId}  </script>")
    List<DeviceNameAndSeq> listDeviceParamNameAndSeqByDevId(@Param("conditionDto") ConditionDto conditionDto);

    /**
     *  根据设备id，查出该设备的计划编制主参数的一级参数Id,二级参数Id
     * @param devId
     * @return
     */
    @Select("<script>SELECT bps.param_first_id, bps.id AS paramSecondId, bps.first_sign  " +
            "  FROM bus_param_second bps WHERE bps.first_sign in (1,2) AND bps.device_id = #{devId}</script>")
    List<DeviceParamIdAndSeq> listDeviceParamIdAndSeqByDevId(@Param("devId") String devId);




}
