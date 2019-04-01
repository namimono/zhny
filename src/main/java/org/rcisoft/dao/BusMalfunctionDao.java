package org.rcisoft.dao;

import org.rcisoft.entity.BusMalfunction;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by JiChao on 2019/3/29.
 */
@Repository
public interface BusMalfunctionDao extends Mapper<BusMalfunction> {
}
