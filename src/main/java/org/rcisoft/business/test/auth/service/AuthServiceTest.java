package org.rcisoft.business.test.auth.service;

import org.rcisoft.entity.SysUser;

public interface AuthServiceTest {
    Integer register(SysUser userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
