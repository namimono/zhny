package org.rcisoft.service.test.auth;

import org.rcisoft.entity.SysUser;

public interface AuthServiceTest {
    Integer register(SysUser userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
