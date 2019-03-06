package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusParamFirst;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/6 15:46
 **/
@Repository
public interface BusParamFirstDao extends Mapper<BusParamFirst> {

    /**
     * 查询一级参数信息
     */
    @Select("SELECT * FROM bus_param_first WHERE project_id = #{projectId};")
    List<Map<String,Object>> queryParamFirstInfo(BusParamFirst busParamFirst);
}
