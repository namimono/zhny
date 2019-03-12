package org.rcisoft.business.equipment.report.service;

import org.rcisoft.entity.BusFormula;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:36
 **/

public interface FormulaOperationService {

    /**
     * 根据项目ID查询公式信息
     */
    List<BusFormula> queryFormula(String projectId);
}
