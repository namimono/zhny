package org.rcisoft.business.whole.home.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.whole.home.entity.ProjectDetail;
import org.rcisoft.business.whole.home.entity.ProjectHome;
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
            "from energy_statistics where time_year = #{year} GROUP BY project_id</script>")
    List<Map<String,Object>> queryEnergyStatics(int year);

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
    @Select("<script>select bp.id as Id,bp.name as proName,bp.create_time as createTime,bp.building_local as buildingLocal,bb.name as buildingType,bp.building_area " +
            "as buildingArea from bus_project bp,bus_building bb where bb.id = bp.building_id </script>")
    List<ProjectHome> queryProjectDetail();
}
