package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:28
 **/
@Repository
public interface SysUserDao extends Mapper<SysUser> {

    /**
     * 获取业主信息
     */
    @Select("SELECT * FROM sys_user WHERE type = '3';")
    @ResultType(SysUser.class)
    List<Map<String,Object>> queryOwnerInfo();
}
