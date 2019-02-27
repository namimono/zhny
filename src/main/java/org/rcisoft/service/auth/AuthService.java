package org.rcisoft.service.auth;

import org.rcisoft.entity.SysUser;

public interface AuthService {
    Integer register(SysUser userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
