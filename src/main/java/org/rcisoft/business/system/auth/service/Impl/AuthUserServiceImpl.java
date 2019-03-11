package org.rcisoft.business.system.auth.service.Impl;

import org.rcisoft.base.jwt.JwtTokenUtil;
import org.rcisoft.business.system.auth.service.AuthService;
import org.rcisoft.business.system.roleuser.dao.SysUserMenuDao;
import org.rcisoft.dao.SysUserDao;
import org.rcisoft.entity.SysMenu;
import org.rcisoft.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:08 2019/3/6
 */
@Service
public class AuthUserServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private SysUserMenuDao sysUserMenuDao;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthUserServiceImpl(
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
    @Transactional
    @Override
    public Integer register(SysUser sysUser) {
        if(sysUserMenuDao.selectOne(sysUser) != null){
            return null;
        }
        sysUser.setId(UUID.randomUUID().toString().replaceAll("-",""));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = sysUser.getPassword();
        sysUser.setPassword(encoder.encode(rawPassword));
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return sysUserMenuDao.insert(sysUser);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String Token = jwtTokenUtil.generateToken(userDetails);
        return Token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        SysUser user = (SysUser) userDetailsService.loadUserByUsername(username);
        if(jwtTokenUtil.canTokenBeRefreshed(token,user.getUpdateTime())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public List<SysMenu> userMenu(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        List<SysMenu> list = sysUserMenuDao.userMenu(username);
        return list;
    }
}
