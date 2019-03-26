package org.rcisoft.business.energy.emission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.energy.emission.entity.EmissionParam;
import org.rcisoft.business.energy.emission.service.EmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/3/25.
 * 能耗管理--用能比较
 */
@Api(tags = "能耗管理--用能比较")
@RestController
@RequestMapping("emission")
public class EmissionController {

    @Autowired
    EmissionService emissionServiceImpl;

    @ApiOperation(value="碳排放量强度", notes="减排、今日、本月、本年、昨日、日同比、日环比、月同比、月环比、百分比")
    @GetMapping("/queryEmission/{projectId}")
    public Result queryEmission(@PathVariable String projectId) {
        return Result.result(emissionServiceImpl.queryEmission(projectId));
    }

    @ApiOperation(value="碳排放强度统计", notes="日排放返回24小时，月排放返回当月总日期")
    @GetMapping("/queryEmissionStatistics/{projectId}")
    public Result queryEmissionStatistics(@RequestBody EmissionParam emissionParam) {
        return Result.result(emissionServiceImpl.queryEmissionStatistics(emissionParam));
    }
}
