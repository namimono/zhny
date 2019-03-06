package org.rcisoft.dao;

import org.rcisoft.entity.EnergyPrice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Create by 土豆儿
 * Time：2019/3/6 12:22
 **/
@Repository
public interface EnergyPriceDao extends Mapper<EnergyPrice> {
}
