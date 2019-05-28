package org.rcisoft.business.operation.saving.service;

import org.rcisoft.business.operation.saving.entity.PlanCostAndRealCost;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/28
 */
public interface EnergySavingService {

    /**
     * 查询某个项目的今日计划费用与实际费用
     * @return List<PlanCostAndRealCost>
     */
    List<PlanCostAndRealCost> listRunningEnergyCost(String projectId);
}
