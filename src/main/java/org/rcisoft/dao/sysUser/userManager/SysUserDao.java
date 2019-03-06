package org.rcisoft.dao.sysUser.userManager;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by Minghui Xu
 * Time：2019/3/5 13:28
 **/
@Component("SysUserDao2")
@Repository
public interface SysUserDao extends Mapper<SysUser> {



    /**
     * 根据用户名查询信息
     * Create by Minghui Xu
     * @param username
     * @return
     */
    @Select("<script>select * from sys_user where username = #{username}</script>")
    @ResultType(SysUser.class)
    SysUser selectByName(@Param("username") String username);

    /**
     * 查询用户数量
     * Create by Minghui Xu
     * @return
     */
    @Select("<script>select count(1) from sys_user</script>")
    @ResultType(SysUser.class)
    Integer selectUserCount();




}
