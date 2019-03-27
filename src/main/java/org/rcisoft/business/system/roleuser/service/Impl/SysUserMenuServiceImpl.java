package org.rcisoft.business.system.roleuser.service.Impl;

import org.rcisoft.base.jwt.JwtTokenUtil;
import org.rcisoft.business.system.roleuser.dao.SysUserMenuDao;
import org.rcisoft.business.system.roleuser.service.SysUserMenuService;
import org.rcisoft.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:08 2019/3/6
 */
@Service
public class SysUserMenuServiceImpl implements SysUserMenuService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private SysUserMenuDao sysUserMenuDao;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public SysUserMenuServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            SysUserMenuDao sysUserMenuDao
    ){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.sysUserMenuDao = sysUserMenuDao;
    }


    @Override
    public List<SysMenu> userMenu(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        List<SysMenu> list = sysUserMenuDao.userMenu(username);
        return list;
    }
}
