package org.rcisoft.business.whole.right.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.controller.HttpServletController;
import org.rcisoft.business.whole.right.service.WeatherParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:09 2019/4/12
 */
@Api(tags = "天气参数")
@RestController
@RequestMapping("/WeatherParam")
public class WeatherParamController extends HttpServletController {
    @Autowired
    WeatherParamService weatherParamService;

    @ApiOperation(value = "下载天气excel",notes = "下载天气excel")
    @GetMapping("downloadExcel/{proId}/{start}/{finish}")
    public void downloadExcel(@PathVariable String proId, @PathVariable String start, @PathVariable String finish){
        weatherParamService.downloadWeather(request,response,proId,start,finish);
    }
}
