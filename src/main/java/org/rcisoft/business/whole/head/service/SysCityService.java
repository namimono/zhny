package org.rcisoft.business.whole.head.service;

import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysCity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:38 2019/3/8
 */
public interface SysCityService {
    /**
     * 根据城市名称查询城市信息
     * @param name
     * @return
     */
    BusTemperature queryCityByName(String code);

    /**
     * 查询城市天气
     * @return
     */
    Integer queryCityWeather();

    /**
     * 查询所有项目
     * @return
     */
    List<BusProject> queryAllProj(HttpServletRequest request);
}
