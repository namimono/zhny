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

    @ApiOperation(value = "查询设备参数",notes = "查询设备参数")
    @GetMapping(value = "queryDeviceParam/{id}")
    public Result queryDeviceParam(@PathVariable String id){return Result.result(1,busProjectService.queryDeviceParam(id));}
}
