package org.rcisoft.business.equipment.report.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:03
 **/

public interface DeviceReportService {

    /**
     * 导出当日设备信息excel
     * @param response
     * @param deviceId
     * @param proId
     * @param date
     */
    void downloadDeviceTodayData(HttpServletResponse response, String deviceId, String proId, String date);
}
