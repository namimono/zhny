package org.rcisoft.business.management.distribution.service.Impl;

import org.apache.ibatis.annotations.Param;
import org.rcisoft.business.management.distribution.dao.energyDistributionDao;
import org.rcisoft.business.management.distribution.entity.EnergyDistribution;
import org.rcisoft.business.management.distribution.service.energyDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:25 2019/3/12
 */
@Service
public class energyDistributionServiceImpl implements energyDistributionService {
    @Autowired
    energyDistributionDao energyDistributionDao;

    /**
     * 能耗分布计算
     * @param timeYear
     * @param Month
     * @return
     */
    @Override
    public List<EnergyDistribution> queryEnergyDistributed(int timeYear,String Month) {
        EnergyDistribution energyDistribution = new EnergyDistribution();
        Calendar cal = Calendar.getInstance();
        //获取当前月份
        int timeMonth = cal .get(Calendar.MONTH) + 1;
        List<EnergyDistribution> list = energyDistributionDao.queryEnergyDistributed(timeYear,Month,timeMonth);
        for(EnergyDistribution ed:list) {
            //计算水气电所有费用总和
            BigDecimal sum = ed.getSumElec().add(ed.getSumGas()).add(ed.getSumWater());
            //计算能耗水平
            BigDecimal energyNum = sum.divide(ed.getBuildingArea(), 2, BigDecimal.ROUND_HALF_UP);
            ed.setEnergyNum(energyNum);
            try{
                //截取年份存入年份字段
                Calendar c = Calendar.getInstance();
                c.setTime(ed.getEquipmentAge());
                ed.setEquipmentYear(c.get(Calendar.YEAR));
                c.setTime(ed.getBuildingAge());
                ed.setBuildingYear(c.get(Calendar.YEAR));
            }catch(Exception e ){
                e.getStackTrace();
            }
        }
        System.out.println(list);
        return list;
    }
}
