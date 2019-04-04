package org.rcisoft.business.operation.adaptive.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.adaptive.entity.BuildingParam;
import org.rcisoft.business.operation.adaptive.service.AdaptiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 土豆儿
 * @date 2019/3/18 10:51
 **/
@Api(tags = "节能运维-自适应模块")
@RestController
@RequestMapping("adaptive")
public class AdaptiveController {

    @Autowired
    private AdaptiveService adaptiveServiceImpl;

    @ApiOperation(value="建筑负荷", notes="建筑负荷")
    @PostMapping("/queryBuilding")
    public Result queryBuilding(@RequestBody BuildingParam buildingParam) {
        return Result.result(adaptiveServiceImpl.queryBuilding(buildingParam));
    }

    @ApiOperation(value="建筑负荷excel下载", notes="建筑负荷excel下载")
    @PostMapping("/downloadBuilding")
    public void downloadBuilding(HttpServletRequest request, HttpServletResponse response, @RequestBody BuildingParam buildingParam) {
        adaptiveServiceImpl.downloadBuilding(request, response, buildingParam);
    }
}
