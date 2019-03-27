package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.base.util.SpecialBatchMapper;
import org.rcisoft.entity.BusParamSecond;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 15:47
 **/
@Repository
public interface BusParamSecondDao extends Mapper<BusParamSecond>, SpecialBatchMapper<BusParamSecond> {

    /**
     * 查询二级参数信息
     */
    @Select("SELECT * FROM bus_param_second WHERE \n" +
            "param_first_id = #{paramFirstId} \n" +
            "AND \n" +
            "project_id = #{projectId} \n" +
            "AND \n" +
            "system_id = #{systemId};")
    @ResultType(BusParamSecond.class)
    List<BusParamSecond> queryParamSecondInfo(BusParamSecond busParamSecond);

    /**
     * 根据项目ID和参数来源查询二级参数信息
     */
    @Select("SELECT * FROM bus_param_second WHERE \n" +
            "project_id = #{projectId} \n" +
            "AND \n" +
            "source_id = #{sourceId};")
    @ResultType(BusParamSecond.class)
    List<BusParamSecond> queryParamSecondByProId(@Param("projectId") String projectId,@Param("sourceId") String sourceId);

    /**
     * 根据一级参数IDs查询二级参数信息
     */
    @Select("SELECT * FROM bus_param_second WHERE param_first_id in (${paramFirstIds})")
    @ResultType(BusParamSecond.class)
    List<BusParamSecond> queryParamSecondByIds(@Param("paramFirstIds") String paramFirstIds);
}
