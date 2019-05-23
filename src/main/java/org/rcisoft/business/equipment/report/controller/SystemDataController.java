package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.report.entity.SystemDataDto;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:18
 **/
@Api(tags = "电子报表-系统数据")
@RestController
@RequestMapping("systemData")
public class SystemDataController {

    @Autowired
    private SystemDataService systemDataServiceImpl;

    @ApiOperation(value="查询图表数据",notes="查询图表数据")
    @GetMapping("/queryEchartData/{paramSecondIds}/{proId}/{date}")
    public Result queryEchartData(@PathVariable String paramSecondIds, @PathVariable String proId, @PathVariable String date){
        return Result.result(systemDataServiceImpl.queryEchartData(paramSecondIds,proId,date));
    }

    @ApiOperation(value="下载数据文档",notes="参数：项目ID,一级参数code,二级参数code,选择时间(yyyy-MM-dd)")
    @PostMapping("/downlDataDocument")
    public void downlDataDocument(HttpServletRequest request,HttpServletResponse response, @RequestBody SystemDataDto systemDataDto){
        systemDataServiceImpl.downlDataDocument(request,response,systemDataDto);
    }

    @ApiOperation(value = "查询系统数据", notes = "参数：项目ID,一级参数code,二级参数code,选择时间(yyyy-MM-dd)")
    @PostMapping("/listSystemData")
    public Result listSystemData(@RequestBody SystemDataDto systemDataDto){
        return Result.result(systemDataServiceImpl.listSystemData(systemDataDto));
    }


}
