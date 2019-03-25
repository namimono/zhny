package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.business.equipment.report.service.DeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:05
 **/
@Api(tags = "电子报表-设备日报表")
@RestController
@RequestMapping("deviceReport")
public class DeviceReportController {

    @Autowired
    private DeviceReportService deviceReportServiceImpl;

    @ApiOperation(value="导出当日设备信息excel",notes="导出当日设备信息excel",produces="application/octet-stream")
    @GetMapping("/downloadDeviceTodayData/{deviceId}/{proId}/{date}")
    public void downloadDeviceTodayData(HttpServletResponse response,@PathVariable String deviceId,@PathVariable String proId,@PathVariable String date){
        deviceReportServiceImpl.downloadDeviceTodayData(response,deviceId,proId,date);
    }
}
