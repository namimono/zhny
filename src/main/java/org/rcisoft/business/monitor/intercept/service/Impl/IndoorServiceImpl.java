package org.rcisoft.business.monitor.intercept.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.rcisoft.base.util.FormulaUtil;
import org.rcisoft.business.monitor.intercept.dao.IndoorDao;
import org.rcisoft.business.monitor.intercept.entity.BusParamOutsideAndInside;
import org.rcisoft.business.monitor.intercept.entity.BusParamType;
import org.rcisoft.business.monitor.intercept.entity.OutsideAndInsideTemp;
import org.rcisoft.business.monitor.intercept.service.IndoorService;
import org.rcisoft.dao.BusTemperatureDao;
import org.rcisoft.dao.SysDataDao;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private SysDataDao sysDataDao;
    @Autowired
    private BusTemperatureDao busTemperatureDao;

    @Override
    public List<BusIndoor> queryFloor(String projectId) {
        return indoorDao.queryFloor(projectId);
    }

    @Override
    public List<BusIndoor> queryDoor(String projectId, int floor) {
        return indoorDao.queryDoor(projectId, floor);
    }

    @Override
    public OutsideAndInsideTemp queryIndoorParam(String indoorId, String proId, String coding) {
        BigDecimal sum, sumNum;
        OutsideAndInsideTemp outsideAndInsideTemp = new OutsideAndInsideTemp();
        //查出最近的项目网关数据
        JSONObject jsonObject = JSONObject.parseObject(indoorDao.queryJson(proId));
        List<BusParamType> busIndoorParam = indoorDao.queryBusIndoorParamInside(indoorId, proId);
        List<BusParamType> busOutdoor = indoorDao.queryBusIndoorParamOutside(proId);
        for (BusParamType bp : busIndoorParam) {
            String codingFirst = bp.getCodingFirst();
            String codingSecond = bp.getCodingSecond();
            JSONObject json = (JSONObject) jsonObject.get(codingFirst);
            if (json == null) {
                return null;
            }
            JSONObject jsonSecond = (JSONObject) json.get("REG_VAL");
            if (jsonSecond == null) {
                return null;
            }
            sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
            switch (bp.getType()) {
                case 1:
                    outsideAndInsideTemp.setInsideTemp(sum);
                    break;
                case 2:
                    outsideAndInsideTemp.setInsideHumidity(sum);
                    break;
                case 3:
                    outsideAndInsideTemp.setInsidePm(sum);
                    break;
                case 4:
                    outsideAndInsideTemp.setInsideCo2(sum);
                    break;
                default:
                    break;
            }
        }
        for (BusParamType bo : busOutdoor) {
            String codingFirst = bo.getCodingFirst();
            String codingSecond = bo.getCodingSecond();
            JSONObject json = (JSONObject) jsonObject.get(codingFirst);
            if (json == null) {
                return null;
            }
            JSONObject jsonSecond = (JSONObject) json.get("REG_VAL");
            if (jsonSecond == null) {
                return null;
            }
            sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
            switch (bo.getType()) {
                case 3:
                    outsideAndInsideTemp.setOutsidePm(sum);
                    break;
                case 4:
                    outsideAndInsideTemp.setOutsideCo2(sum);
                    break;
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
    public Map<String, Object> queryJsonIndoor(String proId, int type, String coding, String indoor) {
        BigDecimal sum;
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat f = new SimpleDateFormat("HH");
        BigDecimal inside[] = new BigDecimal[24];
        BigDecimal outside[] = new BigDecimal[24];
        List<SysData> json = indoorDao.queryJsonIndoor(proId);

        if (type == 3 || type == 4) {
            BusParamOutsideAndInside insideParam = indoorDao.queryParamHoursInside(type, indoor);
            BusParamOutsideAndInside outsideParam = indoorDao.queryParamHoursOutside(type, proId);
            if (insideParam != null && outsideParam != null) {
                for (SysData js : json) {
                    Date day = js.getCreateTime();
                    int hour = Integer.parseInt(f.format(day));
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = insideParam.getCodingFirst();
                    String codingSecond = insideParam.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject) jsonFirst.get("REG_VAL");
                    sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for (int i = 0; i < inside.length; i++) {
                        inside[hour] = sum;
                    }
                }
                for (SysData js : json) {
                    Date day = js.getCreateTime();
                    int hour = Integer.parseInt(f.format(day));
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = outsideParam.getCodingFirst();
                    String codingSecond = outsideParam.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject) jsonFirst.get("REG_VAL");
                    sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for (int i = 0; i < inside.length; i++) {
                        outside[hour] = sum;
                    }
                }
            }
        } else {
            BusParamOutsideAndInside insideParam = indoorDao.queryParamHoursInside(type, indoor);
            if (insideParam != null) {
                for (SysData js : json) {
                    Date day = js.getCreateTime();
                    int hour = Integer.parseInt(f.format(day));
                    JSONObject jsonObject = JSONObject.parseObject(js.getJson());
                    String codingFirst = insideParam.getCodingFirst();
                    String codingSecond = insideParam.getCodingSecond();
                    JSONObject jsonFirst = (JSONObject) jsonObject.get(codingFirst);
                    JSONObject jsonSecond = (JSONObject) jsonFirst.get("REG_VAL");
                    sum = new BigDecimal(jsonSecond.get(codingSecond).toString());
                    for (int i = 0; i < inside.length; i++) {
                        inside[hour] = sum;
                    }
                }
                if (type == 1 || type == 2) {
                    if (type == 1) {
                        List<BusTemperature> listTemp = indoorDao.queryTempHours(coding);
                        for (BusTemperature bt : listTemp) {
                            Date day = bt.getCreateTime();
                            int hour = Integer.parseInt(f.format(day));
                            sum = bt.getTemperature();
                            outside[hour] = sum;
                        }
                    } else {
                        List<BusTemperature> listHumi = indoorDao.queryHumidityHours(coding);
                        for (BusTemperature bt : listHumi) {
                            Date day = bt.getCreateTime();
                            int hour = Integer.parseInt(f.format(day));
                            sum = new BigDecimal(bt.getHumidity());
                            outside[hour] = sum;
                        }
                    }
                }
            }
        }
        map.put("inside", inside);
        map.put("outside", outside);
        return map;
    }


    @Override
    public Map<String, Object> MonthParamContrast(String proId, int type, String coding, int year, int month, String indoorId) {
        //获得指定年月的天数
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        //室内环境参数
        BigDecimal[] insideArray = new BigDecimal[day];
        //室外环境参数
        BigDecimal[] outsideArray = new BigDecimal[day];

        //查出指定项目，指定房间的四种室内环境参数配置信息
        List<BusParamType> busIndoorParamList = indoorDao.queryBusIndoorParamInside(indoorId, proId);
        //查出指定月份的项目的网关数据
        List<SysData> sysDataList = sysDataDao.listSysDataByProIdAndMonth(proId, cal.getTime());
        //把每个月的项目数据，按每天分组
        Map<Integer, List<SysData>> groupSysDataByDay = groupSysDataByDay(sysDataList);


        switch (type) {
            //温度
            case 1:
                //室内温度
                setEnvironment(insideArray, groupSysDataByDay, type, busIndoorParamList);
                //室外温度
                setOutsideEnvironment(coding, cal, outsideArray, type);
                break;
            //湿度
            case 2:
                //室内湿度
                setEnvironment(insideArray, groupSysDataByDay, type, busIndoorParamList);
                //室外湿度
                setOutsideEnvironment(coding, cal, outsideArray, type);
                break;
            //PM2.5
            case 3:
                //室内PM2.5
                setEnvironment(insideArray, groupSysDataByDay, type, busIndoorParamList);
                //室外PM2.5
                List<BusParamType> busOutsideParamListPM = indoorDao.queryBusIndoorParamOutside(proId);
                setEnvironment(outsideArray, groupSysDataByDay, type, busOutsideParamListPM);
                break;
            //CO2
            case 4:
                //室内CO2
                setEnvironment(insideArray, groupSysDataByDay, type, busIndoorParamList);
                //室外CO2
                List<BusParamType> busOutsideParamListCO = indoorDao.queryBusIndoorParamOutside(proId);
                setEnvironment(outsideArray, groupSysDataByDay, type, busOutsideParamListCO);
                break;
            default:
                break;
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("inside", insideArray);
        map.put("outside", outsideArray);
        return map;
    }

    /**
     * 获得室外温度或湿度
     * @author GaoLiWei
     * @date 14:14 2019/6/12
     **/
    private void setOutsideEnvironment(String coding, Calendar cal, BigDecimal[] outsideArray, int type) {
        //获得这个城市这一个月的所有时刻的天气情况
        List<BusTemperature> busTemperatureList = busTemperatureDao.listBusTemperatureByMonth(cal.getTime(), coding);
        //将这个月的天气情况按照天分组
        Map<Integer, List<BusTemperature>> groupBusTemperatureByDay = groupBusTemperatureByDay(busTemperatureList);

        //对一天的数据进行遍历，求出这一天的室外天气
        for (Map.Entry<Integer, List<BusTemperature>> entry : groupBusTemperatureByDay.entrySet()) {
            //获得天
            Integer nowDay = entry.getKey();
            //获得这一天的所有天气情况
            List<BusTemperature> temperatureList = entry.getValue();
            //计算这一天的室外天气情况的和
            BigDecimal outside = new BigDecimal(0);
            for (BusTemperature bt : temperatureList) {
                switch (type) {
                    case 1:
                        //室外温度
                        outside = outside.add(bt.getTemperature());
                        break;
                    case 2:
                        //室外湿度
                        outside = outside.add(new BigDecimal(bt.getHumidity()));
                        break;
                    default:
                        break;
                }
            }
            //计算这一天室外温度平均值
            outside = outside.divide(new BigDecimal(temperatureList.size()), 2, BigDecimal.ROUND_HALF_UP);
            outsideArray[nowDay - 1] = outside;
        }
    }

    /**
     * 封装室内的环境参数
     *
     * @author GaoLiWei
     * @date 13:52 2019/6/12
     **/
    private void setEnvironment(BigDecimal[] array, Map<Integer, List<SysData>> groupSysDataByDay, int type, List<BusParamType> paramList) {
        //环境参数配置
        BusParamType busParamType = getBusParamTypeByType(type, paramList);
        if (null != busParamType){
            //对这一天的数据进行遍历，计算出这天环境参数
            for (Map.Entry<Integer, List<SysData>> entry : groupSysDataByDay.entrySet()) {
                //哪一天
                int nowDay = entry.getKey();
                //当天的数据
                List<SysData> nowSysDataList = entry.getValue();

                //当天的环境参数的和
                BigDecimal envValue = new BigDecimal(0);
                for (SysData nowDaySysData : nowSysDataList) {
                    //获得这天中这个时间段的环境参数的和
                    String value = FormulaUtil.getValueFromJson(busParamType.getCodingFirst(), busParamType.getCodingSecond(), nowDaySysData.getJson());
                    String nowDayValue = null == value ? "0" : value;
                    envValue = envValue.add(new BigDecimal(nowDayValue));
                }
                //获得今天的平均值
                envValue = envValue.divide(new BigDecimal(nowSysDataList.size()), 2, BigDecimal.ROUND_HALF_UP);
                array[nowDay - 1] = envValue;
            }
        }

    }

    private BusParamType getBusParamTypeByType(int type, List<BusParamType> busIndoorParamList) {
        BusParamType busParamType = null;
        for (BusParamType bpt : busIndoorParamList) {
            if (bpt.getType() == type) {
                busParamType = bpt;
                break;
            }
        }
        return busParamType;
    }


    /**
     * 把这个月的天气情况按照每一天分组
     *
     * @author GaoLiWei
     * @date 13:29 2019/6/12
     **/
    private Map<Integer, List<BusTemperature>> groupBusTemperatureByDay(List<BusTemperature> busTemperatureList) {

        Map<Integer, List<BusTemperature>> map = new HashMap<>();
        for (BusTemperature busTemperature : busTemperatureList) {
            //获得当前这条数据的天数
            int day = Integer.parseInt(new SimpleDateFormat("dd").format(busTemperature.getCreateTime()));
            groupDataByDay(map, day, busTemperature);
        }
        return map;
    }

    /**
     * 把每个月的项目网管数据，按每个月得天分组
     *
     * @author GaoLiWei
     * @date 11:24 2019/6/12
     **/
    private Map<Integer, List<SysData>> groupSysDataByDay(List<SysData> sysDataList) {

        Map<Integer, List<SysData>> map = new HashMap<>();
        for (SysData sysData : sysDataList) {
            //获得当前这条数据的天数
            int day = Integer.parseInt(new SimpleDateFormat("dd").format(sysData.getCreateTime()));
            groupDataByDay(map, day, sysData);
        }
        return map;
    }

    /**
     * 把数据按照天分类
     *
     * @author GaoLiWei
     * @date 13:31 2019/6/12
     **/
    private void groupDataByDay(Map map, int day, Object obj) {

        //获得当天在map中的数据
        List list = (List) map.get(day);
        //如果当天在map中的数据是null,则证明还没有添加过这天的数据
        if (null == list) {
            List returnList = new ArrayList<>();
            returnList.add(obj);
            //在map中为这一天新加数据
            map.put(day, returnList);
        } else {
            //如果map在这天中已经有了数据,则把原来的list中新增一个，在放到map里
            list.add(obj);
            map.put(day, list);
        }
    }

}
