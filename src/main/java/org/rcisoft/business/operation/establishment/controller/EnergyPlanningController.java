package org.rcisoft.business.operation.establishment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.service.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    private BusDeviceTypeService busDeviceTypeService;
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


    @ApiOperation(value = "点击修改计划列表中的修改或者新增计划编制记录时，查出设备的参数名称", notes = "参数：设备Id")
    @PostMapping("/listDeviceParamNameByDevId")
    public Result listDeviceParamNameByDevId(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.listDeviceParamNameByDevId(conditionDto));
    }

    @ApiOperation(value = "点击修改计划列表中的修改计划编制记录时，查出要修改的记录的信息用于回显", notes = "参数：计划编制Id")
    @PostMapping("/getEnergyPlanningRecordById")
    public Result getEnergyPlanningRecordById(@RequestBody ConditionDto conditionDto) {
        return Result.result(energyPlanningService.getEnergyPlanningRecordById(conditionDto));
    }


    @ApiOperation(value = "修改或者新增计划编制记录时,根据主参数的值，查出副参数值", notes = "参数：主参数，设备Id")
    @PostMapping("/listEnergyParamLibraryByParam")
    public Result listEnergyParamLibraryByParam(@RequestBody EnergyParamLibrary energyParamLibrary) {
        return Result.result(energyParamLibraryService.listEnergyParamLibraryByParam(energyParamLibrary));
    }

    @ApiOperation(value = "修改完成后，点击保存，更新计划编制信息", notes = "参数：需要更新的数据,包括表Id,对应的电费用，气费用，电能耗，气能耗，开始结束时间（yyyy-MM-dd HH:mm:ss），四个参数值")
    @PostMapping("/updateEnergyPlanningRecord")
    public Result updateEnergyPlanningRecord(@RequestBody EnergyPlanningRecord energyPlanningRecord) {
        return Result.result(energyPlanningRecordService.updateEnergyPlanningRecord(energyPlanningRecord), "修改计划编制成功", "修改计划编制失败");
    }

    @ApiOperation(value = "点击添加设备，添加设备", notes = "参数：设备Id,项目Id,创建时间（yyyy-MM-dd）")
    @PostMapping("/saveEnergyPlanningDevice")
    public Result saveEnergyPlanningDevice(@RequestBody EnergyPlanningDevice energyPlanningDevice) {
        return Result.result(energyPlanningDeviceService.saveEnergyPlanningDevice(energyPlanningDevice), "添加设备成功", "添加设备失败,可能已经添加过这个设备了");
    }

    @ApiOperation(value = "点击删除设备，删除设备", notes = "参数：设备Id,创建时间（yyyy-MM-dd）")
    @DeleteMapping("/deleteEnergyPlanningDevice")
    public Result deleteEnergyPlanningDevice(@RequestBody EnergyPlanningDevice energyPlanningDevice) {
        return Result.result(energyPlanningDeviceService.deleteEnergyPlanningDevice(energyPlanningDevice), "删除设备成功", "删除设备失败");
    }



    @ApiOperation(value = "添加设备时，选择设备类型，查询设备类别", notes = "无参数")
    @GetMapping("/listBusTypeFirst")
    public Result listBusTypeFirst() {
        return Result.result(busDeviceTypeService.listBusDeviceType());
    }


    @ApiOperation(value = "添加设备时，选择设备，根据设备类型查询设备", notes = "参数：设备类型Id,项目Id")
    @PostMapping("/listBusDevice")
    public Result listBusDevice(@RequestBody BusDevice busDevice) {
        return Result.result(busDeviceService.listBusDevice(busDevice));
    }


    @ApiOperation(value = "点击添加一条，新增计划编制", notes = "参数为：项目Id,设备Id,所有参数值，对应的电费用，气费用，电能耗，气能耗,开始时间与结束时间（yyyy-MM-dd HH:mm:ss）,创建时间（yyyy-MM-dd）")
    @PostMapping("/saveEnergyPlanningRecord")
    public Result saveEnergyPlanningRecord(@RequestBody EnergyPlanningRecord energyPlanningRecord) throws ParseException {
        return Result.result(energyPlanningRecordService.saveEnergyPlanningRecord(energyPlanningRecord), "添加计划成功", "添加计划失败");
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
