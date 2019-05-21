package org.rcisoft.business.equipment.maintain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.maintain.service.MaintainService;
import org.rcisoft.entity.BusMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/4/1.
 */
@Api(tags = "设备维护--养护计划")
@RestController
@RequestMapping("maintain")
public class MaintainController {

    @Autowired
    MaintainService maintainServiceImpl;

    @ApiOperation(value="养护日程", notes="count > 0，认为")
    @GetMapping("/querySchedule/{projectId}/{year}")
    public Result querySchedule(@PathVariable String projectId, @PathVariable Integer year) {
        return Result.result(maintainServiceImpl.querySchedule(projectId, year));
    }

    @ApiOperation(value="当日养护计划", notes="当日养护计划")
    @GetMapping("/queryPlan/{projectId}/{year}/{month}/{day}")
    public Result queryPlan(@PathVariable String projectId, @PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return Result.result(maintainServiceImpl.queryPlan(projectId, year, month, day));
    }

    @ApiOperation(value="根据id查询养护计划", notes="根据id查询养护计划")
    @GetMapping("/queryMaintenance/{id}")
    public Result queryMaintenance(@PathVariable String id) {
        return Result.result(maintainServiceImpl.getMaintenanceAndDevTypeId(id));
    }

    @ApiOperation(value="新增养护计划", notes="新增养护计划")
    @PostMapping("/insertMaintenance")
    public Result insertMaintenance(@RequestBody BusMaintenance busMaintenance) {
        return Result.result(maintainServiceImpl.insertMaintenance(busMaintenance), "新增养护计划成功","新增养护计划失败");
    }

    @ApiOperation(value="修改养护计划", notes="修改养护计划")
    @PutMapping("/updateMaintenance")
    public Result updateMaintenance(@RequestBody BusMaintenance busMaintenance) {
        return Result.result(maintainServiceImpl.updateMaintenance(busMaintenance), null);
    }

    @ApiOperation(value="删除养护计划", notes="删除养护计划")
    @DeleteMapping("/deleteMaintenance/{id}")
    public Result deleteMaintenance(@PathVariable String id) {
        return Result.result(maintainServiceImpl.deleteMaintenance(id), null);
    }

}
