package org.rcisoft.service.sysManagement.projMaintenance.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.dao.EnergyPriceDao;
import org.rcisoft.dao.EnergyStandardDao;
import org.rcisoft.dao.sysManagement.projMaintenance.BasicDataDao;
import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStandard;
import org.rcisoft.service.sysManagement.projMaintenance.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:27
 **/
@Service
public class BasicDataServiceImpl implements BasicDataService {

    @Autowired
    private BasicDataDao basicDataDao;
    @Autowired
    private EnergyStandardDao energyStandardDao;
    @Autowired
    private EnergyPriceDao energyPriceDao;

    /**
     *获取水电气24小时单价信息
     */
    @Override
    public int addPerHourPrice(List<EnergyPrice> list){
//        Map<String,List<String>> mapList = new HashMap<>();
//        List<Map<String,String>> listMap = new ArrayList<>();
//        String[] array = {"0","0","0","0","0","0","0","0","0","0","0","0",
//                "0","0","0","0","0","0","0","0","0","0","0","0"};
//        List<String> waterList = new ArrayList<>(Arrays.asList(array));
//        listMap = basicDataDao.queryWaterPerHourPrice(energyPrice);
//        for (Map<String, String> map : listMap){}
        int sum = 0;
        for(EnergyPrice energyPrice : list){
            energyPrice.setId(UuidUtil.create32());
            energyPriceDao.insert(energyPrice);
            sum++;
        }
        return sum;
    }

    /**
     * 新增能源标准
     */
    @Override
    public int addEnergyStandard(List<EnergyStandard> list){
        int sum = 0;
        for(EnergyStandard energyStandard : list){
            energyStandard.setId(UuidUtil.create32());
            energyStandardDao.insert(energyStandard);
            sum++;
        }
        return sum;
    }

}
