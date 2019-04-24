package org.rcisoft.business.whole.right.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.controller.HttpServletController;
import org.rcisoft.base.result.Result;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.business.whole.right.dao.AnalysisReportDao;
import org.rcisoft.business.whole.right.service.AnalysisReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:15 2019/4/12
 */
@Api(tags = "分析报告")
@RestController
public class AnalysisReportController extends HttpServletController {

    @Autowired
    private AnalysisReportService analysisReportService;

    @ApiOperation(value = "分析报告列表",notes = "分析报告列表")
    @GetMapping("queryAnalysisReport/{year}")
    public Result queryAnalysisReport(@PathVariable int year){
        return Result.result(1,analysisReportService.queryAnalysisReport(year));
    }

    @ApiOperation(value = "上传报告文件",notes = "上传报告文件")
    @PostMapping("uploadAnalysisReport")
    public ServiceResult uploadAnalysisReport(@RequestParam MultipartFile file,@RequestParam String proId, @RequestParam int year, @RequestParam int month){
        return analysisReportService.uploadAnalysisReport(file,proId,year,month);
    }

    @ApiOperation(value = "下载报告文件",notes = "下载报告文件")
    @GetMapping("downloadAnalysisReport/{proId}/{year}/{month}")
    public void downloadAnalysisReport(@PathVariable String proId,@PathVariable int year,@PathVariable int month) throws Exception {
        analysisReportService.downloadAnalysisReport(request,response,proId,year,month);
    }

    @ApiOperation(value = "删除报告文件",notes = "删除报告文件")
    @DeleteMapping("deteleAnalysisReport/{Id}}")
    public void deteleAnalysisReport(@PathVariable String Id){
        analysisReportService.deleteAnalysisReport(Id);
    }
}
