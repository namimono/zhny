package org.rcisoft.business.operation.adaptive.service;

import org.rcisoft.business.operation.adaptive.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 土豆儿
 * @date 2019/3/18 10:50
 **/

public interface AdaptiveService {

    /**
     * 气候自适应模块
     */
    ClimateAdaptation climateAdaptation(String projectId,String beginTime,String endTime);
//
//    /**
//     * 建筑负荷自适应模块
//     */
//    BuildingAdaptation buildingAdaptation(String proId, String year, String month, String day);
//
//    /**
//     * 计算建筑负荷最优供水温度
//     */
//    List<Object> buildingList(AdaptiveParam adaptiveParam);

    /**
     * 建筑负荷
     * @param buildingParam
     * @return
     */
    BuildingResult queryBuilding(BuildingParam buildingParam);

    /**
     * 建筑负荷excel下载
     * @param buildingParam
     */
    void downloadBuilding(HttpServletRequest request, HttpServletResponse response, BuildingParam buildingParam);
}
