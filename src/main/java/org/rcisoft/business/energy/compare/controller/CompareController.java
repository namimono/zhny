package org.rcisoft.business.energy.compare.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.energy.compare.service.CompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JiChao on 2019/3/20.
 * 能耗管理--用能比较
 */
@Api(tags = "能耗管理--用能比较")
@RestController
@RequestMapping("compare")
public class CompareController {

    @Autowired
    CompareService compareServiceImpl;

    @ApiOperation(value="能耗概况", notes="能耗概况")
    @GetMapping("/queryEnergyDayAndMon/{projectId}")
    public Result queryEnergyDayAndMon(@PathVariable String projectId) {
        return Result.result(compareServiceImpl.queryEnergyDayAndMon(projectId));
    }

    @ApiOperation(value="能耗标准", notes="能耗标准")
    @GetMapping("/queryEnergyStandard/{projectId}")
    public Result queryEnergyStandard(@PathVariable String projectId) {
        return Result.result(compareServiceImpl.queryEnergyStandard(projectId));
    }

    @ApiOperation(value="用能比较", notes="energyType：1水2电3气；compareType：1同比2环比")
    @GetMapping("/queryEnergyCompare/{projectId}/{energyType}/{compareType}/{year}/{month}")
    public Result queryEnergyCompare(@PathVariable String projectId, @PathVariable Integer energyType, @PathVariable Integer compareType, @PathVariable Integer year, @PathVariable Integer month) {
        return Result.result(compareServiceImpl.queryEnergyCompare(projectId, energyType, compareType, year, month));
    }

}
