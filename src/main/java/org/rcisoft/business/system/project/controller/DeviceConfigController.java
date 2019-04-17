package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.entity.ParamFirstContainSecond;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.entity.BusDeviceType;
import org.rcisoft.entity.BusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:14
 **/
@Api(tags = "项目编辑-设备配置")
@RestController
@RequestMapping("deviceConfig")
public class DeviceConfigController {

    @Autowired
    private DeviceConfigService deviceConfigServiceImpl;

    @ApiOperation(value="新增设备配置信息", notes="新增设备配置信息")
    @PostMapping("/addDeviceConfigInfo")
    public Result addDeviceConfigInfo(@RequestBody BusDevice busDevice){
        return Result.result(deviceConfigServiceImpl.addDeviceConfigInfo(busDevice),"新增设备配置信息成功","新增设备配置信息失败");
    }

    @ApiOperation(value="删除设备信息(谨慎!)", notes="删除设备信息(谨慎!)")
    @DeleteMapping("/deleteAllByProId/{deviceId}")
    public Result deleteDevice(@PathVariable String deviceId){
        int i = deviceConfigServiceImpl.deleteDevice(deviceId);
        return Result.result(i,"删除设备信息成功","删除设备信息失败",i);
    }

    @ApiOperation(value="修改设备信息", notes="修改设备信息")
    @PutMapping("/updateDevice")
    public Result updateDevice(@RequestBody BusDevice busDevice){
        return Result.result(deviceConfigServiceImpl.updateDevice(busDevice),"修改设备信息成功","修改设备信息失败");
    }

    @ApiOperation(value="查询设备简要信息（设备配置）", notes="查询设备简要信息（设备配置）")
    @GetMapping("/queryDeviceBriefInfo/{systemId}/{projectId}")
    public Result queryDeviceBriefInfo(@PathVariable String systemId,@PathVariable String projectId){
        return Result.result(deviceConfigServiceImpl.queryDeviceBriefInfo(systemId,projectId));
    }

    @ApiOperation(value="根据设备ID查询设备信息", notes="根据设备ID查询设备信息")
    @GetMapping("/queryDeviceInfo/{deviceId}")
    public Result queryDeviceInfo(@PathVariable String deviceId){
        return Result.result(deviceConfigServiceImpl.queryDeviceInfo(deviceId));
    }

    @ApiOperation(value="查询系统类型", notes="查询系统类型")
    @GetMapping("/querySystem")
    public Result querySystem(){
        return Result.result(deviceConfigServiceImpl.querySystem());
    }

    @ApiOperation(value="查询参数来源", notes="查询参数来源")
    @GetMapping("/querySource")
    public Result querySource(){
        return Result.result(deviceConfigServiceImpl.querySource());
    }

    @ApiOperation(value="查询能耗分类", notes="查询能耗分类")
    @GetMapping("/queryEnergyType")
    public Result queryEnergyType(){
        return Result.result(deviceConfigServiceImpl.queryEnergyType());
    }

//    @ApiOperation(value="处理一、二级设备类型下拉菜单级联格式", notes="处理一、二级设备类型下拉菜单级联格式")
//    @GetMapping("/processTypeFormat/{systemId}")
//    public Result processTypeFormat(@PathVariable String systemId){
//        return Result.result(deviceConfigServiceImpl.processTypeFormat(systemId));
//    }

//    @ApiOperation(value="删除一级参数信息", notes="删除一级参数信息")
//    @DeleteMapping("/deleteParamFirst/{paramFirstId}")
//    public Result deleteParamFirst(@PathVariable String paramFirstId){
//        return Result.result(deviceConfigServiceImpl.deleteParamFirst(paramFirstId),"删除一级参数信息成功","删除一级参数信息失败");
//    }
//
//    @ApiOperation(value="删除二级参数信息", notes="删除二级参数信息")
//    @DeleteMapping("/deleteParamSecond/{paramSecondId}")
//    public Result deleteParamSecond(@PathVariable String paramSecondId){
//        return Result.result(deviceConfigServiceImpl.deleteParamSecond(paramSecondId),"删除二级参数信息成功","删除二级参数信息失败");
//    }

//    @ApiOperation(value="批量更新一级参数", notes="批量更新一级参数")
//    @PutMapping("/updateAllParamFirst")
//    public Result updateAllParamFirst(@RequestBody List<BusParamFirst> list){
//        return Result.result(deviceConfigServiceImpl.updateAllParamFirst(list),"批量更新一级参数成功","批量更新一级参数失败");
//    }

//    @ApiOperation(value="修改一级参数信息", notes="修改一级参数信息")
//    @PutMapping("/updateParamFirst")
//    public Result updateParamFirst(@RequestBody BusParamFirst busParamFirst){
//        return Result.result(deviceConfigServiceImpl.updateParamFirst(busParamFirst),"修改一级参数信息成功","修改一级参数信息失败");
//    }

//    @ApiOperation(value="批量更新二级参数", notes="批量更新二级参数")
//    @PutMapping("/updateAllParamSecond")
//    public Result updateAllParamSecond(@RequestBody List<BusParamSecond> list){
//        return Result.result(deviceConfigServiceImpl.updateAllParamSecond(list),"批量更新二级参数成功","批量更新二级参数失败");
//    }

//    @ApiOperation(value="修改二级参数信息", notes="修改二级参数信息")
//    @PutMapping("/updateParamSecond")
//    public Result updateParamSecond(@RequestBody BusParamSecond busParamSecond){
//        return Result.result(deviceConfigServiceImpl.updateParamSecond(busParamSecond),"修改二级参数信息成功","修改二级参数信息失败");
//    }

//    @ApiOperation(value="新增一二级参数信息（第一次同时新增一二级参数）", notes="新增一二级参数信息（第一次同时新增一二级参数）")
//    @PostMapping("/addParamInfo")
//    public Result addParamInfo(@RequestBody BusParamFirst busParamFirst,@RequestBody BusParamSecond busParamSecond){
//        return Result.result(deviceConfigServiceImpl.addParamInfo(busParamFirst,busParamSecond),"新增一二级参数信息成功","新增一二级参数信息失败");
//    }
//
//    @ApiOperation(value="新增二级参数信息（单独新增二级参数）", notes="新增二级参数信息（单独新增二级参数）")
//    @PostMapping("/addParamSecond")
//    public Result addParamSecond(@RequestBody BusParamSecond busParamSecond){
//        return Result.result(deviceConfigServiceImpl.addParamSecond(busParamSecond),"新增二级参数信息成功","新增二级参数信息失败");
//    }

    @ApiOperation(value="查询一二级参数信息", notes="查询一二级参数信息")
    @GetMapping("/queryParamInfo/{deviceId}")
    public Result queryParamInfo(@PathVariable String deviceId){
        return Result.result(deviceConfigServiceImpl.queryParamInfo(deviceId));
    }

    @ApiOperation(value="批量增删改一二级参数信息", notes="批量增删改一二级参数信息")
    @PostMapping("/batchOperationParams/{paramFirstIds}/{paramSecondIds}")
    public Result batchOperationParams(@RequestBody List<ParamFirstContainSecond> list,@PathVariable String paramFirstIds,@PathVariable String paramSecondIds){
        return Result.serviceResult(deviceConfigServiceImpl.batchOperationParams(list,paramFirstIds,paramSecondIds),"保存成功","操作失败");
    }

    @ApiOperation(value="新增厂家信息", notes="新增厂家信息")
    @PostMapping("/addFactory")
    public Result addFactory(@RequestBody BusFactory busFactory){
        return Result.result(deviceConfigServiceImpl.addFactory(busFactory),"新增厂家信息成功","新增厂家信息失败");
    }

    @ApiOperation(value="删除厂家信息", notes="删除厂家信息")
    @DeleteMapping("/deleteFactory/{factoryId}")
    public Result deleteFactory(@PathVariable String factoryId){
        return Result.result(deviceConfigServiceImpl.deleteFactory(factoryId),"删除厂家信息成功","删除厂家信息失败");
    }

    @ApiOperation(value="修改厂家信息", notes="修改厂家信息")
    @PutMapping("/updateFactory")
    public Result updateFactory(@RequestBody BusFactory busFactory){
        return Result.result(deviceConfigServiceImpl.updateFactory(busFactory),"修改厂家信息成功","修改厂家信息失败");
    }

    @ApiOperation(value="查询厂家信息", notes="查询厂家信息")
    @GetMapping("/queryFactory")
    public Result queryFactory (){
        return Result.result(deviceConfigServiceImpl.queryFactory());
    }

    @ApiOperation(value="新增设备类型", notes="新增设备类型")
    @PostMapping("/addDeviceType")
    public Result addDeviceType(@RequestBody BusDeviceType busDeviceType){
        return Result.result(deviceConfigServiceImpl.addDeviceType(busDeviceType),"新增设备类型成功","新增设备类型失败");
    }

    @ApiOperation(value="删除设备类型", notes="删除设备类型")
    @DeleteMapping("/deleteDeviceType/{typeId}")
    public Result deleteDeviceType(@PathVariable String typeId){
        return Result.result(deviceConfigServiceImpl.deleteDeviceType(typeId),"删除设备类型成功","删除设备类型失败");
    }

    @ApiOperation(value="修改设备类型", notes="修改设备类型")
    @PutMapping("/updateDeviceType")
    public Result updateDeviceType(@RequestBody BusDeviceType busDeviceType){
        return Result.result(deviceConfigServiceImpl.updateDeviceType(busDeviceType),"修改设备类型成功","修改设备类型失败");
    }

    @ApiOperation(value="查询设备类型", notes="查询设备类型")
    @GetMapping("/queryDeviceType")
    public Result queryDeviceType(){
        return Result.result(deviceConfigServiceImpl.queryDeviceType());
    }

    @ApiOperation(value="新增设备图片", notes="新增设备图片")
    @PostMapping("/addTypeImage")
    public Result addTypeImage(@RequestParam MultipartFile file,@RequestParam(value = "proId") String proId,@RequestParam(value = "name")String name){
        return Result.serviceResult(deviceConfigServiceImpl.addTypeImage(file,proId,name),"新增设备图片成功","新增设备图片失败");
    }

    @ApiOperation(value="删除设备图片", notes="删除设备图片")
    @DeleteMapping("/deleteDevicePic/{picId}")
    public Result deleteDevicePic(@PathVariable String picId){
        return Result.result(deviceConfigServiceImpl.deleteDevicePic(picId),"删除设备图片成功","删除设备图片失败");
    }

    @ApiOperation(value="修改设备图片", notes="修改设备图片")
    @PutMapping("/updateTypeImage")
    public Result updateTypeImage(MultipartFile file,@RequestParam(value = "picId") String picId,@RequestParam(value = "name")String name){
        return Result.serviceResult(deviceConfigServiceImpl.updateTypeImage(file,picId,name),"修改设备图片成功","修改设备图片失败");
    }

    @ApiOperation(value="查询设备图片", notes="查询设备图片")
    @GetMapping("/queryDevicePic/{proId}")
    public Result queryDevicePic(@PathVariable String proId){
        return Result.result(deviceConfigServiceImpl.queryDevicePic(proId));
    }
}
