package org.rcisoft.service.sysUser.userManager.Impl;

import org.rcisoft.dao.SysUserDao;
import org.rcisoft.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:03 2019/3/6
 */
@Primary
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.selectByName(s);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
        System.out.println("username:"+sysUser.getUsername()+";password:"+sysUser.getPassword());
        return sysUser;
    }
}
