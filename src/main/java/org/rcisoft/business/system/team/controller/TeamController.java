package org.rcisoft.business.system.team.controller;

import io.swagger.annotations.*;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.team.service.TeamService;
import org.rcisoft.entity.BusTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JiChao on 2019/3/12.
 * 系统管理--团队维护
 */
@Api(tags = "系统管理--团队维护")
@RestController
@RequestMapping("team")
public class TeamController {

    @Autowired
    TeamService teamServiceImpl;

    @ApiOperation(value="分页查询线上、线下团队列表", notes="分页查询线上、线下团队列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "项目类型，1：线上，2：线下", dataType = "数字"),
    })
    @GetMapping("/queryTeamByType/{type}")
    public Result queryTeamByType(@PathVariable Integer type) {
        return Result.result(teamServiceImpl.queryTeamByType(type));
    }

    @ApiOperation(value="根据id查询线上、线下团队", notes="根据id查询线上、线下团队")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团队主键", dataType = "字符串"),
    })
    @GetMapping("/queryTeamById/{id}")
    public Result queryTeamById(@PathVariable String id) {
        return Result.result(teamServiceImpl.queryTeamById(id));
    }

    @ApiOperation(value="增加团队", notes="增加团队")
    @PostMapping("/insertTeam")
    public Result insertTeam(@RequestBody BusTeam busTeam) {
        return Result.result(teamServiceImpl.insertTeam(busTeam), null);
    }

    @ApiOperation(value="修改团队", notes="修改团队")
    @PutMapping("/updateTeam")
    public Result updateTeam(@RequestBody BusTeam busTeam) {
        return Result.result(teamServiceImpl.updateTeam(busTeam), null);
    }

    @ApiOperation(value="删除团队", notes="删除团队")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团队主键", dataType = "字符串"),
    })
    @DeleteMapping("/deleteTeamById/{id}")
    public Result deleteTeamById(@PathVariable String id) {
        return Result.result(teamServiceImpl.deleteTeamById(id), null);
    }

    @ApiOperation(value="查询团队负责人id、姓名列表", notes="查询团队负责人id、姓名列表")
    @GetMapping("/queryPrincipalList")
    public Result queryPrincipalList() {
        return Result.result(teamServiceImpl.queryPrincipalList());
    }

}
