package org.rcisoft.dao;

import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Repository
public interface EnergyPlanningRecordDao extends Mapper<EnergyPlanningRecord> {
}
