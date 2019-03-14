package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.dao.BusParamFirstDao;
import org.rcisoft.dao.EnergyConfigDao;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.EnergyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:50
 **/
@Service
public class OtherConfigServiceImpl implements OtherConfigService {

    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private EnergyConfigDao energyConfigDao;

    /**
     * 根据参数来源查询表具
     */
    @Override
    public List<BusParamFirst> queryParamFirstBySource(BusParamFirst busParamFirst){
        return busParamFirstDao.queryParamFirstBySource(busParamFirst);
    }

    /**
     * 根据项目设备等ID查询能耗分类信息
     */
    @Override
    public List<EnergyTypeConfig> queryTypeNameByConfig(EnergyTypeConfig energyTypeConfig){
        return energyConfigDao.queryTypeNameByConfig(energyTypeConfig);
    }

    /**
     * 修改能源配置信息
     */
    @Override
    public int updateEnergyConfig(EnergyConfig energyConfig){
        return energyConfigDao.updateByPrimaryKeySelective(energyConfig);
    }


}
