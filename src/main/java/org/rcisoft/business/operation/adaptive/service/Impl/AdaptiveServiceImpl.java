package org.rcisoft.business.operation.adaptive.service.Impl;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
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

    @Override
    public BuildingResult queryBuilding(AdaptiveParam adaptiveParam) {
        FelEngine felEngineImpl = new FelEngineImpl();
        Calendar calendar = Calendar.getInstance();
        BuildingResult result = new BuildingResult();
        BigDecimal[] buildingArray = result.getBuildingArray();
        BigDecimal[] tempArray = result.getTempArray();
        // 查询室外温度
        String time = adaptiveParam.getTime();
        String code = busProjectDao.queryCityCode(adaptiveParam.getProjectId());
        List<BusTemperature> temperatureList = busTemperatureDao.queryTemp(time, code);
        temperatureList.forEach(busTemperature -> {
            calendar.setTime(busTemperature.getCreateTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            tempArray[hour] = busTemperature.getTemperature();
        });
        // 查询建筑负荷
        // 从data表中查询数据
        List<SysData> dataList = sysDataDao.querySysDataFormat(time, adaptiveParam.getProjectId());
        String codingFirst = adaptiveParam.getCodingFirst();
        String codingSecond = adaptiveParam.getCodingSecond();
        // 获取公式
        String formula = adaptiveParam.getFormula();
        // 获取变量列表
        List<VariableParam> variableList = adaptiveParam.getVariableList();
        // 排序变量list
        if (variableList != null) {
            Collections.sort(variableList, (o1, o2) -> {
                String o1V = o1.getVariable();
                String o2V = o2.getVariable();
                if (o1V.length() > o2V.length()) {
                    return -1;
                } else {
                    return 1;
                }
            });
        }
        // 处理数据
        dataList.forEach(sysData -> {
            String value = null;
            calendar.setTime(sysData.getCreateTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String json = sysData.getJson();
            if (adaptiveParam.getType() == 1) {
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
    public void downloadBuilding(HttpServletRequest request, HttpServletResponse response, AdaptiveParam adaptiveParam) {
        BuildingResult result = this.queryBuilding(adaptiveParam);
        // 创建excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        // 第一行
        String time = adaptiveParam.getTime();
        Row row1 = sheet.createRow(0);
        row1.createCell(0, CellType.STRING).setCellValue("日期：");
        row1.createCell(1, CellType.STRING).setCellValue(time);
        row1.createCell(2, CellType.STRING).setCellValue("来源：");
        if (adaptiveParam.getType() == 1) {
            // 来源传感器
            row1.createCell(3, CellType.STRING).setCellValue(adaptiveParam.getCodingFirst() + "-" + adaptiveParam.getCodingSecond());
        } else {
            // 来源公式
            row1.createCell(3, CellType.STRING).setCellValue(adaptiveParam.getFormula());
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
        ExcelUtil.downloadExcel(request, response, "建筑负荷-" + time, workbook);
    }

    @Override
    public List<BigDecimal[]> queryClimate(ClimateParam climateParam) {
        Calendar calendar = Calendar.getInstance();
        // 至少有一条数据，第一条是最优供水温度，其他为自定义选择
        List<BigDecimal[]> result = new ArrayList<>();
        // 计算最优供水温度
        String projectId = climateParam.getProjectId();
        String code = busProjectDao.queryCityCode(projectId);
        String time = climateParam.getTime();
        // 查询当天的实际温度
        List<BusTemperature> temperatureList = busTemperatureDao.queryTempDate(time, code);
        /** 最优温度 */
        BigDecimal[] tempResult = new BigDecimal[24];
        temperatureList.forEach(busTemperature -> {
            calendar.setTime(busTemperature.getCreateTime());
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            //得到时间
            BigDecimal temperature = busTemperature.getTemperature();
            //计算公式
            BigDecimal optimum = new BigDecimal(0);
            if (temperature.compareTo(new BigDecimal(35)) > 0) {//大于35度
                optimum = new BigDecimal(0.4).multiply(temperature).subtract(new BigDecimal(6));
            } else if (temperature.compareTo(new BigDecimal(26)) > 0 && temperature.compareTo(new BigDecimal(35)) < 1) {//大于26度，小于等于35度
                optimum = new BigDecimal(94).subtract(new BigDecimal(2).multiply(temperature)).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP);
            } else if (temperature.compareTo(new BigDecimal(0)) < 1) {//小于0度
                optimum = new BigDecimal(0).subtract(temperature).add(new BigDecimal(40));
            } else {//大于0度，小于等于26度
                optimum = temperature;
            }
            //放入结果集
            tempResult[hour] = optimum;
        });
        // 放入最优供水温度
        result.add(tempResult);
        /** 自定义选择值 */
        List<CodingParam> codingList = climateParam.getCodingList();
        if (codingList.size() > 0) {
            // 增加返回值的内容
            for (int i = 0; i < codingList.size(); i++) {
                result.add(new BigDecimal[24]);
            }
            // 查询当天sys_data数据
            List<SysData> dataList = sysDataDao.querySysDataFormat(time, projectId);
            // 循环sys_data数据
            dataList.forEach(sysData -> {
                String json = sysData.getJson();
                Date date = sysData.getCreateTime();
                calendar.setTime(date);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                for (int i = 0; i < codingList.size(); i++) {
                    String codingFirst = codingList.get(i).getCodingFirst();
                    String codingSecond = codingList.get(i).getCodingSecond();
                    String value = FormulaUtil.getValueFromJson(codingFirst, codingSecond, json);
                    result.get(i + 1)[hour] = new BigDecimal(value);
                }
            });
        }
        return result;
    }

    @Override
    public void downloadClimate(HttpServletRequest request, HttpServletResponse response, ClimateParam climateParam) {
        List<BigDecimal[]> climateData = this.queryClimate(climateParam);
        List<CodingParam> titleList = climateParam.getCodingList();
        String time = climateParam.getTime();
        // 创建excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        // 标题行
        Row rowTitle = sheet.createRow(0);
        rowTitle.createCell(0, CellType.STRING).setCellValue("小时");
        for (int i = 0; i < titleList.size(); i++) {
            rowTitle.createCell(i + 1, CellType.STRING).setCellValue(titleList.get(i).getTitle());
        }
        // 数据行
        for (int i = 0; i < 24; i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0, CellType.STRING).setCellValue(i);
            for (int j = 0; j < climateData.size(); j++) {
                BigDecimal value = climateData.get(j)[i];
                row.createCell(j + 1, CellType.STRING).setCellValue(value == null ? "" : value.toString());
            }
        }
        ExcelUtil.downloadExcel(request, response, "气候自适应-" + time, workbook);
    }

}
