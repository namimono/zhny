package org.rcisoft.business.operation.adaptive.service;

import org.rcisoft.business.operation.adaptive.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/18 10:50
 **/

public interface AdaptiveService {

    /**
     * 建筑负荷
     * @param adaptiveParam
     * @return
     */
    BuildingResult queryBuilding(AdaptiveParam adaptiveParam);

    /**
     * 建筑负荷excel下载
     * @param adaptiveParam
     */
    void downloadBuilding(HttpServletRequest request, HttpServletResponse response, AdaptiveParam adaptiveParam);

    /**
     * 气候自适应
     * @param climateParam
     * @return
     */
    List<BigDecimal[]> queryClimate(ClimateParam climateParam);

    /**
     * 气候自适应excel下载
     * @param request
     * @param response
     * @param climateParam
     */
    void downloadClimate(HttpServletRequest request, HttpServletResponse response, ClimateParam climateParam);
}
