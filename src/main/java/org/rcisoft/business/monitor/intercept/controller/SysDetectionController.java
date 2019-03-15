package org.rcisoft.business.monitor.intercept.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.monitor.intercept.service.SysDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:01 2019/3/13
 */
@Api(tags = "系统检测")
@RestController
@RequestMapping("SysDetection")
public class SysDetectionController {
    @Autowired
    private SysDetectionService sysDetectionService;

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



}
