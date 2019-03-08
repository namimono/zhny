package org.rcisoft.controller.ProjManagement.projMaintenance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;
import org.rcisoft.service.ProjManagement.TeamMaintenance.ProjectConfigService;
import org.rcisoft.service.sysManagement.projMaintenance.ProjConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:54
 **/

@Api(tags = "项目编辑-项目配置")
@RestController
@RequestMapping("projectConfig")
public class ProjectConfigController {

    @Autowired
    private ProjectConfigService projConfigServiceImpl;



    @ApiOperation(value="获取线上团队及其负责人信息", notes="获取线上团队及其负责人信息")
    @RequestMapping(value = "/queryAllOnTeamInfo",method = RequestMethod.GET)
    public Result queryAllOnTeamInfo(){
        return Result.result(1, projConfigServiceImpl.queryAllOnTeamInfo());
    }

    @ApiOperation(value="获取线下团队及其负责人信息", notes="获取线下团队及其负责人信息")
    @RequestMapping(value = "/queryAllOutTeamInfo",method = RequestMethod.GET)
    public Result queryAllOutTeamInfo(){
        return Result.result(1, projConfigServiceImpl.queryAllOutTeamInfo());
    }

    @ApiOperation(value = "获取所有关于项目的信息",notes = "获取所有关于项目的信息")
    @RequestMapping(value = "/queryAllProjInfo",method = RequestMethod.GET)
    public Result queryAllProjInfo(){
        return  Result.result(1,projConfigServiceImpl.queryAllProjInfo());
    }
}
