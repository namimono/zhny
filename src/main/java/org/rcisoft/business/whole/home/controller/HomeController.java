package org.rcisoft.business.whole.home.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.whole.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:23 2019/4/10
 */
@Api(tags = "主页")
@RestController
@RequestMapping("home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation(value = "项目详情",notes = "项目详情")
    @GetMapping("homeProject/{year}")
    public Result homeProject(@PathVariable int year){
        return Result.result(1,homeService.queryProjectHome(year));

    }

}
