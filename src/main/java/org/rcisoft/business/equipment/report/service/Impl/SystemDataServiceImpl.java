package org.rcisoft.business.equipment.report.service.Impl;

import org.rcisoft.business.equipment.report.dao.SystemDataDao;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.rcisoft.dao.BusParamSecondDao;
import org.rcisoft.entity.BusParamSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/
@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataDao systemDataDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;

    /**
     * 根据参数来源查询二级参数
     */
    @Override
    public List<BusParamSecond> queryParamSecondBySource(String proId,String sourceId){
        return busParamSecondDao.queryParamSecondByProId(proId,sourceId);
    }
}
