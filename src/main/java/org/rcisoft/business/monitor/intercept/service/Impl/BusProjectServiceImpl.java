package org.rcisoft.business.monitor.intercept.service.Impl;

import org.rcisoft.base.redis.RedisService;
import org.rcisoft.business.monitor.intercept.entity.BusProjectParam;
import org.rcisoft.business.monitor.intercept.service.BusProjectService;
import org.rcisoft.dao.BusProjectDao;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.SysCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:02 2019/3/18
 */
@Service
public class BusProjectServiceImpl implements BusProjectService {

    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private RedisService redisService;


    @Override
    public Map<String,Object> queryPhones(String id) {
        String Phones = busProjectDao.queryPhones(id);
        String phones[] = Phones.split(",");
        for(int i = 0;i < phones.length; i++){
            System.out.println(phones[i]);
        }
        Map<String,Object> map = new HashMap<>();
        for(int i = 0;i < phones.length; i++){
            map.put(phones[i],redisService.get(phones[i]));
            System.out.println(phones[i]);
        }
        System.out.println(map);
        return map;
    }

    @Override
    public List<Map<String, Object>> queryParam(String id) {
        List<Map<String,Object>> data = new ArrayList<>();
        List<BusProjectParam> BusProjectParamList = busProjectDao.queryParam(id);
        Map<String,List<BusProjectParam>> resultMap = new HashMap<>();
        for(BusProjectParam list:BusProjectParamList) {
        	if(resultMap.containsKey(list.getName())) {
        		resultMap.get(list.getName()).add(list);
        	}else {
        		List<BusProjectParam> paramList = new ArrayList<>();
        		paramList.add(list);
        		resultMap.put(list.getName(), paramList);
        	}
        }
        for(String key : resultMap.keySet()){
            Map<String,Object> firstMap = new HashMap<>();
            firstMap.put("name",resultMap.get(key).get(0).getName());
            List<Map<String,String>> secondListMap = new ArrayList<>();
            for (BusProjectParam busProjectParam:resultMap.get(key)){
                Map<String,String> secondMap = new HashMap<>();
                secondMap.put("coding",busProjectParam.getCoding());
                secondListMap.add(secondMap);
            }
            firstMap.put("child",secondListMap);
            data.add(firstMap);
        }
        return data;
    }
}
