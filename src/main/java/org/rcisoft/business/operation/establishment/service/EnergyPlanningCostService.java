package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.business.operation.establishment.entity.ProIdAndDate;
import org.rcisoft.entity.EnergyPlanningCost;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
public interface EnergyPlanningCostService {


    /**
     *  根据项目Id与时间查出当前项目在改天的计划能耗花费
     * @param proIdAndDate
     * @return
     */
    EnergyPlanningCost getEnergyPlanningCostByDateAndProId(ProIdAndDate proIdAndDate);

}
