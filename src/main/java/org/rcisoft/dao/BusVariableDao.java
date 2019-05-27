package org.rcisoft.dao;

import org.apache.ibatis.annotations.*;
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

    /**
     * 批量新增公式参数
     * @param insertList
     * @return
     */
    @Insert("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "insert into bus_variable(id, variable, device_id, param_first_id, param_second_id, formula_id, project_id, create_time) " +
            "values(#{item.id}, #{item.variable}, #{item.deviceId}, #{item.paramFirstId}, #{item.paramSecondId}, #{item.formulaId}, #{item.projectId}, #{item.createTime})" +
            "</foreach>" +
            "</script>")
    int batchSave(List<BusVariable> insertList);

    /**
     * 批量更新公式参数
     * @param updateList
     * @return
     */
    @Update("<script>" +
            "<foreach collection=\"list\" item=\"item\" separator=\";\">" +
            "update bus_variable set " +
            "variable = #{item.variable}, device_id = #{item.deviceId}, " +
            "param_first_id = #{item.paramFirstId}, param_second_id = #{item.paramSecondId} " +
            "where id = #{item.id}" +
            "</foreach>" +
            "</script>")
    int batchUpdate(List<BusVariable> updateList);

    /**
     * 批量删除公式参数
     * @param delIds
     * @return
     */
    @Delete("<script>" +
            "<foreach collection=\"array\" item=\"item\" separator=\";\">" +
            "delete from bus_variable where id = #{item}" +
            "</foreach>" +
            "</script>")
    int batchDelete(String[] delIds);
}
