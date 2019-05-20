package org.rcisoft.business.equipment.device.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/3/26.
 * 设备维护--设备管理
 */
@Api(tags = "设备维护--设备管理")
@RestController
@RequestMapping("device")
public class DeviceController {

    @Autowired
    DeviceService deviceServiceImpl;

    @ApiOperation(value="资产统计", notes="查询设备列表")
    @GetMapping("/queryDevices/{projectId}/{deviceTypeId}")
    public Result queryDevices(@PathVariable String projectId, @PathVariable String deviceTypeId) {
        return Result.result(deviceServiceImpl.queryDevices(projectId, deviceTypeId));
    }

    @ApiOperation(value="巡检记录", notes="查询巡检列表")
    @PostMapping("/queryInspectionForPage")
    public Result queryInspectionForPage(@RequestParam String deviceId, @RequestParam Integer year, @RequestParam Integer month) {
        return Result.result(deviceServiceImpl.queryInspectionForPage(deviceId, year, month));
    }

}
