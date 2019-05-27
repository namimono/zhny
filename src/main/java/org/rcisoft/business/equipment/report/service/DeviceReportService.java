package org.rcisoft.business.equipment.report.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:03
 **/

public interface DeviceReportService {

    /**
     * 导出当日设备信息excel
     * @param response
     * @param proId
     * @param date
     */
    void downloadDeviceTodayData(HttpServletResponse response, String proId, String date);

    /**
     * 到处设备信息
     * @param request
     * @param response
     * @param projectId
     * @param time
     */
    void downloadDeviceData(HttpServletRequest request, HttpServletResponse response, String projectId, String time);

}


