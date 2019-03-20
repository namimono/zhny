package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.service.EnergyParamLibraryService;
import org.rcisoft.dao.EnergyParamLibraryDao;
import org.rcisoft.entity.EnergyParamLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return energyParamLibraryDao.select(energyParamLibrary);
    }

}
