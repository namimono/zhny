package org.rcisoft.controller.sysManagement.projMaintenance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.BusProject;
import org.rcisoft.service.projMaintenance.ProjConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        return Result.result(1, projConfigServiceImpl.queryAllInfo());
    }

    @ApiOperation(value="设置项目配置信息", notes="设置项目配置信息")
    @PostMapping("/setProjConfig")
    public Result setProjConfig(@RequestBody BusProject busProject){
        return Result.result(1, projConfigServiceImpl.setProjConfig(busProject));
    }

    @ApiOperation(value="获取省份信息及其ID", notes="获取省份信息及其ID")
    @GetMapping("/queryProvinceInfo")
    public Result queryProvinceInfo(){
        return Result.result(1, projConfigServiceImpl.queryProvinceInfo());
    }

    @ApiOperation(value="根据省份ID获取城市信息及其code", notes="根据省份ID获取城市信息及其code")
    @GetMapping("/queryCityInfo")
    public Result queryCityInfo(@RequestParam("provinceId") String provinceId){
        return Result.result(1, projConfigServiceImpl.queryCityInfo(provinceId));
    }

    @ApiOperation(value="获取线下团队信息", notes="获取线下团队信息")
    @GetMapping("/queryOutTeamInfo")
    public Result queryOutTeamInfo(){
        return Result.result(1, projConfigServiceImpl.queryOutTeamInfo());
    }

    @ApiOperation(value="获取线上团队信息", notes="获取线上团队信息")
    @GetMapping("/queryOnTeamInfo")
    public Result queryOnTeamInfo(){
        return Result.result(1, projConfigServiceImpl.queryOnTeamInfo());
    }

    @ApiOperation(value="获取巡查员信息", notes="获取巡查员信息")
    @GetMapping("/queryInspectorInfo")
    public Result queryInspectorInfo(){
        return Result.result(1, projConfigServiceImpl.queryInspectorInfo());
    }

    @ApiOperation(value="获取建筑类型信息", notes="获取建筑类型信息")
    @GetMapping("/queryBuildingInfo")
    public Result queryBuildingInfo(){
        return Result.result(1, projConfigServiceImpl.queryBuildingInfo());
    }

    @ApiOperation(value="获取建筑分区(气候分区)信息", notes="获取建筑分区(气候分区)信息")
    @GetMapping("/queryBuildZoneInfo")
    public Result queryBuildZoneInfo(){
        return Result.result(1, projConfigServiceImpl.queryBuildZoneInfo());
    }

    @ApiOperation(value="获取业主信息", notes="获取业主信息")
    @GetMapping("/queryOwnerInfo")
    public Result queryOwnerInfo(){
        return Result.result(1, projConfigServiceImpl.queryOwnerInfo());
    }
}
