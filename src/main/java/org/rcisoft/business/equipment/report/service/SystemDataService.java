package org.rcisoft.business.equipment.report.service;

import org.rcisoft.entity.BusParamSecond;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/

public interface SystemDataService {

    /**
     * 根据参数来源查询二级参数
     */
    List<BusParamSecond> queryParamSecondBySource(String proId, String sourceId);
}
