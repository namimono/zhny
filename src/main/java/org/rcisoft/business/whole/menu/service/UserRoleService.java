package org.rcisoft.business.whole.menu.service;

import org.rcisoft.business.whole.menu.entity.FirstMenu;

import java.util.List;

public interface UserRoleService {
    /**
     * 根据用户名查询可以访问的菜单
     * @param token
     * @return
     */
    List<FirstMenu>  userRoleMenu(String token);
}
