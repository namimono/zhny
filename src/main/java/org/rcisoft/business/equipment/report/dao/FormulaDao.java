package org.rcisoft.business.equipment.report.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.report.entity.FormulaAndVariables;
import org.rcisoft.business.equipment.report.entity.FormulaEntity;
import org.rcisoft.business.equipment.report.entity.VariableParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/21 9:23
 **/
@Repository
public interface FormulaDao {

    /**
     * 根据项目id查询所有公式
     * @param projectId
     * @return
     */
    @Select("<script>" +
            "select * from bus_formula where project_id = #{projectId}" +
            "</script>")
    @ResultType(FormulaAndVariables.class)
    List<FormulaAndVariables> queryFormulasByProjectId(@Param("projectId") String projectId);

    /**
     * 根据项目id查询所有公式
     * @param projectId
     * @return
     */
    @Select("<script>" +
            "select * from bus_formula where project_id = #{projectId}" +
            "</script>")
    @ResultType(FormulaEntity.class)
    List<FormulaEntity> queryFormulaEntity(@Param("projectId") String projectId);

    @Select("<script>" +
            "select v.formula_id, v.variable, f.coding codingFirst, s.coding codingSecond from bus_variable v " +
            "left join bus_param_first f on f.id = v.param_first_id " +
            "left join bus_param_second s on s.id = v.param_second_id " +
            "where v.project_id = #{projectId} " +
            "order by v.create_time asc" +
            "</script>")
    @ResultType(VariableParam.class)
    List<VariableParam> queryVariableParam(@Param("projectId") String projectId);
}
