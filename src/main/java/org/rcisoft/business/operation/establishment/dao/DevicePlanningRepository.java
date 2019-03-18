package org.rcisoft.business.operation.establishment.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.operation.establishment.entity.DevicePlanningFromDb;
import org.rcisoft.business.operation.establishment.entity.PlanList;
import org.rcisoft.business.operation.establishment.entity.ProIdAndDate;
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
     * @param proIdAndDate
     * @return
     */
    @Select("<script>SELECT epr.device_id as deviceId, bd.`name` as deviceName, bps.`name` as mainName, bps1.`name` as mainName2, " +
            " epr.start_time, epr.end_time, epr.main_value, epr.main_value2\n" +
            "FROM energy_planning_record epr\n" +
            "LEFT JOIN bus_param_second bps ON epr.main_second_id = bps.id\n" +
            "LEFT JOIN bus_param_second bps1 ON epr.main_second_id2 = bps1.id\n" +
            "LEFT JOIN bus_device bd ON epr.device_id = bd.id\n" +
            "WHERE epr.create_time = #{proIdAndDate.date} AND epr.project_id = #{proIdAndDate.proId}</script>")
    @ResultType(DevicePlanningFromDb.class)
    List<DevicePlanningFromDb> listDevicePlanningFromDb(@Param("proIdAndDate") ProIdAndDate proIdAndDate);

    /**
     *  根据设备Id查出设备的计划编制信息
     * @param deviceId
     * @return
     */
    @Select("<script>SELECT epr.id as energyPlanningRecordId, epr.device_id, bp.`name` as deviceName, epr.start_time, epr.end_time  " +
            "  FROM energy_planning_record epr \n" +
            "LEFT JOIN bus_device bp ON epr.device_id = bp.id WHERE epr.device_id = #{deviceId} </script>")
    @ResultType(PlanList.class)
    List<PlanList> listPlanList(@Param("deviceId") String deviceId);
}
