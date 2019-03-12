package org.rcisoft.business.management.distribution.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.management.distribution.entity.EnergyDistribution;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.params.Params;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:00 2019/3/12
 */
@Repository
public interface energyDistributionDao extends Mapper<EnergyDistribution> {

    /**
     * 能耗分布计算
     * @param timeYear
     * @param Month
     * @param timeMonth
     * @return
     */
    @Select("<script>select bbz.name as buildingZoneType,bb.name as buildingType,bp.name as ProjName," +
            "bp.building_local as buildingLocal,bp.building_age as buildingAge,bp.equipment_age as equipmentAge," +
            "bp.building_coordinate as buildingCoordinate,bp.building_area as buildingArea," +
            "SUM(es.money_water) as sumWater,SUM(es.money_gas) as sumGas,SUM(es.money_elec) as sumElec " +
            "from bus_project bp right join energy_statistics es on bp.id = es.project_id ,bus_building bb ,bus_building_zone bbz " +
            "where bbz.id = bp.building_zone_id and bb.id = bp.building_id and es.time_year = ${timeYear} " +
            "<if test = \"Month != null and timeMonth != '' \">and es.time_month = #{Month}</if> " +
            "<if test = \"Month == null and Month == ''\"> and es.time_month &lt; ${timeMonth}</if>"+
            "GROUP BY bp.name" +
            "</script>")
    @ResultType(EnergyDistribution.class)
    List<EnergyDistribution> queryEnergyDistributed(@Param("timeYear") int timeYear,@Param("Month") String Month,@Param("timeMonth") int timeMonth);
}
