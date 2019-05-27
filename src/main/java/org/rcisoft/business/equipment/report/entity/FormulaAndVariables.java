package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusFormula;
import org.rcisoft.entity.BusVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * 公式和参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormulaAndVariables extends BusFormula {

    /**
     * 参数
     */
    private List<BusVariable> variableList = new ArrayList<>();

}
