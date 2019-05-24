package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusFormula;
import org.rcisoft.entity.BusVariable;

import java.util.List;

/**
 * 公式和变量参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormulaParams {

    /**
     * 公式
     */
    private BusFormula busFormula;

    /**
     * 参数集合
     */
    private List<BusVariable> variableList;

    /**
     * 需要删除的参数ids，逗号分隔
     */
    private String deleteVariableIds;

    /**
     * 项目id
     */
    private String projectId;
    
}
