package org.rcisoft.business.management.distribution.service;

import org.rcisoft.business.management.distribution.entity.ProjectInfomation;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:22 2019/3/12
 */
public interface energyDistributionService {
    List<ProjectInfomation> queryEnergyDistributed(int year, int month);
}
