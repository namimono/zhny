package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.FormulaVariableData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/21 9:23
 **/
@Repository
public interface FormulaDao {

    /**
     * 根据公式ID获取对应的变量信息ID查询参数信息
     */
    @Select("SELECT a.id AS 'variableId',a.variable,a.formula_id AS 'formulaId',\n" +
            "b.coding AS 'paramFirstCoding',c.coding AS 'paramSecondCoding',f.`name` AS 'formulaName'\n" +
            "FROM bus_variable a LEFT JOIN bus_formula f ON f.id = a.formula_id,\n" +
            "bus_param_first b,bus_param_second c\n" +
            "WHERE a.formula_id in (${formulaIds})\n" +
            "AND b.id = a.param_first_id\n" +
            "AND c.id = a.param_second_id;")
    List<FormulaVariableData> queryParamsByFormula(@Param("formulaIds") String formulaIds);
}
