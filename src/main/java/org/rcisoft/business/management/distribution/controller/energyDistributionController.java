package org.rcisoft.business.management.distribution.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.management.distribution.service.energyDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:48 2019/3/12
 */
@Api(tags = "能耗分布")
@RestController
@RequestMapping("energyDistribution")
public class energyDistributionController {

    @Autowired
    private energyDistributionService energyDistributionService;

    @ApiOperation(value = "能耗分布及计算",notes = "能耗分布计算")
    @PostMapping(value = "/energyNum")
    public Result energyNum(@RequestParam int year, @RequestParam int month){
        return Result.result(energyDistributionService.queryEnergyDistributed(year, month));
    }
}
