package org.rcisoft.business.equipment.report.service;

import org.rcisoft.business.equipment.report.entity.VariableAndParam;
import org.rcisoft.entity.BusFormula;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.entity.BusVariable;
import org.rcisoft.entity.SysSource;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 增加公式信息
     */
    int addFormula(BusFormula busFormula);

    /**
     * 删除公式信息
     */
    int deleteFormula(BusFormula busFormula);

    /**
     * 修改公式信息
     */
    int updateFormula(BusFormula busFormula);

    /**
     * 根据公式ID和项目ID查询变量
     */
    List<VariableAndParam> queryVariable(String projectId, String formulaId);

    /**
     * 查询参数来源
     */
    List<SysSource> querySource();

    /**
     * 增加变量信息
     */
    int addVariable(BusVariable busVariable);

    /**
     * 删除变量信息
     */
    int deleteVariable(BusVariable busVariable);

    /**
     * 修改变量信息
     */
    int updateVariable(BusVariable busVariable);

    /**
     * 根据项目ID和参数来源查询二级参数信息
     */
    List<BusParamSecond> queryParamSecondByProId(String projectId, String sourceId);

    /**
     * 导出公式数据
     */
    void downloadFormulaData(HttpServletResponse response,String projectId,String beginTime,String endTime,List<BusFormula> formulaList);

    /**
     * 查询计算公式结果
     */
    List queryResult(String proId,String beginTime,String endTime);
}
