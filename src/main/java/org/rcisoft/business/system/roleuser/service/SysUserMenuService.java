package org.rcisoft.business.system.roleuser.service;

import org.rcisoft.business.system.roleuser.entity.IdAndPassword;
import org.rcisoft.business.system.roleuser.entity.ProjectName;
import org.rcisoft.business.system.roleuser.entity.RolePermissionFirst;
import org.rcisoft.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/23
 */
public interface SysUserMenuService {
    List<SysMenu> userMenu(String oldtoken);

    /**
     * 修改密码
     * @return  Integer
     */
    Integer updatePassword(IdAndPassword idAndPassword);

    /**
     * 根据角色标志位，查询这个角色对应的权限（显示的菜单）
     * @param flag
     * @return List<RolePermissionFirst>
     */
    List<RolePermissionFirst> getRolePermission(String flag);


    /**
     * 修改角色的权限
     * @param sysRoleMenuMidList
     * @return Integer
     */
    Integer setRolePermission(List<SysRoleMenuMid> sysRoleMenuMidList);


    /**
     * 查出某个角色下的所有用户信息
     * @param flag
     * @return List<SysUser>
     */
    List listRoleUser(String flag);

    /**
     * 删除某个角色下的某个用户
     * @param flag
     * @param id
     * @return Integer
     */
    Integer deleteRoleUser(String flag,String id);


    /**
     * 新增系统用户信息
     * @param sysUser
     * @return Integer
     */
    Integer saveSysUser(SysUser sysUser);

    /**
     * 编辑系统用户信息
     * @param sysUser
     * @return Integer
     */
    Integer updateSysUser(SysUser sysUser);

    /**
     * 新增团队负责人
     * @param sysPrincipal
     * @return Integer
     */
    Integer savePrincipal(SysPrincipal sysPrincipal);


    /**
     * 编辑团队负责人
     * @param sysPrincipal
     * @return Integer
     */
    Integer updatePrincipal(SysPrincipal sysPrincipal);


    /**
     * 新增巡检员
     * @param sysInspector
     * @return Integer
     */
    Integer saveInspector(SysInspector sysInspector);

    /**
     * 编辑巡检员
     * @param sysInspector
     * @return Integer
     */
    Integer updateInspector(SysInspector sysInspector);


    /**
     * 新增认定员
     * @param sysAuthenticator
     * @return Integer
     */
    Integer saveAuthenticator(SysAuthenticator sysAuthenticator);

    /**
     * 编辑认定员
     * @param sysAuthenticator
     * @return Integer
     */
    Integer updateAuthenticator(SysAuthenticator sysAuthenticator);


    /**
     * 重置密码
     * @param flag
     * @param id
     * @return Integer
     */
    Integer resetPassWord(String flag, String id);

    /**
     * 修改密码
     * @param oldPass
     * @param newPass
     * @return
     */
    Integer changePassword(HttpServletRequest request, String oldPass, String newPass);


    /**
     * 查找所有项目的名称
     * @return List<ProjectName>
     * @param id
     */
    List<ProjectName> listProjectName(String id);

    /**
     * 新增用户项目
     * @param sysUserProjectMidList
     * @return Integer
     */
    Integer saveUserProject(List<SysUserProjectMid> sysUserProjectMidList);


}
