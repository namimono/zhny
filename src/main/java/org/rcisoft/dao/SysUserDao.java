package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据用户名查询菜单详情
     * Create by Minghui Xu
     * @param username
     * @return
     */
    @Select("<script>select su.id,sm.url,sm.name from sys_user su , sys_user_role_mid surm , sys_role_menu_mid srmm , sys_menu sm" +
            " where su.id = surm.user_id and surm.role_id = srmm.role_id and srmm.menu_id = sm.id and su.username = #{username} </script>")
    Map<String,Object> userMenu(@Param("username") String username);
}
