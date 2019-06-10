package org.rcisoft.business.management.distribution.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.management.distribution.entity.MoneyCost;
import org.rcisoft.business.management.distribution.entity.ProjectInfomation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:00 2019/3/12
 */
@Repository
public interface EnergyDistributionDao {

    /**
     * 查询所有线上项目
     * @return
     */
    @Select("<script>select p.id, p.name, p.building_local, p.building_area, p.building_coordinate, p.building_age, p.equipment_age, b.name building_type_name, z.name building_zone_name, pro.name province_name, c.name city_name " +
            "from bus_project p left join bus_building b on b.id = p.building_id " +
            "left join bus_building_zone z on z.id = p.building_zone_id " +
            "left join sys_province pro on pro.id = p.province_id " +
            "left join sys_city c on c.id = p.city_id " +
            "where p.online = 1 and p.receive = 1</script>")
    @ResultType(ProjectInfomation.class)
    List<ProjectInfomation> queryProjectInfo();

    /**
     * 查询当月
     * @param year
     * @param month
     * @return
     */
    @Select("<script>select s.project_id, sum(money_water) money_water, sum(money_elec) money_elec, sum(money_gas) money_gas from energy_statistics s where s.time_year = #{year} and s.time_month = #{month} group by s.project_id</script>")
    @ResultType(MoneyCost.class)
    List<MoneyCost> queryProjectMoney(@Param("year") int year, @Param("month") int month);

}
