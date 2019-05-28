package org.rcisoft.business.whole.menu.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.whole.menu.entity.UserRoleMenuDto;
import org.rcisoft.entity.SysMenu;
import org.rcisoft.entity.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserRoleDao extends Mapper<SysUser>{
    /**
     * 根据用户名查询该用户角色能访问的资源
     * Create by Hukaihan
     * @param username
     * @return
     */
//    @Select("<script>select sm.id,sm.url,sm.name from sys_user su , sys_user_role_mid surm , sys_role_menu_mid srmm , sys_menu sm" +
//            " where su.id = surm.user_id and surm.role_id = srmm.role_id and srmm.menu_id = sm.id and su.username = #{username} </script>")
//    List<SysMenu> userMenu(@Param("username") String username);
    @Select("select sm.id,\n" +
            "sm.name,sm.url,sm.pid,sm.level,sm.ordered " +
            "from sys_role_menu_mid rm inner join sys_menu sm on rm.menu_id=sm.id " +
            "where rm.role_id=(select type from sys_user where username =#{username})")
    List<UserRoleMenuDto> userRoleMenu(@Param("username") String username);
}
