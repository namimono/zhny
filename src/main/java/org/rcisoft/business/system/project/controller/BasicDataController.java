package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.service.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

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

    @ApiOperation(value="新增水电气24小时单价信息", notes="新增水电气24小时单价信息")
    @PostMapping("/addPerHourPrice")
    public Result addPerHourPrice(@RequestBody List<EnergyPrice> list){
        return Result.result(basicDataServiceImpl.addPerHourPrice(list),"新增水电气24小时单价信息成功","新增水电气24小时单价信息失败");
    }

    @ApiOperation(value="修改水电气24小时单价信息", notes="修改水电气24小时单价信息")
    @PutMapping("/updetePerHourPrice")
    public Result updetePerHourPrice(@RequestBody List<EnergyPrice> list){
        return Result.result(basicDataServiceImpl.updatePerHourPrice(list),"修改水电气24小时单价信息成功","修改水电气24小时单价信息失败");
    }

    @ApiOperation(value="查询水电气24小时单价信息", notes="查询水电气24小时单价信息")
    @GetMapping("/queryPerHourPrice/{proId}")
    public Result queryPerHourPrice(@PathVariable String proId){
        return Result.result(basicDataServiceImpl.queryPerHourPrice(proId));
    }

    @ApiOperation(value="新增能源标准", notes="新增能源标准")
    @PostMapping("/addEnergyStandard")
    public Result addEnergyStandard(@RequestBody List<EnergyStandard> list){
        return Result.result(basicDataServiceImpl.addEnergyStandard(list),"新增能源标准成功","新增能源标准失败");
    }

    @ApiOperation(value="修改能源标准", notes="修改能源标准")
    @PutMapping("/updateEnergyStandard")
    public Result updateEnergyStandard(@RequestBody List<EnergyStandard> list){
        return Result.result(basicDataServiceImpl.updateEnergyStandard(list),"修改能源标准成功","修改能源标准失败");
    }

    @ApiOperation(value="查询水电气24小时单价信息", notes="查询水电气24小时单价信息")
    @GetMapping("/queryEnergyStandard/{proId}")
    public Result queryEnergyStandard(@PathVariable String proId){
        return Result.result(basicDataServiceImpl.queryEnergyStandard(proId));
    }

    @ApiOperation(value="上传基准碳排放量模板", notes="上传基准碳排放量模板")
    @PostMapping(value = "/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return basicDataServiceImpl.upload(file);
    }

    @ApiOperation(value="下载基准碳排放量模板", notes="下载基准碳排放量模板")
    @GetMapping(value = "/fileDownload")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
       return basicDataServiceImpl.downloadFile(request,response);
    }
}
