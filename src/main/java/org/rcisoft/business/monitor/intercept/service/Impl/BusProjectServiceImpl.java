package org.rcisoft.business.monitor.intercept.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.rcisoft.base.redis.RedisService;
import org.rcisoft.business.monitor.intercept.dao.BusProjectParamDao;
import org.rcisoft.business.monitor.intercept.dao.DeviceDetailDao;
import org.rcisoft.business.monitor.intercept.dao.DeviceParamDao;
import org.rcisoft.business.monitor.intercept.entity.*;
import org.rcisoft.business.monitor.intercept.service.BusProjectService;
import org.rcisoft.dao.BusProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    @Autowired
    private DeviceParamDao deviceParamDao;
    @Autowired
    private BusProjectParamDao busProjectParamDao;
    @Autowired
    private DeviceDetailDao deviceDetailDao;


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

    @Override
    public List<DeviceParam> queryDeviceParam(String deviceId) {
        return deviceParamDao.queryDeviceParam(deviceId);
    }

    @Override
    public List<String> queryDeviceTitle() {
        return deviceParamDao.queryDeviceTitle();
    }

    @Override
    public List<DeviceInfo> queryDeviceInfo(String typeFirstId) {
        List<String> list_mal = deviceParamDao.queryMalfunction();
        List<DeviceInfo> list = new ArrayList<>();
        list = deviceParamDao.queryDeviceInfo(typeFirstId);
        for (DeviceInfo li : list){
            for(String str : list_mal){
                if(li.getId().equals(str)){
                    li.setStatus(1);
                }
            }
            long time = li.getRuntime();
            long second = time / 1000;
            long minute = second / 60;
            second = second % 1000;
            long hour = minute / 60;
            minute = minute % 60;
            String runtime = hour + ":" + minute + ":" + second;
            li.setTime(runtime);
        }
        return list;
    }

    @Override
    public List<String> queryModelName() {
        return busProjectParamDao.queryModelName();
    }

    @Override
    public Map<String,Object> EnergyEchart(String titleId) {
        Map<String,Object> map = new HashMap<>();
        List<TimeJson> list_data = deviceParamDao.queryData();
        List<EnergyEcharts> list = deviceParamDao.queryEnergyEchart(titleId);
        System.out.println(list_data);
        System.out.println(list);
        for (int i = 0; i < list.size(); i++){
            int resultData[] = new int[24];
            for(int j = 0; j < list_data.size(); j++){
                JSONObject jsonObject = JSON.parseObject(list_data.get(j).getJson());
                JSONObject jsonData = jsonObject.getJSONObject(list.get(i).getCodingFirst());
                JSONObject jData = jsonData.getJSONObject("REG_VAL");
                SimpleDateFormat formatter = new SimpleDateFormat( "HH");
                int time = Integer.valueOf(formatter.format(list_data.get(j).getCreateTime()));
                resultData[time] = jData.getInteger(list.get(i).getCodingSecond());
                map.put(list.get(i).getNameSecond(),resultData);
            }
        }
        return map;
    }

    @Override
    public List<EnergyParam> queryEnergyParam(String deviceId) {
        return busProjectParamDao.queryEnergyParam(deviceId);
    }

    @Override
    public List<DeviceFixValue> queryDeviceFixValue(String deviceId) {
        return busProjectParamDao.queryDeviceFixParam(deviceId);
    }

    @Override
    public List<DeviceDetail> queryDeviceDetail(String deviceId) {
        return deviceDetailDao.queryDeviceDetail(deviceId);
    }

    @Override
    public List<String> queryJsonByProId(String ProId) {
        List<String> jsonList = new ArrayList<>();
        String phoneList = redisService.get(ProId);
        String phone[] = phoneList.split(",");
        System.out.println(phone);
        for (int i = 0;i < phone.length; i++){
            jsonList.add(redisService.get(phone[i]));
        }

        System.out.println(jsonList);
        return jsonList;
    }
}
