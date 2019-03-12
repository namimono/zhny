package org.rcisoft.business.management.evaluateproj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.management.evaluateproj.service.ProConfigService;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Minghui Xu
 * Time：2019/3/4 14:54
 **/

@Api(tags = "项目评估")
@RestController
@RequestMapping("projectConfig")
public class ProConfigController {

    @Autowired
    private ProConfigService projConfigServiceImpl;

    @ApiOperation(value = "获取所有关于项目的信息",notes = "获取所有关于项目的信息")
    @RequestMapping(value = "/queryAllProjInfo",method = RequestMethod.GET)
    public Result queryAllProjInfo(){
        return  Result.result(1,projConfigServiceImpl.queryAllProjInfo());
    }
}
