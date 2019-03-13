package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysSource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 15:20
 **/
@Repository
public interface SysSourceDao extends Mapper<SysSource> {

    /**
     * 查询参数来源信息
     */
    @Select("SELECT * FROM sys_source;")
    @ResultType(SysSource.class)
    List<SysSource> querySourceInfo();
}
