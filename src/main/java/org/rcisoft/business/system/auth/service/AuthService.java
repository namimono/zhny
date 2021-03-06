package org.rcisoft.business.system.auth.service;

import org.rcisoft.entity.SysMenu;
import org.rcisoft.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:55 2019/3/6
 */
public interface AuthService {
    Integer register(SysUser userToAdd);
    Map<String, Object> login(String username, String password);
    String refresh(String oldToken);
    List<SysMenu> userMenu(String oldtoken);
}
