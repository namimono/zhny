package org.rcisoft.service.sysManagement.projMaintenance;

import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStandard;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:27
 **/

public interface BasicDataService {

    /**
     *新增水电气24小时单价信息
     */
    int addPerHourPrice(List<EnergyPrice> list);

    /**
     * 新增能源标准
     */
    int addEnergyStandard(List<EnergyStandard> list);
}
