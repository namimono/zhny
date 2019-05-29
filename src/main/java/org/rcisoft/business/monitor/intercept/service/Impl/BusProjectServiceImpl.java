package org.rcisoft.business.monitor.intercept.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
    private RedisService redisService;
    @Autowired
    private DeviceParamDao deviceParamDao;

    @Override
    public List<DeviceInfo> queryDeviceInfo(String typeFirstId, String projectId, String systemId) {
        //查出当天该项目有哪些设备有故障
        List<String> list_mal = deviceParamDao.queryMalfunction(projectId,systemId);
        //查出当前项目当前类型的设备信息
        List<DeviceInfo> list = deviceParamDao.queryDeviceInfo(typeFirstId,projectId,systemId);
        for (DeviceInfo li : list){
            for(String str : list_mal){
                //如果当前设备有故障，则设置状态位
                if(li.getId().equals(str)){
                    li.setStatus(1);
                }
            }
            long time = li.getRuntime();
            long hour = time / 60;
            long minute = time % 60;
            String runtime = hour + ":" + minute ;
            li.setTime(runtime);
        }
        return list;
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
    public List<ParamElec> queryDeviceElec(String projectId, String systemId) {
        return deviceParamDao.queryDeviceElec(projectId, systemId);
    }

    @Override
    public List<Params> queryParams(String deviceId, Integer count) {
        return deviceParamDao.queryParams(deviceId, count);
    }

    @Override
    public ParamsResult queryParamsAll(String deviceId) {
        ParamsResult result = new ParamsResult();
        List<ParamsResult.Elec> elecList = result.getElecList();
        List<Params> paramsList = deviceParamDao.queryParamsAll(deviceId);
        result.setParamList(paramsList);
        paramsList.forEach(params -> {
            if (params.getEnergyType() == 2) {
                ParamsResult.Elec elec = new ParamsResult().new Elec();
                elec.setElecFirstCode(params.getFirstCode());
                elec.setElecSecondCode(params.getSecondCode());
                elec.setElecType(params.getElecType());
                elecList.add(elec);
            }
        });
        return result;
    }

    @Override
    public JSONArray queryCacheData(String projectId) {
        JSONArray result = new JSONArray();
        String phones = redisService.get(projectId);
        if (StringUtils.isNotEmpty(phones)) {
            String[] phoneArray = phones.split(",");
            for (String phone : phoneArray) {
                String jsonStr = redisService.get(phone);
                if (StringUtils.isNotEmpty(jsonStr)) {
                    result.add(JSON.parseObject(jsonStr));
                }
            }
        }
        return result;
    }

}
