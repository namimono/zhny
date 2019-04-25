package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.rcisoft.entity.SysRoleMenuMid;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/24
 */
@Repository
public interface SysRoleMenuMidDao extends Mapper<SysRoleMenuMid> {

    /**
     * 批量新增角色的权限
     *
     * @param sysRoleMenuMidList
     * @return Integer
     */
    @Insert("<script>" +
            "<foreach collection = \"sysRoleMenuMidList\" item = \"item\" separator=\";\"> " +
            " insert into sys_role_menu_mid(id, role_id, menu_id ) " +
            " values(#{item.id}, #{item.roleId}, #{item.menuId}) " +
            "</foreach>" +
            "</script>")
    Integer setRolePermission(@Param("sysRoleMenuMidList") List<SysRoleMenuMid> sysRoleMenuMidList);
}
