package org.rcisoft.dao;

import org.rcisoft.entity.SysMenu;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author GaoLiwei
 * @date 2019/4/23
 */
@Repository
public interface SysMenuDao extends Mapper<SysMenu> {
}
