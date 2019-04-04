package org.rcisoft.business.equipment.fault.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.fault.service.FaultService;
import org.rcisoft.entity.BusMalfunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JiChao on 2019/3/29.
 */
@Api(tags = "设备维护--故障统计")
@RestController
@RequestMapping("fault")
public class FaultController {

    @Autowired
    FaultService faultServiceimpl;

    @ApiOperation(value="故障数量统计", notes="全部设备时，typeFirstId=0")
    @GetMapping("/queryFaultCount/{projectId}/{typeFirstId}/{year}/{month}")
    public Result queryFaultCount(@PathVariable String projectId, @PathVariable String typeFirstId, @PathVariable Integer year, @PathVariable Integer month) {
        return Result.result(faultServiceimpl.queryFaultCount(projectId, typeFirstId, year, month));
    }

    @ApiOperation(value="分页查询故障内容列表", notes="全部设备时，typeFirstId=0")
    @GetMapping("/queryFaults/{projectId}/{typeFirstId}/{year}/{month}")
    public Result queryFaults(@PathVariable String projectId, @PathVariable String typeFirstId, @PathVariable Integer year, @PathVariable Integer month) {
        return Result.result(faultServiceimpl.queryFaults(projectId, typeFirstId, year, month));
    }

    @ApiOperation(value="更新故障记录", notes="更新故障记录")
    @PutMapping("/updateMalfunction")
    public Result updateMalfunction(@RequestBody BusMalfunction busMalfunction) {
        return Result.result(faultServiceimpl.updateMalfunction(busMalfunction));
    }

    @ApiOperation(value="故障内容下载", notes="全部设备时，typeFirstId=0")
    @GetMapping("/downloadFaults/{projectId}/{typeFirstId}/{year}/{month}")
    public void downloadFaults(HttpServletRequest request, HttpServletResponse response, @PathVariable String projectId, @PathVariable String typeFirstId, @PathVariable Integer year, @PathVariable Integer month) {
        faultServiceimpl.downloadFaults(request, response, projectId, typeFirstId, year, month);
    }

}
