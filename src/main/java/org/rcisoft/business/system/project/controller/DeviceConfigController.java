package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.*;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:14
 **/
@Api(tags = "项目编辑-设备配置")
@RestController
@RequestMapping("deviceConfig")
public class DeviceConfigController {

    @Autowired
    private DeviceConfigService deviceConfigServiceImpl;

    @ApiOperation(value="新增设备配置信息", notes="新增设备配置信息")
    @PostMapping("/addDeviceConfigInfo")
    public Result addDeviceConfigInfo(@RequestBody BusDevice busDevice){
        return Result.result(1, deviceConfigServiceImpl.addDeviceConfigInfo(busDevice));
    }

    @ApiOperation(value="查询设备简要信息", notes="查询设备简要信息")
    @GetMapping("/queryDeviceBriefInfo")
    public Result queryDeviceBriefInfo(@RequestBody BusDevice busDevice){
        return Result.result(deviceConfigServiceImpl.queryDeviceBriefInfo(busDevice));
    }

    @ApiOperation(value="处理一、二级设备类型下拉菜单级联格式", notes="处理一、二级设备类型下拉菜单级联格式")
    @GetMapping("/processTypeFormat")
    public Result processTypeFormat(@RequestParam("systemId") String systemId){
        return Result.result(deviceConfigServiceImpl.processTypeFormat(systemId));
    }

    @ApiOperation(value="根据项目ID和子系统ID查询未关联一级参数信息", notes="根据项目ID和子系统ID查询未关联一级参数信息")
    @GetMapping("/queryParamFirstInfoBySysId")
    public Result queryParamFirstInfoBySysId(@RequestBody BusParamFirst busParamFirst){
        return Result.result(deviceConfigServiceImpl.queryParamFirstInfoBySysId(busParamFirst));
    }

    @ApiOperation(value="根据项目、设备、系统ID查询固定参数信息", notes="根据项目、设备、系统ID查询固定参数信息")
    @GetMapping("/queryParamFixed")
    public Result queryParamFixed(@RequestBody BusParamFixed busParamFixed){
        return Result.result(deviceConfigServiceImpl.queryParamFixed(busParamFixed));
    }

    @ApiOperation(value="添加固定参数表信息", notes="添加固定参数表信息")
    @PostMapping("/addParamFixed")
    public Result addParamFixed(@RequestBody BusParamFixed busParamFixed){
        return Result.result(1, deviceConfigServiceImpl.addParamFixed(busParamFixed));
    }

    @ApiOperation(value="修改固定参数表信息", notes="修改固定参数表信息")
    @PutMapping("/updateParamFixed")
    public Result updateParamFixed(@RequestBody BusParamFixed busParamFixed){
        return Result.result(1, deviceConfigServiceImpl.updateParamFixed(busParamFixed));
    }

    @ApiOperation(value="删除固定参数表信息", notes="删除固定参数表信息")
    @DeleteMapping("/deleteParamFixed")
    public Result deleteParamFixed(@RequestBody BusParamFixed busParamFixed){
        return Result.result(1, deviceConfigServiceImpl.deleteParamFixed(busParamFixed));
    }

    @ApiOperation(value="增加设备一级参数中间表信息", notes="增加设备一级参数中间表信息")
    @PostMapping("/addMidDeviceFirstInfo")
    public Result addMidDeviceFirstInfo(@RequestBody MidDeviceParamFirst midDeviceParamFirst){
        return Result.result(1, deviceConfigServiceImpl.addMidDeviceFirstInfo(midDeviceParamFirst));
    }

    @ApiOperation(value="删除设备一级参数中间表信息", notes="删除设备一级参数中间表信息")
    @DeleteMapping("/deleteMidDeviceFirstInfo")
    public Result deleteMidDeviceFirstInfo(@RequestBody MidDeviceParamFirst midDeviceParamFirst){
        return Result.result(1, deviceConfigServiceImpl.deleteMidDeviceFirstInfo(midDeviceParamFirst));
    }

    @ApiOperation(value="增加设备二级参数中间表信息", notes="增加设备二级参数中间表信息")
    @PostMapping("/addMidDeviceSecondInfo")
    public Result addMidDeviceSecondInfo(@RequestBody MidDeviceParamSecond midDeviceParamSecond){
        return Result.result(1, deviceConfigServiceImpl.addMidDeviceSecondInfo(midDeviceParamSecond));
    }

    @ApiOperation(value="删除设备二级参数中间表信息", notes="删除设备二级参数中间表信息")
    @DeleteMapping("/deleteMidDeviceSecondInfo")
    public Result deleteMidDeviceSecondInfo(@RequestBody MidDeviceParamSecond midDeviceParamSecond){
        return Result.result(deviceConfigServiceImpl.deleteMidDeviceSecondInfo(midDeviceParamSecond));
    }

    @ApiOperation(value="修改设备一级参数中间表信息", notes="修改设备一级参数中间表信息")
    @PutMapping("/updateMidDeviceFirstInfo")
    public Result updateMidDeviceFirstInfo(@RequestBody MidDeviceParamFirst midDeviceParamFirst){
        return Result.result(1, deviceConfigServiceImpl.updateMidDeviceFirstInfo(midDeviceParamFirst));
    }

    @ApiOperation(value="修改设备二级参数中间表信息", notes="修改设备二级参数中间表信息")
    @PutMapping("/updateMidDeviceSecondInfo")
    public Result updateMidDeviceSecondInfo(@RequestBody MidDeviceParamSecond midDeviceParamSecond){
        return Result.result(1, deviceConfigServiceImpl.updateMidDeviceSecondInfo(midDeviceParamSecond));
    }
}
