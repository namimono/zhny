package org.rcisoft.business.operation.parameter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.operation.parameter.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 土豆儿
 * @date 2019/4/2 14:47
 **/
@Api(tags = "节能运维-参数库")
@RestController
@RequestMapping("parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterServiceImpl;

    @ApiOperation(value="查询设备简要信息（参数库）", notes="查询设备简要信息（参数库）")
    @GetMapping("/queryDeviceBriefByType/{systemId}/{projectId}/{deviceTypeId}")
    public Result queryDeviceBriefByType(@PathVariable String systemId,@PathVariable String projectId,@PathVariable String deviceTypeId){
        return Result.result(parameterServiceImpl.queryDeviceBriefByType(systemId,projectId,deviceTypeId));
    }

    @ApiOperation(value="导出参数库数据", notes="导出参数库数据",produces="application/octet-stream")
    @GetMapping("/downloadParameter/{deviceId}/{deviceName}")
    public void downloadParameter(HttpServletResponse response, @PathVariable String deviceId, @PathVariable String deviceName){
        parameterServiceImpl.downloadParameter(response,deviceId,deviceName);
    }

    @ApiOperation(value="查询参数库参数数据", notes="查询参数库参数数据")
    @GetMapping("/queryParamLibrary/{deviceId}")
    public Result queryParamLibrary(@PathVariable String deviceId){
        return Result.result(parameterServiceImpl.queryParamLibrary(deviceId));
    }
}
