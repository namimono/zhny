package org.rcisoft.business.monitor.intercept.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.monitor.intercept.dao.DeviceParamDao;
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


    @ApiOperation(value = "查询网关",notes = "查询网关")
    @GetMapping(value = "/queryPhones/{id}")
    Result queryPhones(@PathVariable String id){
        return Result.result(1,busProjectService.queryPhones(id));
    }

    @ApiOperation(value = "获取拓扑图json",notes = "获取拓扑图json")
    @RequestMapping(value = "topoJson",method = RequestMethod.GET)
    public Result queryTopoJson(){
        return Result.result(1,sysDetectionService.queryTopoJson());
    }

    @ApiOperation(value = "获取标签",notes = "获取标签")
    @RequestMapping(value = "busTitle",method = RequestMethod.GET)
    public Result queryBusTitle(){
        return Result.result(1,sysDetectionService.queryBusTitle());
    }

    @ApiOperation(value = "获取项目参数",notes = "获取项目参数")
    @GetMapping(value = "queryParam/{id}")
    public Result queryParam(@PathVariable String id){
        return Result.result(1,busProjectService.queryParam(id));
    }

    @ApiOperation(value = "查询设备一二级参数及二级名称",notes = "查询设备一二级参数及二级名称")
    @GetMapping(value = "queryDeviceParam/{id}")
    public Result queryDeviceParam(@PathVariable String id){
        return Result.result(1,busProjectService.queryDeviceParam(id));
    }

    @ApiOperation(value = "查询设备列表标题",notes = "查询设备列表标题")
    @GetMapping(value = "queryDeviceTitle")
    public Result queryDeviceTitle(){
        return Result.result(1,busProjectService.queryDeviceTitle());
    }

    @ApiOperation(value = "获取设备具体信息",notes = "获取设备具体信息")
    @GetMapping(value = "queryDeviceInfo/{typeFirstId}")
    public Result queryDeviceInfo(@PathVariable String typeFirstId){
        return Result.result(1,busProjectService.queryDeviceInfo(typeFirstId));
    }

    @ApiOperation(value = "左侧边栏模块名称",notes = "左侧边栏模块名称")
    @GetMapping(value = "queryModelName")
    public Result queryModelName(){
        return Result.result(1,busProjectService.queryModelName());
    }

    @ApiOperation(value = "查询echarts图数据",notes = "查询echarts图数据")
    @GetMapping(value = "EnergyEchart/{titleId}")
    public Result EnergyEchart(@PathVariable String titleId){
        return Result.result(1,busProjectService.EnergyEchart(titleId));
    }

    @ApiOperation(value = "查询能源参数",notes = "查询能源参数")
    @GetMapping(value = "EnergyParam/{deviceId}")
    public Result EnergyParam(@PathVariable String deviceId){
        return Result.result(1,busProjectService.queryEnergyParam(deviceId));
    }

    @ApiOperation(value = "查询设备固定参数",notes = "查询设备固定参数")
    @GetMapping(value = "DeviceFixValue/{deviceId}")
    public Result queryDeviceFixValue(@PathVariable String deviceId){
        return Result.result(1,busProjectService.queryDeviceFixValue(deviceId));
    }
    
    @ApiOperation(value = "查询设备实时参数，并判断状态",notes = "查询设备实时参数，并判断状态")
    @GetMapping(value = "DeviceDetail/{deviceId}")
    public Result queryDeviceDetail(@PathVariable String deviceId) {
    	return Result.result(1, busProjectService.queryDeviceDetail(deviceId));
    }

    @ApiOperation(value = "获取项目id，取出json串",notes = "获取项目id，取出json串")
    @GetMapping(value = "getProId/{proId}")
    public Result getProId(@PathVariable String proId){
        return  Result.result(1,busProjectService.queryJsonByProId(proId));
    }

}
