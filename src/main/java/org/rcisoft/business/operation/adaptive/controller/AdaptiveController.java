package org.rcisoft.business.operation.adaptive.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.adaptive.entity.AdaptiveParam;
import org.rcisoft.business.operation.adaptive.entity.ClimateParam;
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
    public Result queryBuilding(@RequestBody AdaptiveParam adaptiveParam) {
        return Result.result(adaptiveServiceImpl.queryBuilding(adaptiveParam));
    }

    @ApiOperation(value="建筑负荷excel下载", notes="建筑负荷excel下载")
    @PostMapping("/downloadBuilding")
    public void downloadBuilding(HttpServletRequest request, HttpServletResponse response, @RequestBody AdaptiveParam adaptiveParam) {
        adaptiveServiceImpl.downloadBuilding(request, response, adaptiveParam);
    }

    @ApiOperation(value="气候自适应", notes="气候自适应")
    @PostMapping("/queryClimate")
    public Result queryClimate(@RequestBody ClimateParam climateParam) {
        return Result.result(adaptiveServiceImpl.queryClimate(climateParam));
    }

    @ApiOperation(value="气候自适应excel下载", notes="气候自适应excel下载")
    @PostMapping("/downloadClimate")
    public void downloadClimate(HttpServletRequest request, HttpServletResponse response, @RequestBody ClimateParam climateParam) {
        adaptiveServiceImpl.downloadClimate(request, response, climateParam);
    }
}
