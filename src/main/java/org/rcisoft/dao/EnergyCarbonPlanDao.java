package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.rcisoft.entity.EnergyCarbonPlan;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/6/5
 */
@Repository
public interface EnergyCarbonPlanDao extends Mapper<EnergyCarbonPlan> {

    /**
     *  批量增加EnergyCarbonPlan
     * @param energyCarbonPlanList
     * @return Integer
     */
    @Insert("<script><foreach collection=\"energyCarbonPlanList\" item=\"item\" separator=\";\">" +
            "insert into energy_carbon_plan(id, project_id, time_month, time_day, carbon)" +
            "values(#{item.id}, #{item.projectId},#{item.timeMonth}, #{item.timeDay}, #{item.carbon}) " +
            "</foreach></script>")
    @ResultType(Integer.class)
    int saveEnergyCarbonPlan(@Param("energyCarbonPlanList") List<EnergyCarbonPlan> energyCarbonPlanList);
}
