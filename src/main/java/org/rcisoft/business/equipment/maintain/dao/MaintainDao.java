package org.rcisoft.business.equipment.maintain.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.maintain.entity.MaintainPlanResult;
import org.rcisoft.business.equipment.maintain.entity.ScheduleResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/4/1.
 * 设备维护--养护计划
 */
@Repository
public interface MaintainDao {

    /**
     * 养护日程
     * @param projectId
     * @param year
     * @return
     */
    @Select("<script>select date_format(plan_time, \"%Y-%m-%d\") time, count(1) `count` " +
            "from bus_maintenance " +
            "where project_id = #{projectId} " +
            "and date_format(plan_time, \"%Y\") = #{year} " +
            "group by date_format(plan_time, \"%Y-%m-%d\")</script>")
    @ResultType(ScheduleResult.class)
    List<ScheduleResult> querySchedule(@Param("projectId") String projectId, @Param("year") Integer year);

    /**
     * 当日养护计划列表
     * @param projectId
     * @param time 年月日
     * @return
     */
    @Select("<script>select m.id, m.plan_time time, d.name deviceName, m.principal " +
            "from bus_maintenance m, bus_device d " +
            "where m.device_id = d.id " +
            "and m.project_id = #{projectId} " +
            "and date_format(m.plan_time, \"%Y-%c-%e\") = #{time} " +
            "order by m.plan_time asc</script>")
    @ResultType(MaintainPlanResult.class)
    List<MaintainPlanResult> queryPlan(@Param("projectId") String projectId, @Param("time") String time);

}
