package org.rcisoft.dao;

import org.rcisoft.entity.BusInspection;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author GaoLiwei
 * @date 2019/5/29
 */
@Repository
public interface BusInspectionDao extends Mapper<BusInspection> {
}
