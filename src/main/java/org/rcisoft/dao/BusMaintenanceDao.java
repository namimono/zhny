package org.rcisoft.dao;

import org.rcisoft.entity.BusMaintenance;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by JiChao on 2019/4/1.
 */
@Repository
public interface BusMaintenanceDao extends Mapper<BusMaintenance> {
}
