package org.rcisoft.dao;

import org.rcisoft.entity.BusDeviceType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by JiChao on 2019/4/17.
 */
@Repository
public interface BusDeviceTypeDao extends Mapper<BusDeviceType> {
}
