package org.rcisoft.business.management.distribution.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.rcisoft.business.management.distribution.dao.EnergyDistributionDao;
import org.rcisoft.business.management.distribution.entity.MoneyCost;
import org.rcisoft.business.management.distribution.entity.ProjectInfomation;
import org.rcisoft.business.management.distribution.service.energyDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:25 2019/3/12
 */
@Service
public class energyDistributionServiceImpl implements energyDistributionService {
    @Autowired
    EnergyDistributionDao energyDistributionDao;

    /**
     * 能耗分布计算
     * @param year
     * @param month
     * @return
     */
    @Override
    public List<ProjectInfomation> queryEnergyDistributed(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // 查询所有线上并且接受数据的项目
        List<ProjectInfomation> projectList = energyDistributionDao.queryProjectInfo();
        List<MoneyCost> costList = energyDistributionDao.queryProjectMoney(year, month);

        projectList.forEach(projectInfomation -> {
            String id = projectInfomation.getId();

            Date buildingAge = projectInfomation.getBuildingAge();
            calendar.setTime(buildingAge);
            projectInfomation.setBuildingAgeYear(calendar.get(Calendar.YEAR));

            Date equipmentAge = projectInfomation.getEquipmentAge();
            calendar.setTime(equipmentAge);
            projectInfomation.setEquipmentAgeYear(calendar.get(Calendar.YEAR));

            BigDecimal buildingArea = projectInfomation.getBuildingArea();
            costList.forEach(moneyCost -> {
                if (StringUtils.equals(moneyCost.getProjectId(), id)) {
                    BigDecimal moneyWater = moneyCost.getMoneyWater();
                    BigDecimal moneyElec = moneyCost.getMoneyElec();
                    BigDecimal moneyGas = moneyCost.getMoneyGas();
                    if (buildingArea.compareTo(new BigDecimal(0)) != 0) {
                        BigDecimal divide = moneyWater.add(moneyElec).add(moneyGas).divide(buildingArea, 2, BigDecimal.ROUND_HALF_UP);
                        projectInfomation.setEnergy(divide);
                    }
                }
            });
        });

        return projectList;
    }
}
