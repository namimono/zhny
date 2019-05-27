package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusFormula;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算结果用的对象
 * 需要的是参数的coding
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormulaEntity extends BusFormula {

    private List<VariableParam> variableParamList = new ArrayList<>();

}
