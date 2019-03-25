package org.rcisoft.business.energy.plan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.energy.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JiChao on 2019/3/21.
 * 能耗管理--用能计划
 */
@Api(tags = "能耗管理--用能计划")
@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired
    PlanService planServiceImpl;

    @ApiOperation(value="用能计划统计", notes="用能计划统计")
    @GetMapping("/queryPlanStatistics/{projectId}")
    public Result queryPlanStatistics(@PathVariable String projectId) {
        return Result.result(planServiceImpl.queryPlanStatistics(projectId));
    }

    @ApiOperation(value="今日用能计划", notes="今日用能计划")
    @GetMapping("/queryPlanDay/{projectId}")
    public Result queryPlanDay(@PathVariable String projectId) {
        return Result.result(planServiceImpl.queryPlanDay(projectId));
    }

    @ApiOperation(value="月用能计划", notes="月用能计划")
    @GetMapping("/queryPlanMonth/{projectId}/{year}/{month}")
    public Result queryPlanMonth(@PathVariable String projectId, @PathVariable Integer year, @PathVariable Integer month) {
        return Result.result(planServiceImpl.queryPlanMonth(projectId, year, month));
    }

}
