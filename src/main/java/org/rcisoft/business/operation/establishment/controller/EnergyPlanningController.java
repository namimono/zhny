package org.rcisoft.business.operation.establishment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.establishment.entity.ProIdAndDate;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningCostService;
import org.rcisoft.business.operation.establishment.service.EnergyPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Api(tags = "节能运维--计划编制")
@RestController
@RequestMapping("energyPlanning")
public class EnergyPlanningController {
    @Autowired
    private EnergyPlanningCostService energyPlanningCostService;
    @Autowired
    private EnergyPlanningService energyPlanningService;

    @ApiOperation(value="根据时间与项目Id查询出预计费用信息", notes="根据时间与项目Id查询出预计费用信息")
    @PostMapping("/getEnergyPlanningCostByDateAndProId")
    public Result getEnergyPlanningCostByDateAndProId(@RequestBody ProIdAndDate proIdAndDate){
        return Result.result(energyPlanningCostService.getEnergyPlanningCostByDateAndProId(proIdAndDate));
    }


    @ApiOperation(value="根据时间与项目Id查询出设备的计划编制信息", notes="根据时间与项目Id查询出设备的计划编制信息")
    @PostMapping("/listEnergyPlanningDevice")
    public Result listEnergyPlanningDevice(@RequestBody ProIdAndDate proIdAndDate){
        return Result.result(energyPlanningService.listDevicePlanningRecord(proIdAndDate));
    }

    @ApiOperation(value="根据设备Id查出计划列表用到的信息", notes="根据设备Id查出计划列表用到的信息")
    @PostMapping("/listPlanList")
    public Result listPlanList(@RequestBody ProIdAndDate proIdAndDate){
        return Result.result(energyPlanningService.listPlanList(proIdAndDate.getDevId()));
    }








}
