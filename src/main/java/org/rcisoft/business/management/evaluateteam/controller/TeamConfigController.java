package org.rcisoft.business.management.evaluateteam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.management.evaluateproj.service.ProConfigService;
import org.rcisoft.business.management.evaluateteam.service.TeamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Minghui Xu
 * Time：2019/3/4 14:54
 **/

@Api(tags = "团队管理")
@RestController
@RequestMapping("teamConfig")
public class TeamConfigController {

    @Autowired
    private TeamConfigService teamConfigServiceImpl;



    @ApiOperation(value="获取线上团队及其负责人信息", notes="获取线上团队及其负责人信息")
    @RequestMapping(value = "/queryAllOnTeamInfo",method = RequestMethod.GET)
    public Result queryAllOnTeamInfo(){
        return Result.result(1, teamConfigServiceImpl.queryAllOnTeamInfo());
    }

    @ApiOperation(value="获取线下团队及其负责人信息", notes="获取线下团队及其负责人信息")
    @RequestMapping(value = "/queryAllOutTeamInfo",method = RequestMethod.GET)
    public Result queryAllOutTeamInfo(){
        return Result.result(1, teamConfigServiceImpl.queryAllOutTeamInfo());
    }

}
