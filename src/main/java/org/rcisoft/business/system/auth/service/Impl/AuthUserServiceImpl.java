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
    private SysUserDao sysUserDao;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthUserServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            SysUserMenuDao sysUserMenuDao,
            SysUserDao sysUserDao
    ){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.sysUserMenuDao = sysUserMenuDao;
        this.sysUserDao = sysUserDao;
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
    public Map<String, Object> login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (SysUser) authentication.getPrincipal();// userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);

        SysUser sysUser = sysUserDao.selectByPrimaryKey(((SysUser) userDetails).getId());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("name", sysUser.getRealName());
        return resultMap;
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
