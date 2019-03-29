package org.rcisoft.business.management.distribution.service;

import org.rcisoft.business.management.distribution.entity.EnergyDistribution;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:22 2019/3/12
 */
public interface energyDistributionService {
    Map<String,Object> queryEnergyDistributed(int timeYear,String Month);
}
