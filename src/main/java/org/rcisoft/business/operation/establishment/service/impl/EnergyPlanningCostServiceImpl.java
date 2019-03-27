package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningCostService;
import org.rcisoft.dao.EnergyPlanningCostDao;
import org.rcisoft.entity.EnergyPlanningCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Service
public class EnergyPlanningCostServiceImpl implements EnergyPlanningCostService {
    @Autowired
    private EnergyPlanningCostDao energyPlanningCostDao;

    /**
     * 根据时间与项目Id查询当前计划能耗花费
     * @author GaoLiWei
     * @date 9:55 2019/3/15
     **/
    @Override
    public EnergyPlanningCost getEnergyPlanningCostByDateAndProId(ConditionDto conditionDto) {
        EnergyPlanningCost energyPlanningCost = new EnergyPlanningCost();
        energyPlanningCost.setCreateTime(conditionDto.getDate());
        energyPlanningCost.setProjectId(conditionDto.getProId());
        return energyPlanningCostDao.selectOne(energyPlanningCost);
    }
}
