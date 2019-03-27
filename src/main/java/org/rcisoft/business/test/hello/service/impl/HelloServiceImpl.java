package org.rcisoft.business.test.hello.service.impl;

import com.github.pagehelper.PageInfo;
import org.rcisoft.base.result.PageUtil;
import org.rcisoft.business.test.auth.dao.SysUserDao2;
import org.rcisoft.business.test.hello.service.HelloService;
import org.rcisoft.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by JiChao on 2018/5/29.
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private SysUserDao2 sysUserDao2;

    @Override
    public SysUser selectUser(SysUser sysUser) {
        return sysUserDao2.selectOne(sysUser);
    }

    @Override
    public PageInfo<SysUser> selectUserForPage() {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNotNull("updateTime");
        List<SysUser> list = sysUserDao2.selectByExample(example);
        return PageUtil.pageResult(list);
    }

    @Override
    public int selectUserCount(String id) {
        return sysUserDao2.selectUserCount();
    }
}
