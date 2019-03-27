package org.rcisoft.business.test.hello.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.test.hello.service.HelloService;
import org.rcisoft.entity.BusProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JiChao on 2018/5/29.
 */
@Api(tags = "测试")
@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private HelloService helloServiceImpl;

    @ApiOperation(value="测试", notes="测试测试")
    @GetMapping("/hello")
    public Result hello(@RequestParam String id){
        return Result.result(1, helloServiceImpl.selectUserCount(id));
    }

    @ApiOperation(value="测试分页", notes="测试测试")
    @PostMapping("/helloForPage")
    public Result helloForPage(BusProject busProject, @RequestParam Date createTime) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
        return Result.result(1, helloServiceImpl.selectUserForPage());
    }

}
