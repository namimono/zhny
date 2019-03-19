package org.rcisoft.business.operation.adaptive.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.business.operation.adaptive.entity.AdaptiveParam;
import org.rcisoft.business.operation.adaptive.entity.BuildingAdaptation;
import org.rcisoft.business.operation.adaptive.entity.ClimateAdaptation;
import org.rcisoft.business.operation.adaptive.service.AdaptiveService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/18 10:50
 **/
@Service
public class AdaptiveServiceImpl implements AdaptiveService {

    /**
     * 计算建筑负荷最优供水温度
     */
//    @Override
//    public List<Object> buildingList(AdaptiveParam adaptiveParam) {
//        Calendar cal = Calendar.getInstance();
//        //查询建筑负荷：供水温度，回水温度，水流量
//        String[] code_array = new String[]{"", "", ""};
//        List<BusParamRefer> busParamRefer = busParamReferRepository.queryOtherParam(params);
//        busParamRefer.forEach(b -> {
//            if(StringUtils.equals(b.getOwnParam(), ProEnum.gswd.toString())) code_array[0] = b.getOtherParam();
//            if(StringUtils.equals(b.getOwnParam(), ProEnum.hswd.toString())) code_array[1] = b.getOtherParam();
//            if(StringUtils.equals(b.getOwnParam(), ProEnum.sll.toString())) code_array[2] = b.getOtherParam();
//        });
//        //从sensor表查询对应日期的所有记录
//        List<TotalSensor> totalSensorList = totalSensorRepository.queryTotalSensorList(params);
//        List<Object> buildingList = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
//        totalSensorList.forEach(totalSensor -> {
//            cal.setTime(totalSensor.getTm());
//            //整点数据
//            if (cal.get(Calendar.MINUTE) == 0) {
//                //得到小时
//                int hour = cal.get(Calendar.HOUR_OF_DAY);
//                //得到json对象
//                JSONObject json = JSONObject.parseObject(totalSensor.getSensorJson());
//                //供水温度
//                BigDecimal gswd = (BigDecimal) json.get(code_array[0]);
//                //回水温度
//                BigDecimal hswd = (BigDecimal) json.get(code_array[1]);
//                //水流量
//                BigDecimal sll = (BigDecimal) json.get(code_array[2]);
//                //如果其中任何一个数据为空，继续下一条
//                if (gswd == null || hswd == null || sll == null) return;
//                //公式计算
//                Float building = Math.abs(gswd.subtract(hswd).multiply(sll).multiply(new BigDecimal(4.12)).divide(new BigDecimal(3.6), 2, BigDecimal.ROUND_HALF_UP).floatValue());
//                buildingList.set(hour, building);
//            }
//        });
//        return buildingList;
//    }

    /**
     * 气候自适应模块
     */
//    @Override
//    public ClimateAdaptation climateAdaptation(String proId, String year, String month, String day) {
//        Calendar cal = Calendar.getInstance();
//        //查询实际供水温度
//        Params params = new Params(proId, year + "-" + month + "-" + day, null);
//        //1.从sensor表查询对应日期的所有记录
//        List<TotalSensor> totalSensorList = totalSensorRepository.queryTotalSensorList(params);
//        //2.从bus_param_refer表查询“实际供水温度”对应的code
//        String[] code_array = new String[]{""};
//        String gswd_code = ProEnum.gswd.toString();
//        List<BusParamRefer> busParamReferList = busParamReferRepository.queryOtherParam(params);
//        Iterator<BusParamRefer> it = busParamReferList.iterator();
//        while (it.hasNext()) {
//            BusParamRefer busParamRefer = it.next();
//            if(StringUtils.equals(gswd_code, busParamRefer.getOwnParam())){
//                code_array[0] = busParamRefer.getOtherParam();
//                break;
//            }
//        }
//        //3.取出数据中的整点记录，根据温度的code将数据取出，放入结果中
//        List<Object> realList = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
//        //循环
//        totalSensorList.forEach(totalSensor -> {
//            cal.setTime(totalSensor.getTm());
//            //整点数据
//            if (cal.get(Calendar.MINUTE) == 0) {
//                //得到小时
//                int hour = cal.get(Calendar.HOUR_OF_DAY);
//                //得到json对象
//                JSONObject json = JSONObject.parseObject(totalSensor.getSensorJson());
//                //得到供水温度
//                Object gswd = json.get(code_array[0]);
//                realList.set(hour, gswd);
//            }
//        });
//        //查询最优供水温度
//        //1.根据proId查询项目的城市code
//        String code = this.getCode(proId);
//        //2.根据城市code查询对应日期的室外温度
//        params.setCode(code);
//        List<BusTemperature> busTemperaturesList = busTemperatureRepository.queryBusTemperatureList(params);
//        //3.根据公式算出最优供水温度，不需要计算的数值取原来的数据
//        List<Object> optimumList = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
//        busTemperaturesList.forEach(busTemperature -> {
//            cal.setTime(busTemperature.getTm());
//            int hour = cal.get(Calendar.HOUR_OF_DAY);
//            //得到时间
//            BigDecimal temperature = busTemperature.getTemperature();
//            //计算公式
//            BigDecimal optimum = new BigDecimal(0);
//            if (temperature.compareTo(new BigDecimal(35)) > 0) {//大于35度
//                optimum = new BigDecimal(0.4).multiply(temperature).subtract(new BigDecimal(6));
//            } else if (temperature.compareTo(new BigDecimal(26)) > 0 && temperature.compareTo(new BigDecimal(35)) < 1) {//大于26度，小于等于35度
//                optimum = new BigDecimal(94).subtract(new BigDecimal(2).multiply(temperature)).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP);
//            } else if (temperature.compareTo(new BigDecimal(0)) < 1) {//小于0度
//                optimum = new BigDecimal(0).subtract(temperature).add(new BigDecimal(40));
//            } else {//大于0度，小于等于26度
//                optimum = temperature;
//            }
//            //放入结果集
//            optimumList.set(hour, optimum);
//        });
//        //最后返回结果
//        return new ClimateAdaptation(realList, optimumList);
//    }

    /**
     * 建筑负荷自适应模块
     */
//    @Override
//    public BuildingAdaptation buildingAdaptation(String proId, String year, String month, String day) {
//        Calendar cal = Calendar.getInstance();
//        Params params = new Params(proId, year + "-" + month + "-" + day, this.getCode(proId));
//        //查询室外温度
//        List<BusTemperature> busTemperaturesList = busTemperatureRepository.queryBusTemperatureList(params);
//        List<Object> temperatureList = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
//        busTemperaturesList.forEach(busTemperature -> {
//            cal.setTime(busTemperature.getTm());
//            int hour = cal.get(Calendar.HOUR_OF_DAY);
//            BigDecimal temperature = busTemperature.getTemperature();
//            temperatureList.set(hour, temperature);
//        });
//        List<Object> buildingList = this.buildingList(params);
//        return new BuildingAdaptation(buildingList, temperatureList);
//    }
}
