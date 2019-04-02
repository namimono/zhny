package org.rcisoft.dao;

import org.rcisoft.base.util.SpecialBatchMapper;
import org.rcisoft.entity.EnergyParamLibrary;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 土豆儿
 * @date 2019/3/14 14:47
 **/
@Repository
public interface EnergyParamLibraryDao extends Mapper<EnergyParamLibrary>, SpecialBatchMapper<EnergyParamLibrary> {

}
