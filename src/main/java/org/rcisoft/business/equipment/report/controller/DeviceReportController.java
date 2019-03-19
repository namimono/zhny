package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import org.rcisoft.business.equipment.report.service.DeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
