package org.rcisoft.business.energy.compare.service;

import org.rcisoft.business.energy.compare.entity.CompareResult;
import org.rcisoft.business.energy.compare.entity.EnergyDayAndMon;
import org.rcisoft.business.energy.compare.entity.EnergyStandardResult;

/**
 * Created by JiChao on 2019/3/20.
 * 能耗管理--用能比较
 */
public interface CompareService {

    /**
     * 能耗管理--用能比较--用能概况
     * @param projectId 项目id
     * @return
     */
    EnergyDayAndMon queryEnergyDayAndMon(String projectId);

    /**
     * 能耗管理--用能比较--能耗标准
     * @param projectId
     * @return
     */
    EnergyStandardResult queryEnergyStandard(String projectId);

    /**
     * 能耗管理--用能比较--用能比较
     * @param projcetId
     * @param energyType 1水2电3气
     * @param compareType 1同比2环比
     * @return
     */
    CompareResult queryEnergyCompare(String projcetId, Integer energyType, Integer compareType, Integer year, Integer month);

}
