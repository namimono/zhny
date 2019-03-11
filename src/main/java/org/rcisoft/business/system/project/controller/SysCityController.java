package org.rcisoft.controller.sysManagement.projMaintenance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.rcisoft.base.result.Result;
import org.rcisoft.service.sysManagement.projMaintenance.SysCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:27 2019/3/8
 */
@Api(tags = "项目编辑-获取天气")
@RestController
@RequestMapping("sysCity")
public class SysCityController {
    @Autowired
    SysCityService sysCityService;

    @ApiOperation(value = "获取城市天气",notes = "获取城市天气")
    @RequestMapping(value = "/weatherInfo",method = RequestMethod.POST)
    public Result weather(@RequestParam String city){
        return Result.result(1,sysCityService.queryCityByName(city));
    }
}
