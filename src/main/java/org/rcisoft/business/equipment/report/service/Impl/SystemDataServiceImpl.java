package org.rcisoft.business.equipment.report.service.Impl;

import org.rcisoft.business.equipment.report.dao.SystemDataDao;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/
@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataDao systemDataDao;
}
