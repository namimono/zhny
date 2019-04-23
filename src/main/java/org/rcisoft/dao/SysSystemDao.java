package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysSystem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 14:43
 **/
@Repository
public interface SysSystemDao extends Mapper<SysSystem> {

    /**
     * 查询系统类型信息
     */
    @Select("SELECT * FROM sys_system;")
    @ResultType(SysSystem.class)
    List<SysSystem> querySysSystemInfo();
}
