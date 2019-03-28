package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.ParamFirstAndSecond;
import org.rcisoft.entity.BusParamFirst;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 15:46
 **/
@Repository
public interface BusParamFirstDao extends Mapper<BusParamFirst> {

    /**
     * 根据项目ID查询一级参数信息
     */
    @Select("SELECT * FROM bus_param_first WHERE project_id = #{projectId};")
    @ResultType(BusParamFirst.class)
    List<BusParamFirst> queryParamFirstInfo(BusParamFirst busParamFirst);

    /**
     * 根据项目ID和子系统ID查询未关联一级参数信息
     */
    @Select("SELECT * FROM bus_param_first \n" +
            "WHERE status = '0'\n" +
            "AND project_id = #{projectId} \n" +
            "AND system_id = #{systemId};")
    @ResultType(BusParamFirst.class)
    List<BusParamFirst> queryParamFirstInfoBySysId(BusParamFirst busParamFirst);

    /**
     * 数据配置联表查询一级、二级参数信息
     */
    @Select("SELECT a.coding AS 'firstCoding',\n" +
            "a.name AS 'firstName',\n" +
            "a.system_id AS 'systemId',\n" +
            "b.coding AS 'secondCoding',\n" +
            "b.name AS 'secondName',\n" +
            "b.unit\n" +
            "FROM bus_param_first a,bus_param_second b\n" +
            "WHERE a.project_id = #{projectId} AND b.param_first_id = a.id")
    @ResultType(ParamFirstAndSecond.class)
    List<ParamFirstAndSecond> queryDataParam(@Param("projectId") String projectId);

    /**
     * 根据参数来源查询一级表具参数信息
     */
    @Select("SELECT * FROM bus_param_first \n" +
            "WHERE source_id = '2'\n" +
            "AND project_id = #{projectId};")
    @ResultType(BusParamFirst.class)
    List<BusParamFirst> queryParamFirstBySource(BusParamFirst busParamFirst);
}
