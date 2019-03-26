package org.rcisoft.business.energy.emission.service;

import org.rcisoft.business.energy.emission.entity.EmissionParam;
import org.rcisoft.business.energy.emission.entity.EmissionResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JiChao on 2019/3/25.
 * 能耗管理--碳排放量
 */
public interface EmissionService {

    /**
     * 查询碳排放量
     * @param projectId
     * @return
     */
    EmissionResult queryEmission(String projectId);

    /**
     * 碳排放强度统计
     * @param emissionParam projectId，year，month，day
     * @return
     */
    List<BigDecimal> queryEmissionStatistics(EmissionParam emissionParam);

}
