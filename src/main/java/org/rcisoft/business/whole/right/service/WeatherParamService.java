package org.rcisoft.business.whole.right.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:17 2019/4/12
 */
public interface WeatherParamService {
    public void downloadWeather(HttpServletRequest request,HttpServletResponse response,String proId,String start,String finish);
}
