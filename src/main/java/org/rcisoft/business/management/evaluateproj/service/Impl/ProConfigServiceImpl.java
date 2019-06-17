package org.rcisoft.business.management.evaluateproj.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.rcisoft.business.management.distribution.dao.EnergyDistributionDao;
import org.rcisoft.business.management.distribution.entity.MoneyCost;
import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.business.management.evaluateproj.service.ProConfigService;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.BusTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Create by MingHui Xu
 **/

@Service
public class ProConfigServiceImpl implements ProConfigService {

    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private EnergyDistributionDao energyDistributionDao;

    /**
     * 获取关于项目的所有信息
     */
    @Override
    public List<ProjectAssessment> queryAllProjInfo() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        List<MoneyCost> costList = energyDistributionDao.queryProjectMoney(year, month);
        List<ProjectAssessment> list = busProjectDao.queryAllProjInfo();
        list.forEach(projectAssessment -> {
            projectAssessment.setEnergyCost(new BigDecimal(0));
            BigDecimal buildingArea = projectAssessment.getBuildingArea();
            String id = projectAssessment.getId();
            costList.forEach(moneyCost -> {
                if (StringUtils.equals(moneyCost.getProjectId(), id)) {
                    BigDecimal moneyWater = moneyCost.getMoneyWater();
                    BigDecimal moneyElec = moneyCost.getMoneyElec();
                    BigDecimal moneyGas = moneyCost.getMoneyGas();
                    if (buildingArea.compareTo(new BigDecimal(0)) != 0) {
                        BigDecimal divide = moneyWater.add(moneyElec).add(moneyGas).divide(buildingArea, 2, BigDecimal.ROUND_HALF_UP);
                        projectAssessment.setEnergyCost(divide);
                    }
                }
            });
        });
        return list;
    }


}
