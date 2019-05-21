package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.entity.ProjectConfigInfo;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:54
 **/

@Api(tags = "项目编辑-项目配置")
@RestController
@RequestMapping("projConfig")
public class ProjConfigController {

    @Autowired
    private ProjConfigService projConfigServiceImpl;

//    @ApiOperation(value="获取全部项目表信息", notes="获取全部项目表信息")
//    @GetMapping("/queryAllInfo")
//    public Result queryAllInfo(){
//        return Result.result(projConfigServiceImpl.queryAllInfo());
//    }

    @ApiOperation(value="获取项目简要信息", notes="获取项目简要信息")
    @GetMapping("/queryBriefInfo/{online}")
    public Result queryBriefInfo(@PathVariable Integer online){
        return Result.result(projConfigServiceImpl.queryBriefInfo(online));
    }

    @ApiOperation(value="修改项目配置信息", notes="修改项目配置信息")
    @PostMapping("/updateProjConfig")
    public Result updateProjConfig(@RequestBody ProjectConfigInfo projectConfigInfo){
        return Result.result(projConfigServiceImpl.updateProjConfig(projectConfigInfo),"修改项目配置信息成功","修改项目配置信息失败");
    }

    @ApiOperation(value="新增项目配置信息和节能改造信息", notes="新增项目配置信息和节能改造信息")
    @PostMapping("/addProjConfig")
    public Result addProjConfig(@RequestBody ProjectConfigInfo projectConfigInfo){
        return Result.serviceResult(projConfigServiceImpl.addProjConfig(projectConfigInfo), "新增项目配置信息成功", "新增项目配置信息失败");
    }

    @ApiOperation(value="获取省份信息及其ID", notes="获取省份信息及其ID")
    @GetMapping("/queryProvinceInfo")
    public Result queryProvinceInfo(){
        return Result.result(projConfigServiceImpl.queryProvinceInfo());
    }

//    @ApiOperation(value="根据省份ID获取城市信息及其code", notes="根据省份ID获取城市信息及其code")
//    @GetMapping("/queryCityInfo")
//    public Result queryCityInfo(@RequestParam("provinceId") String provinceId){
//        return Result.result(1, projConfigServiceImpl.queryCityInfo(provinceId));
//    }

    @ApiOperation(value="处理省份、城市及其code信息的格式", notes="处理省份、城市及其code信息的格式")
    @GetMapping("/processingFormat")
    public Result processingFormat(){
        return Result.result(projConfigServiceImpl.processingFormat());
    }

    @ApiOperation(value="获取线下团队信息", notes="获取线下团队信息")
    @GetMapping("/queryOutTeamInfo")
    public Result queryOutTeamInfo(){
        return Result.result(projConfigServiceImpl.queryOutTeamInfo());
    }

    @ApiOperation(value="获取线上团队信息", notes="获取线上团队信息")
    @GetMapping("/queryOnTeamInfo")
    public Result queryOnTeamInfo(){
        return Result.result(projConfigServiceImpl.queryOnTeamInfo());
    }

    @ApiOperation(value="获取巡查员信息", notes="获取巡查员信息")
    @GetMapping("/queryInspectorInfo")
    public Result queryInspectorInfo(){
        return Result.result(projConfigServiceImpl.queryInspectorInfo());
    }

    @ApiOperation(value="获取建筑类型信息", notes="获取建筑类型信息")
    @GetMapping("/queryBuildingInfo")
    public Result queryBuildingInfo(){
        return Result.result(projConfigServiceImpl.queryBuildingInfo());
    }

    @ApiOperation(value="获取建筑分区(气候分区)信息", notes="获取建筑分区(气候分区)信息")
    @GetMapping("/queryBuildZoneInfo")
    public Result queryBuildZoneInfo(){
        return Result.result(projConfigServiceImpl.queryBuildZoneInfo());
    }

    @ApiOperation(value="获取业主信息", notes="获取业主信息")
    @GetMapping("/queryOwnerInfo")
    public Result queryOwnerInfo(){
        return Result.result(projConfigServiceImpl.queryOwnerInfo());
    }

    @ApiOperation(value="新增建筑类型", notes="新增建筑类型")
    @PostMapping("/insertBuildType")
    public Result insertBuildType(@RequestBody BusBuilding busBuilding){
        return Result.result(projConfigServiceImpl.insertBuildType(busBuilding), "新增建筑类型成功", "新增建筑类型失败");
    }

    @ApiOperation(value="修改建筑类型", notes="修改建筑类型")
    @PutMapping("/updateBuildType")
    public Result updateBuildType(@RequestBody BusBuilding busBuilding){
        return Result.result(projConfigServiceImpl.updateBuildType(busBuilding), "修改建筑类型成功", "修改建筑类型失败");
    }

    @ApiOperation(value="删除建筑类型", notes="删除建筑类型")
    @DeleteMapping("/deleteBuildType")
    public Result deleteBuildType(@RequestBody BusBuilding busBuilding){
        return Result.result(projConfigServiceImpl.deleteBuildType(busBuilding), "删除建筑类型成功", "删除建筑类型失败");
    }

    @ApiOperation(value="新增建筑分区(气候分区)", notes="新增建筑分区(气候分区)")
    @PostMapping("/insertBuildZone")
    public Result insertBuildZone(@RequestBody BusBuildingZone busBuildingZone){
        return Result.result(projConfigServiceImpl.insertBuildZone(busBuildingZone), "新增气候分区成功", "新增气候分区失败");
    }

    @ApiOperation(value="修改建筑分区(气候分区)", notes="修改建筑分区(气候分区)")
    @PutMapping("/updateBuildZone")
    public Result updateBuildZone(@RequestBody BusBuildingZone busBuildingZone){
        return Result.result(projConfigServiceImpl.updateBuildZone(busBuildingZone), "修改气候分区成功", "修改气候分区失败");
    }

    @ApiOperation(value="删除建筑分区(气候分区)", notes="删除建筑分区(气候分区)")
    @DeleteMapping("/deleteBuildZone")
    public Result deleteBuildZone(@RequestBody BusBuildingZone busBuildingZone){
        return Result.result(projConfigServiceImpl.deleteBuildZone(busBuildingZone), "删除建筑分区(气候分区)成功", "删除建筑分区(气候分区)失败");
    }

    @ApiOperation(value="查询认定员信息", notes="查询认定员信息")
    @GetMapping("/queryAuthenticator")
    public Result queryAuthenticator(){
        return Result.result(projConfigServiceImpl.queryAuthenticator());
    }

    @ApiOperation(value="项目表、节能改造表联查编辑回显", notes="项目表、节能改造表联查编辑回显")
    @GetMapping("/queryProjectConfigInfo/{projectId}")
    public Result queryProjectConfigInfo(@PathVariable String projectId){
        return Result.result(projConfigServiceImpl.queryProjectConfigInfo(projectId));
    }

    @ApiOperation(value="删除项目信息(谨慎!)", notes="删除项目信息(谨慎!)")
    @DeleteMapping("/deleteAllByProId/{projectId}")
    public Result deleteAllByProId(@PathVariable String projectId){
        int i = projConfigServiceImpl.deleteAllByProId(projectId);
        return Result.result(i,"删除项目信息成功","删除项目信息失败");
    }

    @ApiOperation(value="查询系统类型", notes="查询系统类型")
    @GetMapping("/querySystemType")
    public Result querySystemType(){
        return Result.result(projConfigServiceImpl.querySystemType());
    }

    @ApiOperation(value="新增系统类型", notes="新增系统类型")
    @PostMapping("/addSysSystem")
    public Result addSysSystem(@RequestBody SysSystem sysSystem){
        return Result.result(projConfigServiceImpl.addSysSystem(sysSystem), "新增系统类型成功", "新增系统类型失败");
    }

    @ApiOperation(value="删除系统类型", notes="删除系统类型")
    @DeleteMapping("/deleteSysSystem/{systemId}")
    public Result deleteSysSystem(@PathVariable String systemId){
        return Result.result(projConfigServiceImpl.deleteSysSystem(systemId), "删除系统类型成功", "删除系统类型失败");
    }

    @ApiOperation(value="修改系统类型", notes="修改系统类型")
    @PutMapping("/updateSysSystem")
    public Result updateSysSystem(@RequestBody SysSystem sysSystem){
        return Result.result(projConfigServiceImpl.updateSysSystem(sysSystem), "修改系统类型成功", "修改系统类型失败");
    }
    @ApiOperation(value="修改项目接收状态和在线状态", notes="修改项目接收状态和在线状态")
    @PutMapping("/updateProjectReceiveAndOnline")
    public Result updateProjectReceiveAndOnline(@RequestBody ProjectConfigInfo projectConfigInfo){
        return Result.result(projConfigServiceImpl.updateProjectReceiveAndOnline(projectConfigInfo));
    }
}
