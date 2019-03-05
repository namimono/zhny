package org.rcisoft.controller.projMaintenance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.service.projMaintenance.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:23
 **/
@Api(tags = "项目编辑-基础数据")
@RestController
@RequestMapping("basicData")
public class BasicDataController {

    @Autowired
    private BasicDataService basicDataServiceImpl;

    @ApiOperation(value="获取水电气24小时单价信息", notes="获取水电气24小时单价信息")
    @GetMapping("/queryPerHourPrice")
    public Result queryPerHourPrice(){
        return Result.result(1, basicDataServiceImpl.queryPerHourPrice());
    }
}
