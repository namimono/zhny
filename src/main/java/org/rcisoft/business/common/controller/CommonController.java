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

    @ApiOperation(value="查询一级设备类型", notes="查询本项目中所有存在的一级设备类型")
    @GetMapping("/queryTypeFirst/{projectId}")
    public Result queryTypeFirst(@PathVariable String projectId) {
        return Result.result(commonServiceImpl.queryTypeFirst(projectId));
    }

    @ApiOperation(value="查询项目拥有的系统类型", notes="查询项目拥有的系统类型")
    @GetMapping("/querySystemForProject/{projectId}")
    public Result querySystemForProject(@PathVariable String projectId) {
        return Result.result(commonServiceImpl.querySystemForProject(projectId));
    }

    @ApiOperation(value="查询项目下所有设备", notes="typeFirstId：一级设备类型id，非必填")
    @PostMapping("/queryDevices")
    public Result queryDevices(@RequestParam String projectId, @RequestParam(required = false) String typeFirstId) {
        return Result.result(commonServiceImpl.queryDevices(projectId, typeFirstId));
    }

    @ApiOperation(value="查询项目所有的一级参数", notes="查询项目所有的一级参数")
    @GetMapping("/queryParamFirsts/{projectId}")
    public Result queryParamFirsts(@PathVariable String projectId) {
        return Result.result(commonServiceImpl.queryParamFirsts(projectId));
    }

    @ApiOperation(value="查询一级参数的二级参数", notes="查询一级参数的二级参数")
    @GetMapping("/queryParamSeconds/{paramFirstId}")
    public Result queryParamSeconds(@PathVariable String paramFirstId) {
        return Result.result(commonServiceImpl.queryParamSeconds(paramFirstId));
    }

}
