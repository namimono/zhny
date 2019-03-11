package org.rcisoft.business.system.roleuser.service;

import org.rcisoft.entity.SysMenu;
import org.rcisoft.entity.SysUser;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:55 2019/3/6
 */
public interface SysUserMenuService {
    List<SysMenu> userMenu(String oldtoken);
}
