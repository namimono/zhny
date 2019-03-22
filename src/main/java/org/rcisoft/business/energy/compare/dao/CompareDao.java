package org.rcisoft.business.energy.compare.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.energy.compare.entity.DayAndEnergy;
import org.rcisoft.business.energy.compare.entity.EnergyAndCount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/20.
 * 能耗管理--用能比较
 */
@Repository
public interface CompareDao {

    /**
     * 分别查询水电气能耗和总数量
     * @param projectId
     * @param year
     * @return
     */
    @Select("<script>select sum(energy_water) water, sum(energy_elec) elec, sum(energy_gas) gas, count(1) `count` from energy_statistics where time_year = #{year} and project_id = #{projectId}</script>")
    @ResultType(EnergyAndCount.class)
    EnergyAndCount queryEnergyAndCount(@Param("projectId") String projectId, @Param("year") Integer year);

    /**
     * 查询月份下每日的能耗
     * @param projectId
     * @param year
     * @param month
     * @param column
     * @return
     */
    @Select("<script>select sum(${column}) energy, time_day `day` from energy_statistics where project_id = #{projectId} and time_year = #{year} and time_month = #{month} group by time_day order by time_day asc</script>")
    @ResultType(DayAndEnergy.class)
    List<DayAndEnergy> queryEnergyCompare(@Param("projectId") String projectId, @Param("year") Integer year, @Param("month") Integer month, @Param("column") String column);

}
