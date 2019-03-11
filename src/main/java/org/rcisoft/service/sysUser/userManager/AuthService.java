package org.rcisoft.service.sysUser.userManager;

import org.apache.ibatis.annotations.Param;
import org.rcisoft.entity.SysUser;

import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:55 2019/3/6
 */
public interface AuthService {
    Integer register(SysUser userToAdd);
    String login(String username,String password);
    String refresh(String oldToken);
    Map<String,Object> userMenu(String oldtoken);
}
