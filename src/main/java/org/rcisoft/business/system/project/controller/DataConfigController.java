package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.aop.PageAop;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.business.system.project.service.DataConfigService;
import org.rcisoft.entity.EnergyConfig;
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

    @ApiOperation(value="根据项目ID查询一级参数信息", notes="根据项目ID查询一级参数信息")
    @GetMapping("/queryParamFirstInfo/{projectId}")
    public Result queryParamFirstInfo(@PathVariable String projectId){
        BusParamFirst busParamFirst = new BusParamFirst();
        busParamFirst.setProjectId(projectId);
        return Result.result(dataConfigServiceImpl.queryParamFirstInfo(busParamFirst));
    }

    @ApiOperation(value="新增一级参数信息", notes="新增一级参数信息")
    @PostMapping("/addParamFirstInfo")
    public Result addParamFirstInfo(@RequestBody BusParamFirst busParamFirst){
        return Result.serviceResult(dataConfigServiceImpl.addParamFirstInfo(busParamFirst),"新增一级参数信息成功","新增一级参数信息失败");
    }

    @ApiOperation(value="修改一级参数信息", notes="修改一级参数信息")
    @PutMapping("/updateParamFirstInfo")
    public Result updateParamFirstInfo(@RequestBody BusParamFirst busParamFirst){
        return Result.result(dataConfigServiceImpl.updateParamFirstInfo(busParamFirst),"修改一级参数信息成功","修改一级参数信息失败");
    }

    @ApiOperation(value="查询二级参数信息", notes="查询二级参数信息")
    @GetMapping("/queryParamSecondInfo/{paramFirstId}/{projectId}/{systemId}")
    public Result queryParamSecondInfo(@PathVariable String paramFirstId,@PathVariable String projectId,@PathVariable String systemId){
        BusParamSecond busParamSecond = new BusParamSecond();
        busParamSecond.setParamFirstId(paramFirstId);
        busParamSecond.setProjectId(projectId);
        busParamSecond.setSystemId(systemId);
        return Result.result(dataConfigServiceImpl.queryParamSecondInfo(busParamSecond));
    }

    @ApiOperation(value="新增二级参数信息", notes="新增二级参数信息")
    @PostMapping("/addParamSecondInfo")
    public Result addParamSecondInfo(@RequestBody List<BusParamSecond> list){
        return Result.result(dataConfigServiceImpl.addParamSecondInfo(list),"新增二级参数信息成功","新增二级参数信息失败");
    }

    @ApiOperation(value="修改二级参数信息", notes="修改二级参数信息")
    @PutMapping("/updateParamSecondInfo")
    public Result updateParamSecondInfo(@RequestBody List<BusParamSecond> list){
        return Result.result(dataConfigServiceImpl.updateParamSecondInfo(list),"修改二级参数信息成功","修改二级参数信息失败");
    }

    @ApiOperation(value="数据配置联表同时查询一级、二级参数信息", notes="数据配置联表同时查询一级、二级参数信息")
    @GetMapping("/queryDataParamForPage/{projectId}")
    public Result queryDataParamForPage(@PathVariable String projectId){
        return Result.result(dataConfigServiceImpl.queryDataParamForPage(projectId));
    }

    @ApiOperation(value="增加能源配置信息", notes="增加能源配置信息")
    @PostMapping("/addEnergyConfig")
    public Result addEnergyConfig(@RequestBody EnergyConfig energyConfig){
        return Result.result(dataConfigServiceImpl.addEnergyConfig(energyConfig),"增加能源配置信息成功","增加能源配置信息失败");
    }

    @ApiOperation(value="删除能源配置信息", notes="删除能源配置信息")
    @DeleteMapping("/deleteEnergyConfig")
    public Result deleteEnergyConfig(@RequestBody EnergyConfig energyConfig){
        return Result.result(dataConfigServiceImpl.deleteEnergyConfig(energyConfig),"删除能源配置信息成功","删除能源配置信息失败");
    }

    @ApiOperation(value="删除一级参数信息", notes="删除一级参数信息")
    @DeleteMapping("/deleteParamFirst/{id}")
    public Result deleteParamFirst(@PathVariable String id){
        return Result.result(dataConfigServiceImpl.deleteParamFirst(id),"删除一级参数信息成功","删除一级参数信息失败");
    }

    @ApiOperation(value="删除二级参数信息", notes="删除二级参数信息")
    @DeleteMapping("/deleteParamSecond/{id}")
    public Result deleteParamSecond(@PathVariable String id){
        return Result.result(dataConfigServiceImpl.deleteParamSecond(id),"删除二级参数信息成功","删除二级参数信息失败");
    }
}
