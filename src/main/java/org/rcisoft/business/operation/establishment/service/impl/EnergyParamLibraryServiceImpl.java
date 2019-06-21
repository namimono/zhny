package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.service.EnergyParamLibraryService;
import org.rcisoft.dao.EnergyParamLibraryDao;
import org.rcisoft.entity.EnergyParamLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class EnergyParamLibraryServiceImpl implements EnergyParamLibraryService {
    @Autowired
    private EnergyParamLibraryDao energyParamLibraryDao;

    @Override
    public List<EnergyParamLibrary> listEnergyParamLibraryByParam(EnergyParamLibrary energyParamLibrary){
        Example e = new Example(EnergyParamLibrary.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("deviceId", energyParamLibrary.getDeviceId());
        e.setOrderByClause("main_value asc, main_value2 asc");
        if (energyParamLibrary.getMainValue() != null) {
            c.andEqualTo("mainValue", energyParamLibrary.getMainValue());
        }
        return energyParamLibraryDao.selectByExample(e);
    }

}
