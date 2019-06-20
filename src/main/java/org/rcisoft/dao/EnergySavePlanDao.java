package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.rcisoft.entity.EnergySavePlan;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface EnergySavePlanDao extends Mapper<EnergySavePlan> {

    @Insert("<script><foreach collection=\"saveList\" item=\"item\" separator=\";\">" +
            "insert into energy_save_plan(id, project_id, time_month, time_day, money)" +
            "values(#{item.id}, #{item.projectId},#{item.timeMonth}, #{item.timeDay}, #{item.money}) " +
            "</foreach></script>")
    @ResultType(Integer.class)
    int batchSave(@Param("saveList") List<EnergySavePlan> saveList);

}
