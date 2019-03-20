package org.rcisoft.business.energy.overview.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.energy.overview.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/3/15.
 * 能耗管理--用能概况
 */
@Api(tags = "能耗管理--用能概况")
@RestController
@RequestMapping("overview")
public class OverviewController {

    @Autowired
    OverviewService overviewServiceImpl;

    @ApiOperation(value="根据项目id查询每小时的价格", notes="每小时的数据可能不同，需要在整点时候定时调用")
    @GetMapping("/queryPricePerHour/{projectId}")
    public Result queryPricePerHour(@PathVariable String projectId) {
        return Result.result(overviewServiceImpl.queryPricePerHour(projectId));
    }

    @ApiOperation(value="费用概况：今日、本月费用、排名", notes="费用概况：今日、本月费用、排名")
    @GetMapping("/queryPriceAndRank/{projectId}")
    public Result queryPriceAndRank(@PathVariable String projectId) {
        return Result.result(overviewServiceImpl.queryPriceAndRank(projectId));
    }

    @ApiOperation(value="今日、昨日分时运行费用", notes="1：水，2：电，3：气，总费用不需要传值；0：今日，-1：昨日，0,-1：今日+昨日")
    @GetMapping("/queryPriceForDay/{projectId}/{energyType}/{dayType}")
    public Result queryPriceForDay(@PathVariable String projectId, @PathVariable(required = false) Integer energyType, @PathVariable String dayType) {
        return Result.result(overviewServiceImpl.queryPriceForDay(projectId, energyType, dayType));
    }

    @ApiOperation(value="能耗拆分", notes="改成了饼图，和一期一样；energyType：1：水，2：电，3：气")
    @GetMapping("/queryEnergySplit/{projectId}/{energyType}")
    public Result queryEnergySplit(@PathVariable String projectId, @PathVariable Integer energyType) {
        return Result.result(overviewServiceImpl.queryEnergySplit(projectId, energyType));
    }

}
