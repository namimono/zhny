package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusFormula;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:20
 **/
@Repository
public interface BusFormulaDao extends Mapper<BusFormula> {

    /**
     * 根据项目ID查询公式信息
     */
    @Select("SELECT * FROM bus_formula WHERE project_id = #{projectId}")
    List<BusFormula> queryFormula(@Param("projectId") String projectId);
}
