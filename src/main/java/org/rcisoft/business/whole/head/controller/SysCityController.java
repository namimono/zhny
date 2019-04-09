package org.rcisoft.business.whole.head.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.whole.head.service.SysCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:27 2019/3/8
 */
@Api(tags = "头部")
@RestController
@RequestMapping("sysCity")
public class SysCityController {
    @Autowired
    SysCityService sysCityService;

    @ApiOperation(value = "获取城市天气",notes = "获取城市天气")
    @GetMapping(value = "/weatherInfo/{code}")
    public Result weather(@PathVariable String code){
        return Result.result(1,sysCityService.queryCityByName(code));
    }

    @ApiOperation(value = "头部项目列表",notes = "头部项目列表")
    @GetMapping("queryAllProj")
    public Result queryAllProj(){
        return  Result.result(1,sysCityService.queryAllProj());
    }


}
