package org.rcisoft.business.operation.adaptive.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.util.ExcelUtil;
import org.rcisoft.base.util.FormulaUtil;
import org.rcisoft.business.operation.adaptive.entity.*;
import org.rcisoft.business.operation.adaptive.service.AdaptiveService;
import org.rcisoft.dao.BusProjectDao;
import org.rcisoft.dao.BusTemperatureDao;
import org.rcisoft.dao.SysDataDao;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 土豆儿
 * @date 2019/3/18 10:50
 **/
@Service
public class AdaptiveServiceImpl implements AdaptiveService {

    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private SysDataDao sysDataDao;
    @Autowired
    private BusTemperatureDao busTemperatureDao;

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
    @Override
    public ClimateAdaptation climateAdaptation(String projectId,String beginTime,String endTime) {
        Calendar cal = Calendar.getInstance();
        //查询实际供水温度
        List<SysData> sysDataList = sysDataDao.queryDataByProIdAndTime(projectId,beginTime,endTime);
        //2.从bus_param_refer表查询“实际供水温度”对应的code
//        List<BusParamRefer> busParamReferList = busParamReferRepository.queryOtherParam(params);
//        Iterator<BusParamRefer> it = busParamReferList.iterator();
//        while (it.hasNext()) {
//            BusParamRefer busParamRefer = it.next();
//            if(StringUtils.equals(gswd_code, busParamRefer.getOwnParam())){
//                code_array[0] = busParamRefer.getOtherParam();
//                break;
//            }
//        }
        //3.取出数据中的整点记录，根据温度的code将数据取出，放入结果中
        List<Object> realList = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        //循环
        sysDataList.forEach(sysData -> {
            cal.setTime(sysData.getCreateTime());
            //整点数据
            if (cal.get(Calendar.MINUTE) == 0) {
                //得到小时
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                //得到json对象
                JSONObject json = JSONObject.parseObject(sysData.getJson());
                //得到供水温度
                Object temperature = json.get("aftestwtw");
                realList.set(hour, temperature);
            }
        });
        //查询最优供水温度
        //1.根据proId查询项目的城市code
        String code = busProjectDao.queryCityCode(projectId);
        //2.根据城市code查询对应日期的室外温度
        List<BusTemperature> busTemperaturesList = busTemperatureDao.queryTemperature(beginTime,endTime,code);
        //3.根据公式算出最优供水温度，不需要计算的数值取原来的数据
        List<Object> optimumList = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        busTemperaturesList.forEach(busTemperature -> {
            cal.setTime(busTemperature.getCreateTime());
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            //得到时间
            BigDecimal temperature = busTemperature.getTemperature();
            //计算公式
            BigDecimal optimum = new BigDecimal(0);
            if (temperature.compareTo(new BigDecimal(35)) > 0) {
                //大于35度
                optimum = new BigDecimal(0.4).multiply(temperature).subtract(new BigDecimal(6));
            } else if (temperature.compareTo(new BigDecimal(26)) > 0 && temperature.compareTo(new BigDecimal(35)) < 1) {
                //大于26度，小于等于35度
                optimum = new BigDecimal(94).subtract(new BigDecimal(2).multiply(temperature)).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP);
            } else if (temperature.compareTo(new BigDecimal(0)) < 1) {
                //小于0度
                optimum = new BigDecimal(0).subtract(temperature).add(new BigDecimal(40));
            } else {//大于0度，小于等于26度
                optimum = temperature;
            }
            //放入结果集
            optimumList.set(hour, optimum);
        });
        //最后返回结果
        return new ClimateAdaptation(realList, optimumList);
    }

    @Override
    public BuildingResult queryBuilding(BuildingParam buildingParam) {
        FelEngine felEngineImpl = new FelEngineImpl();
        Calendar calendar = Calendar.getInstance();
        BuildingResult result = new BuildingResult();
        BigDecimal[] buildingArray = result.getBuildingArray();
        BigDecimal[] tempArray = result.getTempArray();
        // 查询室外温度
        String time = buildingParam.getYear() + "-" + buildingParam.getMonth() + "-" + buildingParam.getDay();
        List<BusTemperature> temperatureList = busTemperatureDao.queryTemp(time, buildingParam.getCode());
        temperatureList.forEach(busTemperature -> {
            calendar.setTime(busTemperature.getCreateTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            tempArray[hour] = busTemperature.getTemperature();
        });
        // 查询建筑负荷
        // 从data表中查询数据
        List<SysData> dataList = sysDataDao.querySysData(time, buildingParam.getProjectId());
        String codingFirst = buildingParam.getCodingFirst();
        String codingSecond = buildingParam.getCodingSecond();
        // 获取公式
        String formula = buildingParam.getFormula();
        // 获取变量列表
        List<VariableParam> variableList = buildingParam.getVariableList();
        // 排序变量list
        Collections.sort(variableList, (o1, o2) -> {
            String o1V = o1.getVariable();
            String o2V = o2.getVariable();
            if (o1V.length() > o2V.length()) {
                return -1;
            } else {
                return 1;
            }
        });
        // 处理数据
        dataList.forEach(sysData -> {
            String value = null;
            calendar.setTime(sysData.getCreateTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String json = sysData.getJson();
            if (buildingParam.getType() == 1) {
                // 从sensor中取数据
                value = FormulaUtil.getValueFromJson(codingFirst, codingSecond, json);
            } else {
                // 从公式中取数据
                // 替换数据之后的公式计算式
                String felFormula = new String(formula);
                // 循环变量
                for (VariableParam variableParam : variableList) {
                    // 变量
                    String variable = variableParam.getVariable();
                    // 一级
                    String first = variableParam.getCodingFirst();
                    // 二级
                    String second = variableParam.getCodingSecond();
                    // 变量的值
                    String variableValue = FormulaUtil.getValueFromJson(first, second, json);
                    // 替换公式中的变量
                    if (variableValue != null) {
                        felFormula = felFormula.replaceAll(variable, variableValue);
                    }
                }
                // 计算结果
                value = FormulaUtil.calculate(felFormula, felEngineImpl);
            }
            // 放入结果集
            if (value != null) {
                buildingArray[hour] = new BigDecimal(value);
            }
        });
        return result;
    }

    @Override
    public void downloadBuilding(HttpServletRequest request, HttpServletResponse response, BuildingParam buildingParam) {
        BuildingResult result = this.queryBuilding(buildingParam);
        // 创建excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        // 第一行
        String time = buildingParam.getYear() + "-" + buildingParam.getMonth() + "-" + buildingParam.getDay();
        Row row1 = sheet.createRow(0);
        row1.createCell(0, CellType.STRING).setCellValue("日期：");
        row1.createCell(1, CellType.STRING).setCellValue(time);
        row1.createCell(2, CellType.STRING).setCellValue("来源：");
        if (buildingParam.getType() == 1) {
            // 来源传感器
            row1.createCell(3, CellType.STRING).setCellValue(buildingParam.getCodingFirst() + "-" + buildingParam.getCodingSecond());
        } else {
            // 来源公式
            row1.createCell(3, CellType.STRING).setCellValue(buildingParam.getFormula());
        }
        // 第二行
        Row row2 = sheet.createRow(1);
        row2.createCell(0, CellType.STRING).setCellValue("小时");
        row2.createCell(1, CellType.STRING).setCellValue("室外温度");
        row2.createCell(2, CellType.STRING).setCellValue("建筑负荷");
        // 循环数据
        BigDecimal[] tempArray = result.getTempArray();
        BigDecimal[] buildingArray = result.getBuildingArray();
        int count = tempArray.length;
        for (int i = 0; i < count; i++) {
            Row row = sheet.createRow(i + 2);
            row.createCell(0, CellType.STRING).setCellValue(i);
            row.createCell(1, CellType.STRING).setCellValue(tempArray[i] == null ? "" : String.valueOf(tempArray[i]));
            row.createCell(2, CellType.STRING).setCellValue(buildingArray[i] == null ? "" : String.valueOf(buildingArray[i]));
        }
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        try {
//            workbook.write(os);
//            FileUtils.writeByteArrayToFile(new File("C:\\Users\\JiChao\\Desktop\\建筑负荷.xlsx"), os.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ExcelUtil.downloadExcel(request, response, "建筑负荷-" + time, workbook);
    }

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
