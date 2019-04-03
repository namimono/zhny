package org.rcisoft.business.monitor.intercept.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.rcisoft.business.monitor.intercept.dao.IndoorDao;
import org.rcisoft.business.monitor.intercept.entity.OutsideAndInsideTemp;
import org.rcisoft.business.monitor.intercept.service.IndoorService;
import org.rcisoft.business.whole.head.service.SysCityService;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:47 2019/3/29
 */
@Service
public class IndoorServiceImpl implements IndoorService {
    @Autowired
    IndoorDao indoorDao;
    @Autowired
    SysCityService sysCityService;

    @Override
    public List<String> queryFloor() {
        return indoorDao.queryFloor();
    }

    @Override
    public List<BusIndoor> queryDoor(int floor) {
        return indoorDao.queryDoor(floor);
    }

    @Override
    public OutsideAndInsideTemp queryIndoorParam(String indoorId,String proId,String city) {
        JSONObject jsonObject = JSONObject.parseObject(indoorDao.queryJson(proId));
        List<BusIndoorParam> list = indoorDao.queryBusIndoorParam(indoorId,proId);
        OutsideAndInsideTemp outsideAndInsideTemp = new OutsideAndInsideTemp();
        for (BusIndoorParam bp:list){
            String codingFirst = bp.getCodingFirst();
            String codingSecond = bp.getCodingSecond();
            JSONObject json = (JSONObject) jsonObject.get(codingFirst);
            JSONObject jsonSecond = (JSONObject)json.get("REG_VAL");
            BigDecimal sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
            if (bp.getSide().equals(2)){
                switch (bp.getType()){
                    case 1 :
                        outsideAndInsideTemp.setInsideTemp(sum);break;
                    case 2 :
                        outsideAndInsideTemp.setInsideHumidity(sum);break;
                    case 3 :
                        outsideAndInsideTemp.setInsidePm(sum);break;
                    case 4 :
                        outsideAndInsideTemp.setOutsideCo2(sum);break;
                }
            }else{
                switch (bp.getType()){
                    case 1 :
                        BigDecimal tempNum = indoorDao.queryTemperature(city);
                        outsideAndInsideTemp.setInsideTemp(tempNum);break;
                    case 2 :
                        BigDecimal humidityNum = indoorDao.queryHumidity(city);
                        outsideAndInsideTemp.setInsideHumidity(humidityNum);break;
                    case 3 :
                        outsideAndInsideTemp.setInsidePm(sum);break;
                    case 4 :
                        outsideAndInsideTemp.setInsideCo2(sum);break;
                }
            }
        }
        return outsideAndInsideTemp;
    }

    @Override
    public Map<String,Object> queryJsonIndoor(String proId, int type, String coding,String indoor) {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat f = new SimpleDateFormat("HH");

        BigDecimal inside[] = new BigDecimal[24];
        BigDecimal outside[] = new BigDecimal[24];
        List<SysData> json = indoorDao.queryJsonIndoor(proId);
        List<BusIndoorParam> list = indoorDao.queryParamHours(type,indoor);
        for (BusIndoorParam li:list){
            for (SysData js:json){
                Date day = js.getCreateTime();
                int hour = Integer.parseInt(f.format(day));
                if(li.getSide() == 1){
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = li.getCodingFirst();
                    String codingSecond = li.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject)jsonFirst.get("REG_VAL");
                    BigDecimal sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for(int i = 0; i < inside.length; i++){
                        inside[hour] = sum;
                    }
                }else{
                    if(type == 1){
                        BigDecimal sum = indoorDao.queryTemperature(coding);
                        for(int i = 0; i < outside.length; i++){
                            outside[hour] = sum;
                        }
                    }else if(type == 2){
                        BigDecimal sum = indoorDao.queryHumidity(coding);
                        for(int i = 0; i < outside.length; i++){
                            outside[hour] = sum;
                        }
                    }else{
                        JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                        String codingFirst = li.getCodingFirst();
                        String codingSecond = li.getCodingSecond();
                        JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFirst.get("REG_VAL");
                        BigDecimal sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                        for(int i = 0; i < outside.length; i++){
                            outside[hour] = sum;
                        }
                    }
                }
            }
        }
        map.put("inside",inside);
        map.put("outside",outside);
        return map;
    }
}
