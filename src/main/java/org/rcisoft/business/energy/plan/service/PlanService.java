package org.rcisoft.business.energy.plan.service;

import org.rcisoft.business.energy.plan.entity.PlanDayResult;
import org.rcisoft.business.energy.plan.entity.PlanStatisticsResult;

/**
 * Created by JiChao on 2019/3/21.
 */
public interface PlanService {

    /**
     * 用能计划统计
     * @param projectId
     * @return
     */
    PlanStatisticsResult queryPlanStatistics(String projectId);

    /**
     * 今日用能计划
     * @param projectId
     * @return
     */
    PlanDayResult queryPlanDay(String projectId);

}
