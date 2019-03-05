package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysInspector;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:24
 **/
@Repository
public interface SysInspectorDao extends Mapper<SysInspector> {

    /**
     * 获取巡查员信息
     */
    @Select("SELECT * FROM sys_inspector;")
    @ResultType(SysInspector.class)
    List<Map<String,Object>> queryInspectorInfo();
}
