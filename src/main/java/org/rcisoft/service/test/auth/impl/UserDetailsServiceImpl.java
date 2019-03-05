package org.rcisoft.service.test.auth.impl;

import org.rcisoft.dao.test.auth.SysRoleDao;
import org.rcisoft.dao.test.auth.SysUserDao;
import org.rcisoft.entity.SysRole;
import org.rcisoft.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JiChao on 2018/6/5.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.selectByName(s);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
        System.out.println("username:"+sysUser.getUsername()+";password:"+sysUser.getPassword());
        List<SysRole> sysRoles = sysRoleDao.selectAll();
        sysUser.setRoles(sysRoles);
//        return JwtUserFactory.create(sysUser);
        return sysUser;
    }
}
