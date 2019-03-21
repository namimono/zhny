package org.rcisoft.business.energy.overview.service;

import org.rcisoft.business.energy.overview.entity.*;

/**
 * Created by JiChao on 2019/3/15.
 * 能耗管理--用能概况
 */
public interface OverviewService {

    /**
     * 根据项目id、时刻查询每小时的价格
     * @param projectId 项目id
     * @return
     */
    PricePerHour queryPricePerHour(String projectId);

    /**
     * 费用概况：今日、本月费用、排名
     * @param projectId 项目id
     * @return
     */
    PriceAndRank queryPriceAndRank(String projectId);

    /**
     * 今日、昨日分时运行费用
     * @param projectId 项目id
     * @param energyType 1：水，2：电，3：气，null：总费用
     * @return
     */
    PriceForDay queryPriceForDay(String projectId, Integer energyType);

    /**
     * 能耗拆分，水电气查询
     * @param projectId 项目id
     * @param energyType 1：水，2：电，3：气
     * @return
     */
    EnergySplitResult queryEnergySplit(String projectId, Integer energyType);

}
