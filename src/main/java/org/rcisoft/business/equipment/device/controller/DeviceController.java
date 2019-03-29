package org.rcisoft.business.equipment.device.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/queryDevices/{projectId}/{typeFirstId}")
    public Result queryDevices(@PathVariable String projectId, @PathVariable String typeFirstId) {
        return Result.result(deviceServiceImpl.queryDevices(projectId, typeFirstId));
    }

    @ApiOperation(value="巡检记录", notes="查询巡检列表")
    @GetMapping("/queryInspectionForPage/{deviceId}/{year}/{month}")
    public Result queryInspectionForPage(@PathVariable String deviceId, @PathVariable Integer year, @PathVariable Integer month) {
        return Result.result(deviceServiceImpl.queryInspectionForPage(deviceId, year, month));
    }

}
