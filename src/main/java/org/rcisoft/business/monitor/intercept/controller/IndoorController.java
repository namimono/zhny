package org.rcisoft.business.monitor.intercept.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.monitor.intercept.service.IndoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:49 2019/3/29
 */
@Api(tags = "系统检测-室内环境")
@RestController
@RequestMapping("indoor")
public class IndoorController {

    @Autowired
    private IndoorService indoorService;

    @ApiOperation(value = "查询所有楼层",notes = "查询所有楼层")
    @GetMapping(value = "queryFloor")
    public Result queryFloor(){
        return Result.result(1,indoorService.queryFloor());
    }

    @ApiOperation(value = "根据楼层查询房间",notes = "根据楼层查询房间")
    @GetMapping(value = "queryDoor/{floor}")
    public Result queryDoor(@PathVariable int floor){
        return Result.result(1,indoorService.queryDoor(floor));
    }

    @ApiOperation(value = "查询室内环境参数",notes = "查询室内环境参数")
    @GetMapping(value = "queryBusIndoorParam/{indoorParam}/{proId}/{coding}")
    public Result queryBusIndoorParam(@PathVariable String indoorParam,@PathVariable String proId,@PathVariable String coding){
        return Result.result(1,indoorService.queryIndoorParam(indoorParam,proId,coding));
    }

    @ApiOperation(value = "查询一二级参数24小时",notes = "查询一二级参数24小时")
    @GetMapping(value = "queryParamHour/{proId}/{type}/{coding}/{indoorId}")
    public Result queryParamHour(@PathVariable String proId,@PathVariable int type,@PathVariable String coding,@PathVariable String indoorId){
        return Result.result(1,indoorService.queryJsonIndoor(proId,type,coding,indoorId));
    }

    @ApiOperation(value = "月度环境参数对比",notes = "月度环境参数对比")
    @GetMapping(value = "monthParamContrast/{proId}/{type}/{coding}/{year}/{month}/{indoor}")
    public Result monthParamContrast(@PathVariable String proId,@PathVariable int type,@PathVariable String coding,@PathVariable int year,@PathVariable int month,@PathVariable String indoor){
        return Result.result(1,indoorService.MonthParamContrast(proId,type,coding,year,month,indoor));
    }
}
