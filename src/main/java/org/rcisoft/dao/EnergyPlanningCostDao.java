package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.rcisoft.entity.EnergyPlanningCost;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Repository
public interface EnergyPlanningCostDao extends Mapper<EnergyPlanningCost> {

    /**
     *  批量增加EnergyPlanningCost
     * @param energyPlanningCostList
     * @return
     */
    @Insert("<script><foreach collection = \"energyPlanningCostList\" item = \"item\" separator=\";\"> " +
            " insert into energy_planning_cost(id, project_id, device_id, create_time, energy_elec, energy_gas, money_elec, money_gas) " +
            " values(#{item.id}, #{item.projectId}, #{item.deviceId}, #{item.createTime}, #{item.energyElec}, #{item.energyGas}, #{item.moneyElec}, #{item.moneyGas}) " +
            "</foreach></script>")
    Integer saveListEnergyPlanningCost(@Param("energyPlanningCostList") List<EnergyPlanningCost> energyPlanningCostList);

}
