package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @ApiOperation(value="根据参数来源查询二级参数",notes="根据参数来源查询二级参数")
//    @GetMapping("/queryParamSecondBySource/{sourceId}/{proId}")
//    public void queryParamSecondBySource(@PathVariable String sourceId,@PathVariable String proId){
//        systemDataServiceImpl.queryParamSecondBySource(proId,sourceId);
//    }

    @ApiOperation(value="下载数据文档",notes="下载数据文档",produces="application/octet-stream")
    @GetMapping("/downlDataDocument/{paramSecondIds}/{proId}/{beginTime}/{endTime}")
    public void downlDataDocument(HttpServletResponse response,@PathVariable String paramSecondIds,@PathVariable String proId,@PathVariable String beginTime,@PathVariable String endTime){
        systemDataServiceImpl.downlDataDocument(response,paramSecondIds,proId,beginTime,endTime);
    }
}
