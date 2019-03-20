package org.rcisoft.business.operation.establishment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.service.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private EnergyPlanningRecordService energyPlanningRecordService;
    @Autowired
    private EnergyParamLibraryService energyParamLibraryService;
    @Autowired
    private EnergyPlanningDeviceService energyPlanningDeviceService;
    @Autowired
    private BusTypeFirstService busTypeFirstService;
    @Autowired
    private BusTypeSecondService busTypeSecondService;
    @Autowired
    private BusDeviceService busDeviceService;

    @ApiOperation(value = "刚进入页面时，查询预计费用信息", notes = "参数：项目Id与时间（yyyy-MM-dd）")
    @PostMapping("/getEnergyPlanningCostByDateAndProId")
    public Result getEnergyPlanningCostByDateAndProId(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningCostService.getEnergyPlanningCostByDateAndProId(conditionDto));
    }


    @ApiOperation(value = "刚进入页面时，查询所有设备的计划编制信息(显示在右上角的表格)", notes = "参数：项目Id与时间（yyyy-MM-dd）")
    @PostMapping("/listEnergyPlanningDevice")
    public Result listEnergyPlanningDevice(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.listDevicePlanningRecord(conditionDto));
    }

    @ApiOperation(value = "在右上角点击某个设备，查出这个设备的计划编制信息，显示在计划列表", notes = "参数：设备Id与时间（yyyy-MM-dd）")
    @PostMapping("/listPlanList")
    public Result listPlanList(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.listPlanList(conditionDto));
    }

    @ApiOperation(value = "点击修改计划列表中的修改，查出在计划列表修改计划用到的信息", notes = "参数：计划编制记录表Id与设备Id")
    @PostMapping("/listDevicePlanningByDevIdAndRecId")
    public Result listDevicePlanningByDevIdAndRecId(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.listDevicePlanningByDevIdAndRecId(conditionDto));
    }

    @ApiOperation(value = "修改或者新增计划编制记录时,根据主参数的值，查出副参数值", notes = "参数：主参数，设备Id")
    @PostMapping("/listEnergyParamLibraryByParam")
    public Result listEnergyParamLibraryByParam(@RequestBody EnergyParamLibrary energyParamLibrary) {
        return Result.result(energyParamLibraryService.listEnergyParamLibraryByParam(energyParamLibrary));
    }

    @ApiOperation(value = "修改完成后，点击保存，更新计划编制信息", notes = "参数：需要更新的数据,包括表Id,开始结束时间（yyyy-MM-dd HH:mm:ss），四个参数值")
    @PostMapping("/updateEnergyPlanningRecord")
    public Result updateEnergyPlanningRecord(@RequestBody EnergyPlanningRecord energyPlanningRecord) {
        return Result.result(energyPlanningRecordService.updateEnergyPlanningRecord(energyPlanningRecord), "修改计划编制成功", "修改计划编制失败");
    }

    @ApiOperation(value = "点击添加设备，添加设备", notes = "参数：设备Id,项目Id,创建时间（yyyy-MM-dd）")
    @PostMapping("/saveEnergyPlanningDevice")
    public Result saveEnergyPlanningDevice(@RequestBody EnergyPlanningDevice energyPlanningDevice) {
        return Result.result(energyPlanningDeviceService.saveEnergyPlanningDevice(energyPlanningDevice), "添加设备成功", "添加设备失败,可能已经添加过这个设备了");
    }

    @ApiOperation(value = "添加设备时，选择一级设备类型，查询一级设备类别", notes = "无参数")
    @GetMapping("/listBusTypeFirst")
    public Result listBusTypeFirst() {
        return Result.result(busTypeFirstService.listBusTypeFirst());
    }

    @ApiOperation(value = "添加设备时，选择二级设备类型，查询二级设备类别", notes = "参数：一级设备类型Id")
    @PostMapping("/listBusTypeSecondByFirstId")
    public Result listBusTypeSecondByFirstId(@RequestBody BusTypeSecond busTypeSecond) {
        return Result.result(busTypeSecondService.listBusTypeSecondByFirstId(busTypeSecond));
    }


    @ApiOperation(value = "添加设备时，选择设备，根据设备类型查询设备", notes = "参数：一级设备类型Id，二级设备类型Id,项目Id")
    @PostMapping("/listBusDevice")
    public Result listBusDevice(@RequestBody BusDevice busDevice) {
        return Result.result(busDeviceService.listBusDevice(busDevice));
    }


    @ApiOperation(value = "点击添加一条，新增计划编制", notes = "参数为：项目Id,设备Id,第一个主参数二级Id,第二个主参数二级Id,所有参数值，创建时间（yyyy-MM-dd）,开始时间与结束时间（yyyy-MM-dd HH:mm:ss）")
    @PostMapping("/saveEnergyPlanningRecord")
    public Result saveEnergyPlanningRecord(@RequestBody EnergyPlanningRecord energyPlanningRecord) {
        return Result.result(energyPlanningRecordService.saveEnergyPlanningRecord(energyPlanningRecord), "添加计划成功", "添加计划失败");
    }

    @ApiOperation(value = "点击添加一条后，显示该设备的计划参数名称，查询新增计划编制时的参数名称，Id", notes = "参数为：设备Id")
    @PostMapping("/listDevicePlanningByDevId")
    public Result listDevicePlanningByDevId(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.listDevicePlanningByDevId(conditionDto));
    }

    @ApiOperation(value = "点击删除，删除计划编制记录", notes = "参数为：计划编制记录表Id")
    @DeleteMapping("/deleteEnergyPlanningRecordById")
    public Result deleteEnergyPlanningRecordById(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningRecordService.deleteEnergyPlanningRecordById(conditionDto),"删除计划成功", "删除计划失败");
    }


    @ApiOperation(value = "点击清空当日计划，清空当日计划", notes = "参数为：日期（yyyy-MM-dd）,项目Id")
    @DeleteMapping("/deletePlanTheDayByProIdAndDate")
    public Result deletePlanTheDayByProIdAndDate(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.deletePlanTheDayByProIdAndDate(conditionDto),"删除当日计划成功", "删除当日计划失败,请确认当天是否有计划");
    }


    @ApiOperation(value = "点击复制当日计划，复制当日计划", notes = "参数为：要复制到哪天的日期（yyyy-MM-dd）,日期（yyyy-MM-dd）,项目Id")
    @PostMapping("/copyPlanning")
    public Result copyPlanning(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.copyPlanning(conditionDto),"复制当日计划成功", "复制当日计划失败,请确认当天是否有计划");
    }















}
