package org.rcisoft.dao;

import org.rcisoft.base.util.SpecialBatchMapper;
import org.rcisoft.entity.BusIndoorParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 土豆儿
 * @date 2019/4/11 16:41
 **/
@Repository
public interface BusIndoorParamDao extends Mapper<BusIndoorParam>, SpecialBatchMapper<BusIndoorParam> {
}
