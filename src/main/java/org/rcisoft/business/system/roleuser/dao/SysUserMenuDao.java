package org.rcisoft.business.system.roleuser.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysMenu;
import org.rcisoft.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:28
 **/
@Repository
public interface SysUserMenuDao extends Mapper<SysUser> {


    /**
     * 查询某个角色的菜单权限
     * @param roleId
     * @return List<SysMenu>
     */
    @Select("<script>SELECT sm.id,sm.`name`,sm.pid,sm.`level` FROM sys_role_menu_mid srmm \n" +
            "LEFT JOIN sys_menu sm ON srmm.menu_id = sm.id \n" +
            "WHERE srmm.role_id = #{roleId} </script>")
    List<SysMenu> getRolePermission(@Param("roleId") String roleId);

    /**
     * 根据用户名查询菜单详情
     * Create by Minghui Xu
     * @param username
     * @return
     */
    @Select("<script>select sm.id,sm.url,sm.name from sys_user su , sys_user_role_mid surm , sys_role_menu_mid srmm , sys_menu sm" +
            " where su.id = surm.user_id and surm.role_id = srmm.role_id and srmm.menu_id = sm.id and su.username = #{username} </script>")
    List<SysMenu> userMenu(@Param("username") String username);
}
