package org.rcisoft.business.test.auth.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by JiChao on 2018/6/5.
 */
@Repository
public interface SysUserDao2 extends Mapper<SysUser> {

    @Select("<script>select * from sys_user where username=#{username}</script>")
    @ResultType(SysUser.class)
    SysUser selectByName(@Param("username") String username);

    @Select("<script>select count(1) from sys_user</script>")
    @ResultType(Integer.class)
    Integer selectUserCount();

}
