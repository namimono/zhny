package org.rcisoft.business.equipment.report.service;


import org.rcisoft.business.equipment.report.entity.EchartResult;
import org.rcisoft.business.equipment.report.entity.FormulaAndVariables;
import org.rcisoft.business.equipment.report.entity.FormulaParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:36
 **/

public interface FormulaOperationService {

    /**
     * 添加公式和变量
     * @param formulaParams
     * @return
     */
    int editFormulaAndVariable(FormulaParams formulaParams);

    /**
     * 删除公式和变量
     * @param formulaId
     * @return
     */
    int deleteFormula(String formulaId);

    /**
     * 根据项目id查询所有的公式和公式下的变量集合
     * @param projectId
     * @return
     */
    List<FormulaAndVariables> queryFormulaAndVariale(String projectId);

    /**
     * 查询echart图的数据
     * @param projectId
     * @param date
     * @return
     */
    List<EchartResult> queryData(String projectId, String date);

    /**
     * 下载echart图数据
     * @param projectId
     * @param date
     */
    void downloadData(HttpServletRequest request, HttpServletResponse response, String projectId, String date);

}
