package org.rcisoft.business.system.roleuser.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.jwt.JwtTokenUtil;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.base.util.ZhnyUtils;
import org.rcisoft.business.system.roleuser.dao.SysUserMenuDao;
import org.rcisoft.business.system.roleuser.entity.IdAndPassword;
import org.rcisoft.business.system.roleuser.entity.ProjectName;
import org.rcisoft.business.system.roleuser.entity.RolePermissionFirst;
import org.rcisoft.business.system.roleuser.entity.RolePermissionSecond;
import org.rcisoft.business.system.roleuser.service.SysUserMenuService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GaoLiwei
 * @date 2019/4/23
 */
@Service
public class SysUserMenuServiceImpl implements SysUserMenuService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${password.default}")
    private String password;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysUserMenuDao sysUserMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysPrincipalDao sysPrincipalDao;
    @Autowired
    private SysInspectorDao sysInspectorDao;
    @Autowired
    private SysAuthenticatorDao sysAuthenticatorDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuMidDao sysRoleMenuMidDao;
    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private SysUserProjectMidDao sysUserProjectMidDao;


    @Override
    public List<RolePermissionFirst> getRolePermission(String flag) {
        //要返回的数据的一级菜单权限
        List<RolePermissionFirst> rolePermissionFirstList = new ArrayList<>();

        if (ifInUser(flag)) {
            //查出所有菜单
            Example example = new Example(SysMenu.class);
            example.setOrderByClause("ordered asc");
            List<SysMenu> sysAllMenuList = sysMenuDao.selectByExample(example);
            if (sysAllMenuList.size() > 0) {
                //把所有菜单按照一级，二级菜单进行分组
                Map<Object, List> allMenuGroup = ZhnyUtils.groupListByName(sysAllMenuList, "level");

                //得到所有的1级菜单
                List<SysMenu> allMenuFirstList = allMenuGroup.get(1);
                //得到所有的2级菜单
                List<SysMenu> allMenuSecondList = allMenuGroup.get(2);

                if (null != allMenuFirstList) {
                    for (SysMenu sysMenuFirst : allMenuFirstList) {
                        //要返回的一级菜单数据
                        RolePermissionFirst allMenuFirsts = new RolePermissionFirst();
                        //要返回的二级菜单数据
                        List<RolePermissionSecond> allMenuSeconds = new ArrayList<>();

                        if (null != allMenuSecondList) {
                            for (SysMenu sysMenuSecond : allMenuSecondList) {
                                //如果这个二级菜单属于这个一级菜单
                                if (sysMenuSecond.getPid().equals(sysMenuFirst.getId())) {
                                    RolePermissionSecond rolePermissionSecond = new RolePermissionSecond();
                                    rolePermissionSecond.setId(sysMenuSecond.getId());
                                    rolePermissionSecond.setName(sysMenuSecond.getName());
                                    rolePermissionSecond.setFlag(0);
                                    allMenuSeconds.add(rolePermissionSecond);
                                }
                            }
                        }

                        //封装数据
                        allMenuFirsts.setId(sysMenuFirst.getId());
                        allMenuFirsts.setName(sysMenuFirst.getName());
                        allMenuFirsts.setFlag(0);
                        allMenuFirsts.setRolePermissionSecondList(allMenuSeconds);

                        //将数据增加到要返回的list中
                        rolePermissionFirstList.add(allMenuFirsts);

                    }
                }


                //查出这个角色的菜单
                List<SysMenu> sysRoleMenuList = sysUserMenuDao.getRolePermission(flag);
                if (sysRoleMenuList.size() > 0) {
                    //把角色菜单按照一级，二级菜单进行分组
                    Map<Object, List> roleMenuGroup = ZhnyUtils.groupListByName(sysRoleMenuList, "level");
                    //得到这个角色的1级菜单
                    List<SysMenu> roleMenuFirstList = roleMenuGroup.get(1);
                    //得到这个角色的2级菜单
                    List<SysMenu> roleMenuSecondList = roleMenuGroup.get(2);
                    //遍历处理好的所有一级菜单栏，进行匹配，确定该角色拥有哪些菜单的查看权限
                    for (RolePermissionFirst rolePermissionFirst : rolePermissionFirstList) {
                        if (null != roleMenuFirstList) {
                            //遍历这个角色拥有的一级菜单的权限
                            for (SysMenu roleMenuFirst : roleMenuFirstList) {
                                //如果这个角色拥有当前一级菜单的权限
                                if (roleMenuFirst.getId().equals(rolePermissionFirst.getId())) {
                                    //获得处理好的一级菜单下的所有二级菜单
                                    List<RolePermissionSecond> allMenuSeconds = rolePermissionFirst.getRolePermissionSecondList();
                                    //遍历处理好的二级菜单，进行匹配，确定该角色拥有哪些菜单的查看权限
                                    for (RolePermissionSecond rolePermissionSecond : allMenuSeconds) {
                                        if (null != roleMenuSecondList) {
                                            //遍历这个角色拥有的二级菜单的权限
                                            for (SysMenu roleMenuSecond : roleMenuSecondList) {
                                                //如果这个角色拥有当前二级菜单,则设置状态位为1
                                                if (roleMenuSecond.getId().equals(rolePermissionSecond.getId())) {
                                                    rolePermissionFirst.setFlag(1);
                                                    rolePermissionSecond.setFlag(1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }
        return rolePermissionFirstList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer setRolePermission(List<SysRoleMenuMid> sysRoleMenuMidList) {
        if (sysRoleMenuMidList.size() > 0) {
            String roleId = sysRoleMenuMidList.get(0).getRoleId();
            SysRoleMenuMid sysRoleMenuMid = new SysRoleMenuMid();
            sysRoleMenuMid.setRoleId(roleId);
            sysRoleMenuMidDao.delete(sysRoleMenuMid);
            for (SysRoleMenuMid roleMenuMid : sysRoleMenuMidList) {
                roleMenuMid.setId(UuidUtil.create32());
            }
            return sysRoleMenuMidDao.setRolePermission(sysRoleMenuMidList);

        }
        return 0;
    }

    @Override
    public List listRoleUser(String flag) {
        //如果flag是1，2，3中的一种，则信息在用户表查询
        if (ifInUser(flag)) {
            SysUser sysUser = new SysUser();
            sysUser.setType(Integer.parseInt(flag));
            //将密码设置为空
            List<SysUser> sysUsers = sysUserDao.select(sysUser);
            for (SysUser user : sysUsers) {
                user.setPassword(null);
            }
            return sysUsers;
        }

        //团队负责人标志
        String principalFlag = "4";
        if (principalFlag.equals(flag)) {
            return sysPrincipalDao.selectAll();
        }

        //巡检员标志
        String inspectorFlag = "5";
        if (inspectorFlag.equals(flag)) {
            List<SysInspector> sysInspectors = sysInspectorDao.selectAll();
            for (SysInspector sysInspector : sysInspectors) {
                sysInspector.setPassword(null);
            }
            return sysInspectors;
        }

        //认定员标志
        String authenticatorFlag = "6";
        if (authenticatorFlag.equals(flag)) {
            return sysAuthenticatorDao.selectAll();
        }

        return null;
    }

    @Override
    public Integer deleteRoleUser(String flag, String id) {
        //如果flag是1，2，3中的一种，则信息在用户表查询
        if (ifInUser(flag)) {
            return sysUserDao.deleteByPrimaryKey(id);
        }

        //团队负责人标志
        String principalFlag = "4";
        if (principalFlag.equals(flag)) {
            return sysPrincipalDao.deleteByPrimaryKey(id);
        }

        //巡检员标志
        String inspectorFlag = "5";
        if (inspectorFlag.equals(flag)) {
            return sysInspectorDao.deleteByPrimaryKey(id);
        }

        //认定员标志
        String authenticatorFlag = "6";
        if (authenticatorFlag.equals(flag)) {
            return sysAuthenticatorDao.deleteByPrimaryKey(id);
        }

        return null;
    }

    @Override
    public Integer saveSysUser(SysUser sysUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        sysUser.setId(UuidUtil.create32());
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        return sysUserDao.insertSelective(sysUser);
    }

    @Override
    public Integer updateSysUser(SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        return sysUserDao.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public Integer savePrincipal(SysPrincipal sysPrincipal) {
        sysPrincipal.setId(UuidUtil.create32());
        sysPrincipal.setCreateTime(new Date());
        return sysPrincipalDao.insertSelective(sysPrincipal);
    }

    @Override
    public Integer updatePrincipal(SysPrincipal sysPrincipal) {
        return sysPrincipalDao.updateByPrimaryKeySelective(sysPrincipal);
    }

    @Override
    public Integer saveInspector(SysInspector sysInspector) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        sysInspector.setId(UuidUtil.create32());
        sysInspector.setPassword(encoder.encode(sysInspector.getPassword()));
        sysInspector.setCreateTime(new Date());
        return sysInspectorDao.insertSelective(sysInspector);
    }

    @Override
    public Integer updateInspector(SysInspector sysInspector) {
        String password = sysInspector.getPassword();
        if (StringUtils.isNotEmpty(password)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            sysInspector.setPassword(encoder.encode(password));
        }
        return sysInspectorDao.updateByPrimaryKeySelective(sysInspector);
    }

    @Override
    public Integer saveAuthenticator(SysAuthenticator sysAuthenticator) {
        sysAuthenticator.setId(UuidUtil.create32());
        sysAuthenticator.setCreateTime(new Date());
        return sysAuthenticatorDao.insertSelective(sysAuthenticator);
    }

    @Override
    public Integer updateAuthenticator(SysAuthenticator sysAuthenticator) {
        return sysAuthenticatorDao.updateByPrimaryKeySelective(sysAuthenticator);
    }

    @Override
    public Integer resetPassWord(String flag, String id) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (ifInUser(flag)) {
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            sysUser.setPassword(encoder.encode(password));
            sysUser.setUpdateTime(new Date());
            return sysUserDao.updateByPrimaryKeySelective(sysUser);
        }

        //巡检员标志
        String inspectorFlag = "5";
        if (inspectorFlag.equals(flag)) {
            SysInspector sysInspector = new SysInspector();
            sysInspector.setId(id);
            sysInspector.setPassword(encoder.encode(password));
            return sysInspectorDao.updateByPrimaryKeySelective(sysInspector);
        }

        return 0;
    }

    @Override
    public Integer changePassword(SysUser sysUser) {
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        return sysUserDao.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public List<ProjectName> listProjectName(String id) {
        //查出所有项目
        List<ProjectName> allProjectNameList = busProjectDao.listProjectName();

        //查出用户拥有的项目
        SysUserProjectMid sysUserProjectMid = new SysUserProjectMid();
        sysUserProjectMid.setUserId(id);
        List<SysUserProjectMid> userProjectMidList = sysUserProjectMidDao.select(sysUserProjectMid);

        //遍历所有项目，与用户拥有的项目进行匹配
        for (ProjectName allProjectName : allProjectNameList) {
            //设置标志位为0
            allProjectName.setFlag(0);
            //遍历用户拥有的项目，进行匹配
            for (SysUserProjectMid userProjectMid : userProjectMidList) {
                //如果相等，则表示用户拥有这个项目
                if (allProjectName.getProjectId().equals(userProjectMid.getProjectId())) {
                    //拥有这个项目，则设置标志位为1
                    allProjectName.setFlag(1);
                }
            }
        }
        return allProjectNameList;
    }

    @Override
    public Integer saveUserProject(List<SysUserProjectMid> sysUserProjectMidList) {
        if (sysUserProjectMidList.size() > 0) {
            //用户Id
            SysUserProjectMid sysUserProjectMid = new SysUserProjectMid();
            sysUserProjectMid.setUserId(sysUserProjectMidList.get(0).getUserId());
            //删除当前用户拥有的项目
            sysUserProjectMidDao.delete(sysUserProjectMid);

            //设置Id
            for (SysUserProjectMid sysUserProject : sysUserProjectMidList) {
                sysUserProject.setId(UuidUtil.create32());
            }

            return sysUserProjectMidDao.saveUserProject(sysUserProjectMidList);

        }
        return 0;
    }


    /**
     * 判断状态位是不是1，2，3
     *
     * @author GaoLiWei
     * @date 9:42 2019/4/24
     **/
    private Boolean ifInUser(String flag) {

        //超级管理员标志
        String superAdminFlag = "1";
        //管理员标志
        String adminFlag = "2";
        //业主标志
        String userFlag = "3";
        if (superAdminFlag.equals(flag) || adminFlag.equals(flag) || userFlag.equals(flag)) {
            return true;
        }
        return false;
    }


    @Override
    public List<SysMenu> userMenu(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        List<SysMenu> list = sysUserMenuDao.userMenu(username);
        return list;
    }

    @Override
    public Integer updatePassword(IdAndPassword idAndPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (ifInUser(idAndPassword.getFlag())) {
            //获得用户信息
            SysUser user = sysUserDao.selectByPrimaryKey(idAndPassword.getId());
            //验证密码
            if (encoder.matches(idAndPassword.getOldPassword(), user.getPassword())){
                SysUser sysUser = new SysUser();
                sysUser.setId(idAndPassword.getId());
                sysUser.setPassword(encoder.encode(idAndPassword.getNewPassword()));
                sysUser.setUpdateTime(new Date());
                return sysUserDao.updateByPrimaryKeySelective(sysUser);
            }

        }

        //巡检员标志
        String inspectorFlag = "5";
        if (inspectorFlag.equals(idAndPassword.getFlag())) {
            //获得巡检员信息
            SysInspector inspector = sysInspectorDao.selectByPrimaryKey(idAndPassword.getId());
            //验证密码
            if (encoder.matches(idAndPassword.getOldPassword(), inspector.getPassword())){
                SysInspector sysInspector = new SysInspector();
                sysInspector.setId(idAndPassword.getId());
                sysInspector.setPassword(encoder.encode(idAndPassword.getNewPassword()));
                return sysInspectorDao.updateByPrimaryKeySelective(sysInspector);
            }

        }


        return 0;
    }


}
