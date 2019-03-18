package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysAuthenticator;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/15 14:58
 **/
@Repository
public interface SysAuthenticatorDao extends Mapper<SysAuthenticator> {

    /**
     * 查询认定员信息
     */
    @Select("SELECT * FROM sys_authenticator;")
    List<SysAuthenticator> queryAuthenticator();
}
