package org.rcisoft.business.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/3/26.
 * 公共接口
 */
@Api(tags = "公共接口")
@RestController
@RequestMapping("common")
public class CommonController {

    @Autowired
    CommonService commonServiceImpl;

    @ApiOperation(value="查询设备类型", notes="查询本项目中所有存在的设备类型")
    @PostMapping("/queryDeviceType")
    public Result queryDeviceType(@RequestParam String projectId) {
        return Result.result(commonServiceImpl.queryDeviceType(projectId));
    }

    @ApiOperation(value="查询项目拥有的系统类型", notes="查询项目拥有的系统类型")
    @GetMapping("/querySystemForProject/{projectId}")
    public Result querySystemForProject(@PathVariable String projectId) {
        return Result.result(commonServiceImpl.querySystemForProject(projectId));
    }

    @ApiOperation(value="查询项目下所有设备", notes="deviceTypeId：设备类型id，非必填；systemId：系统id，非必填")
    @PostMapping("/queryDevices")
    public Result queryDevices(@RequestParam String projectId, @RequestParam(required = false) String systemId, @RequestParam(required = false) String deviceTypeId) {
        return Result.result(commonServiceImpl.queryDevices(projectId, systemId, deviceTypeId));
    }

    @ApiOperation(value="查询设备所有的一级参数", notes="sourceId：1：设备，2：计量表，3：传感器，非必填")
    @PostMapping("/queryParamFirsts")
    public Result queryParamFirsts(@RequestParam String deviceId, @RequestParam(required = false) String sourceId) {
        return Result.result(commonServiceImpl.queryParamFirsts(deviceId, sourceId));
    }

    @ApiOperation(value="查询一级参数的二级参数", notes="查询一级参数的二级参数")
    @GetMapping("/queryParamSeconds/{paramFirstId}")
    public Result queryParamSeconds(@PathVariable String paramFirstId) {
        return Result.result(commonServiceImpl.queryParamSeconds(paramFirstId));
    }

    @ApiOperation(value="查询设备&一级参数&二级参数", notes="查询设备&一级参数&二级参数")
    @PostMapping("/queryDeviceAndParam")
    public Result queryDeviceAndParam(@RequestParam String projectId, @RequestParam(required = false) String systemId) {
        return Result.result(commonServiceImpl.queryDeviceAndParam(projectId, systemId));
    }

}
