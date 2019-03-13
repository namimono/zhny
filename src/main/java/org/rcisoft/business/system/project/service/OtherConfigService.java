package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.entity.BusParamFirst;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:48
 **/

public interface OtherConfigService {

    /**
     * 根据参数来源查询表具
     */
    List<BusParamFirst> queryParamFirstBySource(BusParamFirst busParamFirst);

    /**
     * 根据项目设备等ID查询能耗分类信息
     */
    List<EnergyTypeConfig> queryTypeNameByConfig(EnergyTypeConfig energyTypeConfig);
}
