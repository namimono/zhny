package org.rcisoft.business.monitor.intercept.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.rcisoft.business.monitor.intercept.dao.IndoorDao;
import org.rcisoft.business.monitor.intercept.entity.BusParamOutsideAndInside;
import org.rcisoft.business.monitor.intercept.entity.BusParamType;
import org.rcisoft.business.monitor.intercept.entity.OutsideAndInsideTemp;
import org.rcisoft.business.monitor.intercept.service.IndoorService;
import org.rcisoft.business.whole.head.service.SysCityService;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<BusIndoor> queryFloor(String projectId) {
        return indoorDao.queryFloor(projectId);
    }

    @Override
    public List<BusIndoor> queryDoor(String projectId, int floor) {
        return indoorDao.queryDoor(projectId,floor);
    }

    @Override
    public OutsideAndInsideTemp queryIndoorParam(String indoorId,String proId,String coding) {
        BigDecimal sum,sumNum;
        OutsideAndInsideTemp outsideAndInsideTemp = new OutsideAndInsideTemp();
        //查出最近的项目网关数据
        JSONObject jsonObject = JSONObject.parseObject(indoorDao.queryJson(proId));
        List<BusParamType> busIndoorParam = indoorDao.queryBusIndoorParamInside(indoorId,proId);
        List<BusParamType> busOutdoor = indoorDao.queryBusIndoorParamOutside(proId);
        for (BusParamType bp:busIndoorParam){
            String codingFirst = bp.getCodingFirst();
            String codingSecond = bp.getCodingSecond();
            JSONObject json = (JSONObject) jsonObject.get(codingFirst);
            if(json==null){
                return null;
            }
            JSONObject jsonSecond = (JSONObject)json.get("REG_VAL");
            if(jsonSecond==null){
                return null;
            }
            sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
            switch (bp.getType()){
                case 1 :
                    outsideAndInsideTemp.setInsideTemp(sum);break;
                case 2 :
                    outsideAndInsideTemp.setInsideHumidity(sum);break;
                case 3 :
                    outsideAndInsideTemp.setInsidePm(sum);break;
                case 4 :
                    outsideAndInsideTemp.setInsideCo2(sum);break;
                default:
                    break;
            }
        }
        for (BusParamType bo:busOutdoor){
            String codingFirst = bo.getCodingFirst();
            String codingSecond = bo.getCodingSecond();
            JSONObject json = (JSONObject) jsonObject.get(codingFirst);
            if(json==null){
                return null;
            }
            JSONObject jsonSecond = (JSONObject)json.get("REG_VAL");
            if(jsonSecond==null){
                return null;
            }
            sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
            switch (bo.getType()){
                case 3 :
                    outsideAndInsideTemp.setOutsidePm(sum);break;
                case 4 :
                    outsideAndInsideTemp.setOutsideCo2(sum);break;
                default:
                    break;
            }
        }
        sumNum = indoorDao.queryTemperature(coding);
        outsideAndInsideTemp.setOutsideTemp(sumNum);
        sumNum = indoorDao.queryHumidity(coding);
        outsideAndInsideTemp.setOutsideHumidity(sumNum);

        return outsideAndInsideTemp;
    }

    @Override
    public Map<String,Object> queryJsonIndoor(String proId, int type, String coding,String indoor) {
        BigDecimal sum;
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat f = new SimpleDateFormat("HH");
        BigDecimal inside[] = new BigDecimal[24];
        BigDecimal outside[] = new BigDecimal[24];
        List<SysData> json = indoorDao.queryJsonIndoor(proId);

        if (type == 3 || type ==4){
            BusParamOutsideAndInside insideParam = indoorDao.queryParamHoursInside(type,indoor);
            BusParamOutsideAndInside outsideParam = indoorDao.queryParamHoursOutside(type,proId);
            if(insideParam != null && outsideParam != null){
                for (SysData js:json){
                    Date day = js.getCreateTime();
                    int hour = Integer.parseInt(f.format(day));
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = insideParam.getCodingFirst();
                    String codingSecond = insideParam.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject)jsonFirst.get("REG_VAL");
                    sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for(int i = 0; i < inside.length; i++){
                        inside[hour] = sum;
                    }
                }
                for (SysData js:json){
                    Date day = js.getCreateTime();
                    int hour = Integer.parseInt(f.format(day));
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = insideParam.getCodingFirst();
                    String codingSecond = insideParam.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject)jsonFirst.get("REG_VAL");
                    sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for(int i = 0; i < inside.length; i++){
                        outside[hour] = sum;
                    }
                }
            }
        }else{
            BusParamOutsideAndInside insideParam = indoorDao.queryParamHoursInside(type,indoor);
            if(insideParam != null ){
                for (SysData js:json){
                    Date day = js.getCreateTime();
                    int hour = Integer.parseInt(f.format(day));
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = insideParam.getCodingFirst();
                    String codingSecond = insideParam.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject)jsonFirst.get("REG_VAL");
                    sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for(int i = 0; i < inside.length; i++){
                        inside[hour] = sum;
                    }
                }
                if(type == 1 || type == 2 ){
                    if(type == 1){
                        List<BusTemperature> listTemp = indoorDao.queryTempHours(coding);
                        for (BusTemperature bt:listTemp){
                            Date day = bt.getCreateTime();
                            int hour = Integer.parseInt(f.format(day));
                            sum = bt.getTemperature();
                            outside[hour] = sum;
                        }
                    }else{
                        List<BusTemperature> listHumi = indoorDao.queryHumidityHours(coding);
                        for(BusTemperature bt:listHumi){
                            Date day = bt.getCreateTime();
                            int hour = Integer.parseInt(f.format(day));
                            sum = new BigDecimal(bt.getHumidity());
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


    @Override
    public Map<String, Object> MonthParamContrast(String proId, int type, String coding, int year, int month) {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat f = new SimpleDateFormat("dd");
        BigDecimal inside[] = new BigDecimal[31];
        BigDecimal outside[] = new BigDecimal[31];
        List<SysData> listJson = indoorDao.queryJsonByData(year, month);
        BigDecimal sum = new BigDecimal(0);
        BigDecimal average;
        int days = 0;
        if(type == 1 || type == 2 ){
            BusParamOutsideAndInside insideParam = indoorDao.queryParamHoursInside(type,proId);
            if(insideParam != null){
                for (SysData lj:listJson){
                    Date day = lj.getCreateTime();
                    int dayNum = Integer.parseInt(f.format(day));
                    if(dayNum == days){
                        JSONObject jsonObject = (JSONObject) JSONObject.parse(lj.getJson());
                        String codingFirst = insideParam.getCodingFirst();
                        String codingSecond = insideParam.getCodingSecond();
                        JSONObject jsonFist = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFist.get("REG_VAL");
                        sum = sum.add(new BigDecimal(jsonSecond.get(codingSecond).toString()));
                        average = sum.divide(new BigDecimal(24),2);
                        inside[days - 1] = average;
                    }else{
                        days = dayNum;
                        JSONObject jsonObject = (JSONObject) JSONObject.parse(lj.getJson());
                        String codingFirst = insideParam.getCodingFirst();
                        String codingSecond = insideParam.getCodingSecond();
                        JSONObject jsonFist = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFist.get("REG_VAL");
                        sum = sum.add(new BigDecimal(jsonSecond.get(codingSecond).toString()));
                        average = sum.divide(new BigDecimal(24),2);
                        inside[days - 1] = average;
                    }

                }
                if(type == 1){
                    List<BusTemperature> listTemp = indoorDao.queryTempDate(coding,year,month);
                    for (BusTemperature bt:listTemp){
                        Date day = bt.getCreateTime();
                        int dayNum = Integer.parseInt(f.format(day));
                        if(dayNum == days){
                            sum = bt.getTemperature().add(sum);
                            average = sum.divide(new BigDecimal(24),2);
                            outside[dayNum - 1] = average;
                        }else{
                            days = dayNum;
                            sum = bt.getTemperature().add(sum);
                            average = sum.divide(new BigDecimal(24),2);
                            outside[dayNum - 1] = average;
                        }

                    }
                }else{
                    List<BusTemperature> listHumi = indoorDao.queryHumidityDate(coding,year,month);
                    int sumHumi = 0;
                    for(BusTemperature bt:listHumi){
                        Date day = bt.getCreateTime();
                        int dayNum = Integer.parseInt(f.format(day));
                        if(dayNum == days){
                            sumHumi += bt.getHumidity();
                            average = sum.divide(new BigDecimal(30),2);
                            outside[dayNum] = average;
                        }else{
                            sumHumi += bt.getHumidity();
                            average = sum.divide(new BigDecimal(30),2);
                            outside[dayNum] = average;
                        }

                    }
                }
            }
        }else{
            BusParamOutsideAndInside insideParam = indoorDao.queryParamHoursInside(type,proId);
            BusParamOutsideAndInside outsideParam = indoorDao.queryParamHoursOutside(type, proId);
            if(insideParam != null && outsideParam != null){
                for (SysData lj:listJson){
                    Date day = lj.getCreateTime();
                    int dayNum = Integer.parseInt(f.format(day));
                    if(dayNum == days){
                        JSONObject jsonObject = (JSONObject) JSONObject.parse(lj.getJson());
                        String codingFirst = insideParam.getCodingFirst();
                        String codingSecond = insideParam.getCodingSecond();
                        JSONObject jsonFist = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFist.get("REG_VAL");
                        sum = sum.add(new BigDecimal(jsonSecond.get(codingSecond).toString()));
                        average = sum.divide(new BigDecimal(24),2);
                        inside[days - 1] = average;
                    }else{
                        days = dayNum;
                        JSONObject jsonObject = (JSONObject) JSONObject.parse(lj.getJson());
                        String codingFirst = insideParam.getCodingFirst();
                        String codingSecond = insideParam.getCodingSecond();
                        JSONObject jsonFist = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFist.get("REG_VAL");
                        sum = sum.add(new BigDecimal(jsonSecond.get(codingSecond).toString()));
                        average = sum.divide(new BigDecimal(24),2);
                        inside[days - 1] = average;
                    }
                }
                for (SysData lj:listJson){
                    Date day = lj.getCreateTime();
                    int dayNum = Integer.parseInt(f.format(day));
                    if(dayNum == days){
                        JSONObject jsonObject = (JSONObject) JSONObject.parse(lj.getJson());
                        String codingFirst = insideParam.getCodingFirst();
                        String codingSecond = insideParam.getCodingSecond();
                        JSONObject jsonFist = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFist.get("REG_VAL");
                        sum = sum.add(new BigDecimal(jsonSecond.get(codingSecond).toString()));
                        average = sum.divide(new BigDecimal(24),2);
                        outside[days - 1] = average;
                    }else{
                        days = dayNum;
                        JSONObject jsonObject = (JSONObject) JSONObject.parse(lj.getJson());
                        String codingFirst = insideParam.getCodingFirst();
                        String codingSecond = insideParam.getCodingSecond();
                        JSONObject jsonFist = (JSONObject) jsonObject.get(codingFirst);
                        JSONObject jsonSecond = (JSONObject)jsonFist.get("REG_VAL");
                        sum = sum.add(new BigDecimal(jsonSecond.get(codingSecond).toString()));
                        average = sum.divide(new BigDecimal(24),2);
                        outside[days - 1] = average;
                    }
                }


            }
        }
        map.put("inside",inside);
        map.put("outside",outside);
        return map;
    }
}
