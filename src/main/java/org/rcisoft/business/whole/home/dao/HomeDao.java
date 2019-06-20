package org.rcisoft.business.whole.home.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.whole.home.entity.ProjectHome;
import org.rcisoft.entity.EnergySaveReduction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
        * @Description:
        * @Date: Created in 14:42 2019/4/9
        */
@Repository
public interface HomeDao {
    /**
     * 查询年份能源统计
     */
    @Select("<script>select project_id as proId,sum(money_gas) as moneyGas,sum(money_elec) as moneyElec,sum(money_water) as moneyWater " +
            "from energy_statistics where time_year = #{year} and time_month = #{month} and time_day = #{day} GROUP BY project_id</script>")
    List<Map<String,Object>> queryEnergyStatics(@Param("year") int year, @Param("month") int month, @Param("day") int day);

    /**
     * 查询年份碳排放量
     */
    @Select("<script>select project_id as proId,carbon from energy_carbon_reduction where time_year = #{year}</script>")
    List<Map<String,Object>> queryCarbonYear(int year);

    /**
     * 查询故障状态
     */
    @Select("<script>select project_id as proId,count(status) as count from bus_malfunction where status = '1' GROUP BY project_id</script>")
    List<Map<String,Object>> queryStatus();

    /**
     * 查询项目详细信息
     */
    @Select("<script>select bp.code AS code, bp.id as Id,bp.name as proName,bp.create_time as createTime,bp.building_local as buildingLocal,bb.name as buildingType,bp.building_area " +
            "as buildingArea, bp.phones as phones, bp.receive as receive from bus_project bp,bus_building bb, sys_user_project_mid m where bb.id = bp.building_id and m.project_id = bp.id and m.user_id = #{userId} and online = 1</script>")
    List<ProjectHome> queryProjectDetail(@Param("userId") String userId);

    @Select("<script>select * from energy_save_reduction where time_year = #{year}</script>")
    @ResultType(EnergySaveReduction.class)
    List<EnergySaveReduction> querySave(int year);
}
