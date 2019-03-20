package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.entity.*;
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

    @ApiOperation(value="根据参数来源查询表具", notes="根据参数来源查询表具")
    @GetMapping("/queryParamFirstBySource/{projectId}")
    public Result queryParamFirstBySource(@PathVariable String projectId){
        BusParamFirst busParamFirst = new BusParamFirst();
        busParamFirst.setProjectId(projectId);
        return Result.result(otherConfigServiceImpl.queryParamFirstBySource(busParamFirst));
    }

    @ApiOperation(value="根据项目、设备、一二级参数ID查询能耗分类信息", notes="根据(projectId,deviceId,paramFirstId,paramSecondId)查询能耗分类信息")
    @PostMapping("/queryTypeNameByConfig")
    public Result queryTypeNameByConfig(@RequestBody EnergyTypeConfig energyTypeConfig){
        return Result.result(otherConfigServiceImpl.queryTypeNameByConfig(energyTypeConfig));
    }

    @ApiOperation(value="增加能耗配置", notes="增加能耗配置")
    @PostMapping("/addEnergyConfig")
    public Result addEnergyConfig(@RequestBody EnergyConfig energyConfig){
        return Result.result(otherConfigServiceImpl.addEnergyConfig(energyConfig),"增加能耗配置成功","增加能耗配置失败");
    }

    @ApiOperation(value="修改能耗配置信息", notes="修改能耗配置信息")
    @PutMapping("/updateEnergyConfig")
    public Result updateEnergyConfig(@RequestBody EnergyConfig energyConfig){
        return Result.result(otherConfigServiceImpl.updateEnergyConfig(energyConfig),"修改能耗配置信息成功","修改能耗配置信息失败");
    }

    @ApiOperation(value="根据设备ID、二级参数ID查询参数库信息", notes="根据设备ID、二级参数ID查询参数库信息")
    @GetMapping("/queryParamLibrary/{deviceId}/{paramSecondId}")
    public Result queryParamLibrary(@PathVariable String deviceId,@PathVariable String paramSecondId){
        BusParamLibrary busParamLibrary = new BusParamLibrary();
        busParamLibrary.setDeviceId(deviceId);
        busParamLibrary.setParamSecondId(paramSecondId);
        return Result.result(otherConfigServiceImpl.queryParamLibrary(busParamLibrary));
    }

    @ApiOperation(value="新增参数库信息", notes="新增参数库信息")
    @PostMapping("/addParamLibrary")
    public Result addParamLibrary(@RequestBody List<BusParamLibrary> busParamLibraryList){
        return Result.result(otherConfigServiceImpl.addParamLibrary(busParamLibraryList),"新增参数库信息成功","新增参数库信息失败");
    }

    @ApiOperation(value="删除参数库信息", notes="删除参数库信息")
    @DeleteMapping("/deleteParamLibrary")
    public Result deleteParamLibrary(@RequestBody BusParamLibrary busParamLibrary){
        return Result.result(otherConfigServiceImpl.deleteParamLibrary(busParamLibrary),"删除参数库信息成功","删除参数库信息失败");
    }

    @ApiOperation(value="修改参数库信息", notes="修改参数库信息")
    @PutMapping("/updateParamLibrary")
    public Result updateParamLibrary(@RequestBody BusParamLibrary busParamLibrary){
        return Result.result(otherConfigServiceImpl.updateParamLibrary(busParamLibrary),"修改参数库信息成功","修改参数库信息失败");
    }

    @ApiOperation(value="新增参数库记录信息", notes="新增参数库记录信息")
    @PostMapping("/addEnergyParamLibrary")
    public Result addEnergyParamLibrary(@RequestBody EnergyParamLibrary energyParamLibrary){
        return Result.result(otherConfigServiceImpl.addEnergyParamLibrary(energyParamLibrary),"新增参数库记录信息成功","新增参数库记录信息失败");
    }

    @ApiOperation(value="删除参数库记录信息", notes="删除参数库记录信息")
    @DeleteMapping("/deleteEnergyParamLibrary")
    public Result deleteEnergyParamLibrary(@RequestBody EnergyParamLibrary energyParamLibrary){
        return Result.result(otherConfigServiceImpl.deleteEnergyParamLibrary(energyParamLibrary),"删除参数库记录信息成功","删除参数库记录信息失败");
    }

    @ApiOperation(value="修改参数库记录信息", notes="修改参数库记录信息")
    @PutMapping("/updateEnergyParamLibrary")
    public Result updateEnergyParamLibrary(@RequestBody EnergyParamLibrary energyParamLibrary){
        return Result.result(otherConfigServiceImpl.updateEnergyParamLibrary(energyParamLibrary),"修改参数库记录表信息成功","修改参数库记录表信息失败");
    }

    @ApiOperation(value="导出模板（项目维护-其他配置-参数库）", notes="导出模板（项目维护-其他配置-参数库）")
    @GetMapping("/downloadLibraryTemplate")
    public void downloadLibraryTemplate(HttpServletResponse response,String year,String model,String deviceId){
        LibraryAndParam libraryAndParam = new LibraryAndParam();
        libraryAndParam.setDeviceId(deviceId);
        otherConfigServiceImpl.downloadLibraryTemplate(response,year,model,libraryAndParam);
    }

    @ApiOperation(value="导入参数库模板数据", notes="导入参数库模板数据")
    @PostMapping("/importData")
    public void importData(MultipartFile file, String deviceId, String projectId){
        otherConfigServiceImpl.importData(file,deviceId,projectId);
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
    @GetMapping("/queryTitleInfo/{projectId}")
    public Result queryTitleInfo(@PathVariable String projectId){
        return Result.result(otherConfigServiceImpl.queryTitleInfo(projectId));
    }

    @ApiOperation(value="增加自定义参数信息", notes="增加自定义参数信息")
    @PostMapping("/addTitleParamInfo")
    public Result addTitleParamInfo(@RequestBody BusTitleParam busTitleParam){
        return Result.result(otherConfigServiceImpl.addTitleParamInfo(busTitleParam),"增加自定义参数信息成功","增加自定义参数信息失败");
    }

    @ApiOperation(value="删除自定义参数信息", notes="删除自定义参数信息")
    @DeleteMapping("/deleteTitleParamInfo")
    public Result deleteTitleParamInfo(@RequestBody BusTitleParam busTitleParam){
        return Result.result(otherConfigServiceImpl.deleteTitleParamInfo(busTitleParam),"删除自定义参数信息成功","删除自定义参数信息失败");
    }

    @ApiOperation(value="修改自定义参数信息", notes="修改自定义参数信息")
    @PutMapping("/updateTitleParamInfo")
    public Result updateTitleParamInfo(@RequestBody BusTitleParam busTitleParam){
        return Result.result(otherConfigServiceImpl.updateTitleParamInfo(busTitleParam),"修改自定义参数信息成功","修改自定义参数信息失败");
    }

    @ApiOperation(value="根据自定义标题ID查询自定义参数信息", notes="根据自定义标题ID查询自定义参数信息")
    @GetMapping("/queryTitleParamsInfo/{titleId}")
    public Result queryTitleParamsInfo(@PathVariable String titleId){
        return Result.result(otherConfigServiceImpl.queryTitleParamsInfo(titleId));
    }

}
