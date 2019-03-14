package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.EnergyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/queryParamFirstBySource")
    public Result queryParamFirstBySource(String projectId){
        BusParamFirst busParamFirst = new BusParamFirst();
        busParamFirst.setProjectId(projectId);
        return Result.result(otherConfigServiceImpl.queryParamFirstBySource(busParamFirst));
    }

    @ApiOperation(value="根据项目设备等ID查询能耗分类信息", notes="根据项目设备等ID查询能耗分类信息")
    @GetMapping("/queryTypeNameByConfig")
    public Result queryTypeNameByConfig(String projectId,String deviceId,String paramFirstId,String paramSecondId){
        EnergyTypeConfig energyTypeConfig = new EnergyTypeConfig();
        energyTypeConfig.setProjectId(projectId);
        energyTypeConfig.setDeviceId(deviceId);
        energyTypeConfig.setParamFirstId(paramFirstId);
        energyTypeConfig.setParamSecondId(paramSecondId);
        return Result.result(otherConfigServiceImpl.queryTypeNameByConfig(energyTypeConfig));
    }

    @ApiOperation(value="修改能源配置信息", notes="修改能源配置信息")
    @PutMapping("/updateEnergyConfig")
    public Result updateEnergyConfig(@RequestBody EnergyConfig energyConfig){
        return Result.result(1,otherConfigServiceImpl.updateEnergyConfig(energyConfig));
    }
}
