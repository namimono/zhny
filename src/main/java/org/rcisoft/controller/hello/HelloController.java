package org.rcisoft.controller.hello;

import org.rcisoft.base.result.Result;
import org.rcisoft.service.hello.HelloService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/helloForPage")
    public Result helloForPage() {
        return Result.result(1, helloServiceImpl.selectUserForPage());
    }

}
