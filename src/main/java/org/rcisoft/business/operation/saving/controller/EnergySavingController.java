package org.rcisoft.business.operation.saving.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.saving.service.EnergySavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GaoLiwei
 * @date 2019/5/28
 */
@Api(tags = "计划编制-节能运行")
@RestController
@RequestMapping("energySaving")
public class EnergySavingController {
    @Autowired
    private EnergySavingService energySavingService;

    @ApiOperation(value = "查询今日运行费用", notes = "参数：项目Id")
    @GetMapping("/listRunningEnergyCost/{projectId}")
    public Result listRunningEnergyCost(@PathVariable String projectId){
        return Result.result(energySavingService.listRunningEnergyCost(projectId));
    }

}
