package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.aop.PageAop;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.business.system.project.service.DataConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 14:51
 **/
@Api(tags = "项目编辑-数据配置")
@RestController
@RequestMapping("dataConfig")
public class DataConfigController extends PageAop {

    @Autowired
    private DataConfigService dataConfigServiceImpl;

    @ApiOperation(value="查询系统类型信息", notes="查询系统类型信息")
    @GetMapping("/querySysSystemInfo")
    public Result querySysSystemInfo(){
        return Result.result(dataConfigServiceImpl.querySysSystemInfo());
    }

    @ApiOperation(value="查询一级参数信息", notes="查询一级参数信息")
    @GetMapping("/queryParamFirstInfo")
    public Result queryParamFirstInfo(BusParamFirst busParamFirst){
        return Result.result(1, dataConfigServiceImpl.queryParamFirstInfo(busParamFirst));
    }

    @ApiOperation(value="新增一级参数信息", notes="新增一级参数信息")
    @PostMapping("/addParamFirstInfo")
    public Result addParamFirstInfo(@RequestBody BusParamFirst busParamFirst){
        return Result.result(1, dataConfigServiceImpl.addParamFirstInfo(busParamFirst));
    }

    @ApiOperation(value="修改一级参数信息", notes="修改一级参数信息")
    @PostMapping("/updateParamFirstInfo")
    public Result updateParamFirstInfo(@RequestBody BusParamFirst busParamFirst){
        return Result.result(1, dataConfigServiceImpl.updateParamFirstInfo(busParamFirst));
    }

    @ApiOperation(value="查询二级参数信息", notes="查询二级参数信息")
    @GetMapping("/queryParamSecondInfo")
    public Result queryParamSecondInfo(BusParamSecond busParamSecond){
        return Result.result(1, dataConfigServiceImpl.queryParamSecondInfo(busParamSecond));
    }

    @ApiOperation(value="新增二级参数信息", notes="新增二级参数信息")
    @PostMapping("/addParamSecondInfo")
    public Result addParamSecondInfo(@RequestBody List<BusParamSecond> list){
        return Result.result(1, dataConfigServiceImpl.addParamSecondInfo(list));
    }

    @ApiOperation(value="修改二级参数信息", notes="修改二级参数信息")
    @PostMapping("/updateParamSecondInfo")
    public Result updateParamSecondInfo(@RequestBody List<BusParamSecond> list){
        return Result.result(1, dataConfigServiceImpl.updateParamSecondInfo(list));
    }

    @ApiOperation(value="数据配置联表同时查询一级、二级参数信息", notes="数据配置联表同时查询一级、二级参数信息")
    @GetMapping("/queryDataParamForPage")
    public Result queryDataParamForPage(@RequestParam("projectId") String projectId){
        return Result.result(dataConfigServiceImpl.queryDataParamForPage(projectId));
    }
}
