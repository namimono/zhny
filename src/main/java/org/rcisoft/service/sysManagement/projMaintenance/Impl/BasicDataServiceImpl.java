package org.rcisoft.service.projMaintenance.Impl;

import org.rcisoft.dao.projMaintenance.BasicDataDao;
import org.rcisoft.service.projMaintenance.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:27
 **/
@Service
public class BasicDataServiceImpl implements BasicDataService {

    @Autowired
    private BasicDataDao basicDataDao;

    /**
     *获取水电气24小时单价信息
     */
    public List<Map<String,Object>> queryPerHourPrice(){
        List<Map<String,Object>> listMap = new ArrayList<>();
        return listMap;
    }

}
