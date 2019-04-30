package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.entity.BusTitle;
import org.rcisoft.entity.BusTitleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:51
 **/
@Api(tags = "项目编辑-其他配置")
@RestController
@RequestMapping("otherConfig")
public class OtherConfigController {

    @Autowired
    private OtherConfigService otherConfigServiceImpl;

    @ApiOperation(value="导出参数库模板", notes="导出参数库模板",produces="application/octet-stream")
    @GetMapping("/downloadLibraryTemplate/{deviceId}/{deviceName}")
    public void downloadLibraryTemplate(HttpServletResponse response,@PathVariable String deviceId,@PathVariable String deviceName){
        otherConfigServiceImpl.downloadLibraryTemplate(response,deviceId,deviceName);
    }

    @ApiOperation(value="导入参数库模板数据", notes="导入参数库模板数据")
    @PostMapping("/importData")
    public Result importData(MultipartFile file, String deviceId, String projectId){
       return Result.result(otherConfigServiceImpl.importData(file,deviceId,projectId),"导入成功","导入失败");
    }

    @ApiOperation(value="增加自定义标题信息", notes="增加自定义标题信息")
    @PostMapping("/addTitleInfo")
    public Result addTitleInfo(@RequestBody BusTitle busTitle){
        return Result.result(otherConfigServiceImpl.addTitleInfo(busTitle),"增加自定义标题信息成功","增加自定义标题信息失败");
    }

    @ApiOperation(value="删除自定义标题信息", notes="删除自定义标题信息")
    @DeleteMapping("/deleteTitleInfo")
    public Result deleteTitleInfo(@RequestBody BusTitle busTitle){
        return Result.result(otherConfigServiceImpl.deleteTitleInfo(busTitle),"删除自定义标题信息成功","删除自定义标题信息失败");
    }

    @ApiOperation(value="修改自定义标题信息", notes="修改自定义标题信息")
    @PutMapping("/updateTitleInfo")
    public Result updateTitleInfo(@RequestBody BusTitle busTitle){
        return Result.result(otherConfigServiceImpl.updateTitleInfo(busTitle),"修改自定义标题信息成功","修改自定义标题信息失败");
    }

    @ApiOperation(value="根据项目ID查询自定义标题信息", notes="根据项目ID查询自定义标题信息")
    @GetMapping("/queryTitleInfo/{projectId}/{systemId}")
    public Result queryTitleInfo(@PathVariable String projectId,@PathVariable String systemId){
        return Result.result(otherConfigServiceImpl.queryTitleInfo(projectId,systemId));
    }

    @ApiOperation(value="增加自定义参数信息", notes="增加自定义参数信息")
    @PostMapping("/addTitleParamInfo/{titleId}")
    public Result addTitleParamInfo(@RequestBody List<BusTitleParam> titleParamList,@PathVariable String titleId){
        return Result.result(otherConfigServiceImpl.addTitleParamInfo(titleParamList,titleId),"保存自定义参数信息成功","保存自定义参数信息失败");
    }

    @ApiOperation(value="根据自定义标题ID查询自定义参数信息", notes="根据自定义标题ID查询自定义参数信息")
    @GetMapping("/queryTitleParamsInfo/{titleId}")
    public Result queryTitleParamsInfo(@PathVariable String titleId){
        return Result.result(otherConfigServiceImpl.queryTitleParamsInfo(titleId));
    }

}
