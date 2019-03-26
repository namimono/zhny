package org.rcisoft.business.operation.adaptive.service;

import org.rcisoft.business.operation.adaptive.entity.AdaptiveParam;
import org.rcisoft.business.operation.adaptive.entity.BuildingAdaptation;
import org.rcisoft.business.operation.adaptive.entity.ClimateAdaptation;

import java.util.List;

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
}
