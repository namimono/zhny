package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
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
        return Result.result(deviceConfigServiceImpl.addDeviceConfigInfo(busDevice),"新增设备配置信息成功","新增设备配置信息失败");
    }

    @ApiOperation(value="删除设备信息(谨慎!)", notes="删除设备信息(谨慎!)")
    @DeleteMapping("/deleteAllByProId/{deviceId}")
    public Result deleteDevice(@PathVariable String deviceId){
        int i = deviceConfigServiceImpl.deleteDevice(deviceId);
        return Result.result(i,"删除设备信息成功","删除设备信息失败",i);
    }

    @ApiOperation(value="修改设备信息", notes="修改设备信息")
    @PutMapping("/updateDevice")
    public Result updateDevice(@RequestBody BusDevice busDevice){
        return Result.result(deviceConfigServiceImpl.updateDevice(busDevice),"修改设备信息成功","修改设备信息失败");
    }

    @ApiOperation(value="查询设备简要信息", notes="查询设备简要信息")
    @GetMapping("/queryDeviceBriefInfo/{systemId}/{projectId}")
    public Result queryDeviceBriefInfo(@PathVariable String systemId,@PathVariable String projectId){
        DeviceBriefInfo deviceBriefInfo = new DeviceBriefInfo();
        deviceBriefInfo.setSystemId(systemId);
        deviceBriefInfo.setProjectId(projectId);
        return Result.result(deviceConfigServiceImpl.queryDeviceBriefInfo(deviceBriefInfo));
    }

    @ApiOperation(value="根据设备ID查询设备信息", notes="根据设备ID查询设备信息")
    @GetMapping("/queryDeviceInfo/{deviceId}")
    public Result queryDeviceInfo(@PathVariable String deviceId){
        return Result.result(deviceConfigServiceImpl.queryDeviceInfo(deviceId));
    }

    @ApiOperation(value="处理一、二级设备类型下拉菜单级联格式", notes="处理一、二级设备类型下拉菜单级联格式")
    @GetMapping("/processTypeFormat/{systemId}")
    public Result processTypeFormat(@PathVariable String systemId){
        return Result.result(deviceConfigServiceImpl.processTypeFormat(systemId));
    }

    @ApiOperation(value="根据项目ID和子系统ID查询未关联一级参数信息", notes="根据项目ID和子系统ID查询未关联一级参数信息")
    @GetMapping("/queryParamFirstInfoBySysId/{systemId}/{projectId}")
    public Result queryParamFirstInfoBySysId(@PathVariable String systemId,@PathVariable String projectId){
        BusParamFirst busParamFirst = new BusParamFirst();
        busParamFirst.setSystemId(systemId);
        busParamFirst.setProjectId(projectId);
        return Result.result(deviceConfigServiceImpl.queryParamFirstInfoBySysId(busParamFirst));
    }

//    @ApiOperation(value="根据项目、设备、系统ID查询固定参数信息", notes="根据项目、设备、系统ID查询固定参数信息")
//    @GetMapping("/queryParamFixed/{systemId}/{projectId}/{deviceId}")
//    public Result queryParamFixed(@PathVariable String systemId,@PathVariable String projectId,@PathVariable String deviceId){
//        BusParamFixed busParamFixed = new BusParamFixed();
//        busParamFixed.setSystemId(systemId);
//        busParamFixed.setProjectId(projectId);
//        busParamFixed.setDeviceId(deviceId);
//        return Result.result(deviceConfigServiceImpl.queryParamFixed(busParamFixed));
//    }
//
//    @ApiOperation(value="添加固定参数表信息", notes="添加固定参数表信息")
//    @PostMapping("/addParamFixed")
//    public Result addParamFixed(@RequestBody BusParamFixed busParamFixed){
//        return Result.result(deviceConfigServiceImpl.addParamFixed(busParamFixed),"添加固定参数表信息成功","添加固定参数表信息失败");
//    }
//
//    @ApiOperation(value="修改固定参数表信息", notes="修改固定参数表信息")
//    @PutMapping("/updateParamFixed")
//    public Result updateParamFixed(@RequestBody BusParamFixed busParamFixed){
//        return Result.result(deviceConfigServiceImpl.updateParamFixed(busParamFixed),"修改固定参数表信息成功","修改固定参数表信息失败");
//    }
//
//    @ApiOperation(value="删除固定参数表信息", notes="删除固定参数表信息")
//    @DeleteMapping("/deleteParamFixed")
//    public Result deleteParamFixed(@RequestBody BusParamFixed busParamFixed){
//        return Result.result(deviceConfigServiceImpl.deleteParamFixed(busParamFixed),"删除固定参数表信息成功","删除固定参数表信息失败");
//    }
//
//    @ApiOperation(value="增加设备一级参数中间表信息", notes="增加设备一级参数中间表信息")
//    @PostMapping("/addMidDeviceFirstInfo")
//    public Result addMidDeviceFirstInfo(@RequestBody MidDeviceParamFirst midDeviceParamFirst){
//        return Result.result(deviceConfigServiceImpl.addMidDeviceFirstInfo(midDeviceParamFirst),"增加设备一级参数中间表信息成功","增加设备一级参数中间表信息失败");
//    }
//
//    @ApiOperation(value="删除设备一级参数中间表信息", notes="删除设备一级参数中间表信息")
//    @DeleteMapping("/deleteMidDeviceFirstInfo")
//    public Result deleteMidDeviceFirstInfo(@RequestBody MidDeviceParamFirst midDeviceParamFirst){
//        return Result.result(deviceConfigServiceImpl.deleteMidDeviceFirstInfo(midDeviceParamFirst),"删除设备一级参数中间表信息成功","删除设备一级参数中间表信息失败");
//    }
//
//    @ApiOperation(value="增加设备二级参数中间表信息", notes="增加设备二级参数中间表信息")
//    @PostMapping("/addMidDeviceSecondInfo")
//    public Result addMidDeviceSecondInfo(@RequestBody MidDeviceParamSecond midDeviceParamSecond){
//        return Result.result(deviceConfigServiceImpl.addMidDeviceSecondInfo(midDeviceParamSecond),"增加设备二级参数中间表信息成功","增加设备二级参数中间表信息失败");
//    }
//
//    @ApiOperation(value="删除设备二级参数中间表信息", notes="删除设备二级参数中间表信息")
//    @DeleteMapping("/deleteMidDeviceSecondInfo")
//    public Result deleteMidDeviceSecondInfo(@RequestBody MidDeviceParamSecond midDeviceParamSecond){
//        return Result.result(deviceConfigServiceImpl.deleteMidDeviceSecondInfo(midDeviceParamSecond),"删除设备二级参数中间表信息成功","删除设备二级参数中间表信息失败");
//    }
//
//    @ApiOperation(value="修改设备一级参数中间表信息", notes="修改设备一级参数中间表信息")
//    @PutMapping("/updateMidDeviceFirstInfo")
//    public Result updateMidDeviceFirstInfo(@RequestBody MidDeviceParamFirst midDeviceParamFirst){
//        return Result.result(deviceConfigServiceImpl.updateMidDeviceFirstInfo(midDeviceParamFirst),"修改设备一级参数中间表信息成功","修改设备一级参数中间表信息失败");
//    }
//
//    @ApiOperation(value="修改设备二级参数中间表信息", notes="修改设备二级参数中间表信息")
//    @PutMapping("/updateMidDeviceSecondInfo")
//    public Result updateMidDeviceSecondInfo(@RequestBody MidDeviceParamSecond midDeviceParamSecond){
//        return Result.result(deviceConfigServiceImpl.updateMidDeviceSecondInfo(midDeviceParamSecond),"修改设备二级参数中间表信息成功","修改设备二级参数中间表信息失败");
//    }

}
