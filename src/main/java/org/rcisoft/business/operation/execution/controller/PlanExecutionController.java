package org.rcisoft.business.operation.execution.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.execution.service.PlanExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GaoLiwei
 * @date 2019/3/21
 */
@Api(tags = "节能运维--计划执行")
@RestController
@RequestMapping("planExecution")
public class PlanExecutionController {
    @Autowired
    private PlanExecutionService planExecutionService;

    @ApiOperation(value = "刚进入页面时，查询所有设备的计划编制信息(显示在右上角的表格)", notes = "参数：项目Id与时间（yyyy-MM-dd）")
    @PostMapping("/listEnergyPlanningDevice")
    public Result listDevicePlanningInfoAndStatus(@RequestBody ConditionDto conditionDto) {
        return Result.result(planExecutionService.listDevicePlanningInfoAndStatus(conditionDto));
    }

    @ApiOperation(value = "根据月份查询的选定月份的每天有几条计划编制信息", notes = "参数：项目Id与月份时间（yyyy-MM）")
    @PostMapping("/listMonthPlanNum")
    public Result listMonthPlanNum(@RequestBody ConditionDto conditionDto) {
        return Result.result(planExecutionService.listMonthPlanNum(conditionDto));
    }


    @ApiOperation(value = "点击左侧列表某一个时间，查询当天的所有花费信息", notes = "参数：项目Id与时间（yyyy-MM-dd）")
    @PostMapping("/getMoneySum")
    public Result getMoneySum(@RequestBody ConditionDto conditionDto) {
        return Result.result(planExecutionService.getMoneySum(conditionDto));
    }

}
