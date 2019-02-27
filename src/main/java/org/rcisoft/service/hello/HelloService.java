package org.rcisoft.service.hello;

import com.github.pagehelper.PageInfo;
import org.rcisoft.entity.SysUser;

/**
 * Created by JiChao on 2018/5/29.
 */
public interface HelloService {

    SysUser selectUser(SysUser sysUser);

    PageInfo<SysUser> selectUserForPage();

    int selectUserCount(String id);

}
