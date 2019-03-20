package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.entity.EnergyParamLibrary;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
public interface EnergyParamLibraryService {

    /**
     *  根据上级参数值，寻找下级参数
     * @param energyParamLibrary
     * @return
     */
    List<EnergyParamLibrary> listEnergyParamLibraryByParam(EnergyParamLibrary energyParamLibrary);
}
