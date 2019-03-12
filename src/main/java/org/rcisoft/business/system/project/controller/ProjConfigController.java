package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.rcisoft.entity.BusProjectSaving;
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

    @ApiOperation(value="获取全部项目表信息", notes="获取全部项目表信息")
    @GetMapping("/queryAllInfo")
    public Result queryAllInfo(){
        return Result.result(projConfigServiceImpl.queryAllInfo());
    }

    @ApiOperation(value="获取项目简要信息", notes="获取项目简要信息")
    @GetMapping("/queryBriefInfo")
    public Result queryBriefInfo(){
        return Result.result(projConfigServiceImpl.queryBriefInfo());
    }

    @ApiOperation(value="修改项目配置信息", notes="修改项目配置信息")
    @PostMapping("/updateProjConfig")
    public Result updateProjConfig(@RequestBody BusProject busProject){
        return Result.result(1, projConfigServiceImpl.updateProjConfig(busProject));
    }

    @ApiOperation(value="新增项目配置信息", notes="新增项目配置信息")
    @PostMapping("/addProjConfig")
    public Result addProjConfig(@RequestBody BusProject busProject){
        return Result.result(1, projConfigServiceImpl.addProjConfig(busProject));
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
        return Result.result(1, projConfigServiceImpl.insertBuildType(busBuilding));
    }

    @ApiOperation(value="修改建筑类型", notes="修改建筑类型")
    @PutMapping("/updateBuildType")
    public Result updateBuildType(@RequestBody BusBuilding busBuilding){
        return Result.result(1, projConfigServiceImpl.updateBuildType(busBuilding));
    }

    @ApiOperation(value="删除建筑类型", notes="删除建筑类型")
    @DeleteMapping("/deleteBuildType")
    public Result deleteBuildType(@RequestBody BusBuilding busBuilding){
        return Result.result(1, projConfigServiceImpl.deleteBuildType(busBuilding));
    }

    @ApiOperation(value="新增建筑分区(气候分区)", notes="新增建筑分区(气候分区)")
    @DeleteMapping("/insertBuildZone")
    public Result insertBuildZone(@RequestBody BusBuildingZone busBuildingZone){
        return Result.result(1, projConfigServiceImpl.insertBuildZone(busBuildingZone));
    }

    @ApiOperation(value="修改建筑分区(气候分区)", notes="修改建筑分区(气候分区)")
    @PutMapping("/updateBuildZone")
    public Result updateBuildZone(@RequestBody BusBuildingZone busBuildingZone){
        return Result.result(1, projConfigServiceImpl.updateBuildZone(busBuildingZone));
    }

    @ApiOperation(value="删除建筑分区(气候分区)", notes="删除建筑分区(气候分区)")
    @DeleteMapping("/deleteBuildZone")
    public Result deleteBuildZone(@RequestBody BusBuildingZone busBuildingZone){
        return Result.result(1, projConfigServiceImpl.deleteBuildZone(busBuildingZone));
    }

    @ApiOperation(value="新增节能改造信息", notes="新增节能改造信息")
    @PostMapping("/addProjectSaving")
    public Result addProjectSaving(@RequestBody BusProjectSaving busProjectSaving){
        return Result.result(1, projConfigServiceImpl.addProjectSaving(busProjectSaving));
    }

    @ApiOperation(value="修改节能改造信息", notes="修改节能改造信息")
    @PutMapping("/updateProjectSaving")
    public Result updateProjectSaving(@RequestBody BusProjectSaving busProjectSaving){
        return Result.result(1, projConfigServiceImpl.updateProjectSaving(busProjectSaving));
    }
}
