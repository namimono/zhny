package org.rcisoft.business.equipment.report.service.Impl;

import org.rcisoft.business.equipment.report.dao.DeviceReportDao;
import org.rcisoft.business.equipment.report.service.DeviceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:04
 **/
@Service
public class DeviceReportServiceImpl implements DeviceReportService {

    @Autowired
    private DeviceReportDao deviceReportDao;
}
