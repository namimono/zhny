package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.entity.IndoorContainParam;
import org.rcisoft.business.system.project.service.InOutdoorConfigService;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;
import org.rcisoft.entity.BusOutdoor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/11 16:45
 **/
@Api(tags = "设备配置-室内/外环境配置")
@RestController
@RequestMapping("inOutdoorConfig")
public class InOutdoorConfigController {

    @Autowired
    private InOutdoorConfigService inOutdoorConfigServiceImpl;

    @ApiOperation(value="新增室内环境信息和室内环境参数", notes="新增室内环境信息和室内环境参数")
    @PostMapping("/addIndoorInfo/{proId}")
    public Result addIndoorInfo(@RequestBody List<IndoorContainParam> list, @PathVariable String proId){
        return Result.serviceResult(inOutdoorConfigServiceImpl.addIndoorInfo(list,proId),"新增室内环境信息成功","新增室内环境信息失败");
    }

    @ApiOperation(value="删除室内环境信息", notes="删除室内环境信息")
    @DeleteMapping("/deleteIndoorInfo/{indoorId}")
    public Result deleteIndoorInfo(@PathVariable String indoorId){
        return Result.result(inOutdoorConfigServiceImpl.deleteIndoorInfo(indoorId),"删除室内环境信息成功","删除室内环境信息失败");
    }

    @ApiOperation(value="修改室内环境信息和室内环境参数", notes="修改室内环境信息和室内环境参数")
    @PutMapping("/updateIndoorInfo")
    public Result updateIndoorInfo(@RequestBody List<IndoorContainParam> list){
        return Result.result(inOutdoorConfigServiceImpl.updateIndoorInfo(list),"修改室内环境信息成功","修改室内环境信息失败");
    }

    @ApiOperation(value="查询室内环境信息", notes="查询室内环境信息")
    @GetMapping("/queryIndoorInfo/{projectId}")
    public Result queryIndoorInfo (@PathVariable String projectId){
        return Result.result(inOutdoorConfigServiceImpl.queryIndoorInfo(projectId));
    }

    @ApiOperation(value="查询室内环境参数", notes="查询室内环境参数")
    @GetMapping("/queryIndoorParamInfo/{indoorId}")
    public Result queryIndoorParamInfo (@PathVariable String indoorId){
        return Result.result(inOutdoorConfigServiceImpl.queryIndoorParamInfo(indoorId));
    }

    @ApiOperation(value="新增室外配置", notes="新增室外配置")
    @PostMapping("/addOutdoorInfo")
    public Result addOutdoorInfo(@RequestBody BusOutdoor busOutdoor){
        return Result.result(inOutdoorConfigServiceImpl.addOutdoorInfo(busOutdoor),"新增室外配置成功","新增室外配置失败");
    }

    @ApiOperation(value="修改室外配置", notes="修改室外配置")
    @PutMapping("/updateOutdoorInfo")
    public Result updateOutdoorInfo(@RequestBody BusOutdoor busOutdoor){
        return Result.result(inOutdoorConfigServiceImpl.updateOutdoorInfo(busOutdoor),"修改室外配置成功","修改室外配置失败");
    }

    @ApiOperation(value="查询室外配置", notes="查询室外配置")
    @GetMapping("/queryOutdoorInfo/{projectId}")
    public Result queryOutdoorInfo (@PathVariable String projectId){
        return Result.result(inOutdoorConfigServiceImpl.queryOutdoorInfo(projectId));
    }

}
