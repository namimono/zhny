package org.rcisoft.business.monitor.intercept.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.monitor.intercept.service.BusProjectService;
import org.rcisoft.business.monitor.intercept.service.SysDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:05 2019/3/18
 */
@Api(tags = "系统监测")
@RestController
@RequestMapping("BusProject")
public class BusProjectController {

    @Autowired
    private BusProjectService busProjectService;
    @Autowired
    private SysDetectionService sysDetectionService;

    @ApiOperation(value = "获取设备具体信息",notes = "获取设备具体信息")
    @GetMapping(value = "queryDeviceInfo/{typeFirstId}/{projectId}/{systemId}")
    public Result queryDeviceInfo(@PathVariable("typeFirstId") String typeFirstId, @PathVariable("projectId") String projectId, @PathVariable("systemId") String systemId){
        return Result.result(1,busProjectService.queryDeviceInfo(typeFirstId,projectId,systemId));
    }

    @ApiOperation(value = "查询echarts图数据",notes = "查询echarts图数据")
    @GetMapping(value = "EnergyEchart/{titleId}")
    public Result EnergyEchart(@PathVariable String titleId){
        return Result.result(1,busProjectService.EnergyEchart(titleId));
    }

    @ApiOperation(value = "获取标签",notes = "获取标签")
    @GetMapping(value = "busTitle/{projectId}/{systemId}")
    public Result queryBusTitle(@PathVariable("projectId") String projectId, @PathVariable("systemId") String systemId){
        return Result.result(1,sysDetectionService.queryBusTitle(projectId,systemId));
    }

    @ApiOperation(value = "根据项目id、系统id查询拓扑图中每个设备表示电量的参数",notes = "返回设备id，一级参数code，二级参数code，电度：0（默认） or 功率：1")
    @GetMapping(value = "queryDeviceElec/{projectId}/{systemId}")
    public Result queryDeviceElec(@PathVariable String projectId, @PathVariable String systemId) {
        return Result.result(busProjectService.queryDeviceElec(projectId, systemId));
    }

    @ApiOperation(value = "查询设备下部分参数信息",notes = "参数，deviceId：设备id；count：查询的数量")
    @GetMapping(value = "queryParams/{deviceId}/{count}")
    public Result queryParams(@PathVariable String deviceId, @PathVariable Integer count) {
        return Result.result(busProjectService.queryParams(deviceId, count));
    }

    @ApiOperation(value = "查询设备下所有参数信息",notes = "参数，deviceId：设备id")
    @GetMapping(value = "queryParamsAll/{deviceId}")
    public Result queryParamsAll(@PathVariable String deviceId) {
        return Result.result(busProjectService.queryParamsAll(deviceId));
    }

    @ApiOperation(value = "根据项目id查询实时数据",notes = "参数，projectId：项目id")
    @GetMapping(value = "queryCacheData/{projectId}")
    public Result queryCacheData(@PathVariable String projectId) {
        return Result.result(busProjectService.queryCacheData(projectId));
    }

}
