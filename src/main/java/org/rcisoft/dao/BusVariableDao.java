package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.report.entity.VariableAndParam;
import org.rcisoft.entity.BusVariable;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/15 15:16
 **/
@Repository
public interface BusVariableDao extends Mapper<BusVariable> {

    /**
     * 根据公式ID和项目ID查询变量
     */
    @Select("SELECT \n" +
            "a.id,a.variable,a.param_first_id AS 'paramFirstId',a.param_second_id AS 'paramSecondId',\n" +
            "a.formula_id AS 'formulaId',a.project_id AS 'projectId',a.create_time AS 'createTime',\n" +
            "b.coding AS 'paramSecondCoding'\n" +
            "FROM \n" +
            "bus_variable a LEFT JOIN bus_param_second b\n" +
            "ON\n" +
            "a.param_first_id = b.param_first_id \n" +
            "AND a.param_second_id = b.id\n" +
            "WHERE a.formula_id = #{formulaId} AND a.project_id = #{projectId};")
    @ResultType(VariableAndParam.class)
    List<VariableAndParam> queryVariable(@Param("projectId") String projectId,@Param("formulaId") String formulaId);
}
