package org.rcisoft.business.operation.parameter.service;

import org.rcisoft.business.system.project.entity.DeviceBriefInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/2 14:46
 **/

public interface ParameterService {

    /**
     * 导出参数库数据
     */
    void downloadParameter(HttpServletResponse response, String deviceId, String deviceName);
}
