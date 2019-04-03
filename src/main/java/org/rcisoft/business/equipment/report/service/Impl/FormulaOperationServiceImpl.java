package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.rcisoft.base.util.FormulaUtil;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.equipment.report.entity.VariableAndParam;
import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.rcisoft.business.system.project.dao.FormulaDao;
import org.rcisoft.business.system.project.entity.FormulaVariableData;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:37
 **/
@Service
public class FormulaOperationServiceImpl implements FormulaOperationService {

    @Autowired
    private FormulaDao formulaDao;
    @Autowired
    private BusFormulaDao busFormulaDao;
    @Autowired
    private BusVariableDao busVariableDao;
    @Autowired
    private SysSourceDao sysSourceDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;
    @Autowired
    private SysDataDao sysDataDao;

    /**
     * 获取当前系统时间
     */
    private Date getNowTime(){
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = null;
        try {
            nowTime = fdate.parse(fdate.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowTime;
    }

    /**
     * 根据项目ID查询公式信息
     */
    @Override
    public List<BusFormula> queryFormula(String projectId){
        return busFormulaDao.queryFormula(projectId);
    }

    /**
     * 增加公式信息
     */
    @Override
    public int addFormula(BusFormula busFormula){
        busFormula.setId(UuidUtil.create32());
        busFormula.setCreateTime(this.getNowTime());
        return busFormulaDao.insertSelective(busFormula);
    }

    /**
     * 删除公式信息
     */
    @Override
    public int deleteFormula(BusFormula busFormula){
        return busFormulaDao.deleteByPrimaryKey(busFormula);
    }

    /**
     * 修改公式信息
     */
    @Override
    public int updateFormula(BusFormula busFormula){
        return busFormulaDao.updateByPrimaryKeySelective(busFormula);
    }

    /**
     * 根据公式ID和项目ID查询变量
     */
    @Override
    public List<VariableAndParam> queryVariable(String projectId, String formulaId){
        return busVariableDao.queryVariable(projectId,formulaId);
    }

    /**
     * 查询参数来源
     */
    @Override
    public List<SysSource> querySource(){
        return sysSourceDao.querySourceInfo();
    }

    /**
     * 增加变量信息
     */
    @Override
    public int addVariable(BusVariable busVariable){
        busVariable.setCreateTime(this.getNowTime());
        busVariable.setId(UuidUtil.create32());
        return busVariableDao.insertSelective(busVariable);
    }

    /**
     * 删除变量信息
     */
    @Override
    public int deleteVariable(BusVariable busVariable){
        return busVariableDao.deleteByPrimaryKey(busVariable);
    }

    /**
     * 修改变量信息
     */
    @Override
    public int updateVariable(BusVariable busVariable){
        return busVariableDao.updateByPrimaryKeySelective(busVariable);
    }

    /**
     * 根据项目ID和参数来源查询二级参数信息
     */
    @Override
    public List<BusParamSecond> queryParamSecondByProId(String projectId, String sourceId){
        return busParamSecondDao.queryParamSecondByProId(projectId,sourceId);
    }

    /**
     * 导出公式数据
     */
    @Override
    public void downloadFormulaData(HttpServletResponse response,String projectId,String beginTime,String endTime,List<BusFormula> formulaList){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("公式数据");

        /*设置单元格格式为文本格式*/
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本"cell.setCellStyle(textStyle);

        //设置要导出的文件的名字
        String fileName = "FormulaData.xls";
        String[] header = {"公式名称","公式内容","时间","数值"};

        //设置默认列宽
        sheet.setDefaultColumnWidth(12);
        //设置列宽
        sheet.setColumnWidth(0,5000);

        //创建合并单元格对象
        //起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress = new CellRangeAddress(2,2,1,formulaList.size());
        //加载合并单元格对象
        sheet.addMergedRegion(callRangeAddress);

        //添加第一行表头
        HSSFRow row1 = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<formulaList.size()+1;i++){
            if (i == 0) {
                HSSFCell cell = row1.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(header[0]);
                cell.setCellValue(text);
            }else {
                HSSFCell cell = row1.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(formulaList.get(i-1).getName());
                cell.setCellValue(text);
            }
        }

        //存储公式ID组合串
        StringBuffer formulaIds = new StringBuffer();
        formulaList.forEach(busFormula -> {
            //获得公式ID组合串
            formulaIds.append("'");
            formulaIds.append(busFormula.getId());
            formulaIds.append("'");
            formulaIds.append(",");
        });
        //删除末尾的逗号
        formulaIds.deleteCharAt(formulaIds.length()-1);

        //添加第二行表头
        HSSFRow row2 = sheet.createRow(1);
        for(int i=0;i<formulaList.size()+1;i++){
            if (i == 0) {
                HSSFCell cell = row2.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(header[1]);
                cell.setCellValue(text);
            }else {
                HSSFCell cell = row2.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(formulaList.get(i-1).getFormula());
                cell.setCellValue(text);
            }
        }
        //添加第三行表头
        HSSFRow row3 = sheet.createRow(2);
        for(int i=0;i<2;i++){
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header[i+2]);
            cell.setCellValue(text);
        }

        //查询出变量相应的二级参数代码
        List<FormulaVariableData> formulaVariableDataList = formulaDao.queryParamsByFormula(formulaIds.toString());
        //获取JSON数据
        beginTime = beginTime + " 00:00:00";
        endTime = endTime + " 23:59:59";
        List<SysData> sysDataList = sysDataDao.queryDataByProIdAndTime(projectId,beginTime,endTime);
        //分组存储公式变量信息
        Map<String,List<FormulaVariableData>> resultMap = new HashMap<>(16);
        /*
        将所有变量信息数据通过公式ID进行分组，存于resultMap中
         */
        for(FormulaVariableData formulaVariableData : formulaVariableDataList){
            if (resultMap.containsKey(formulaVariableData.getFormulaId())){
                resultMap.get(formulaVariableData.getFormulaId()).add(formulaVariableData);
            }else {
                List<FormulaVariableData> list = new ArrayList<>();
                list.add(formulaVariableData);
                resultMap.put(formulaVariableData.getFormulaId(),list);
            }
        }

        //新增数据行（从表格第四行开始），并且设置单元格数据
        int rowNum = 3;
//        // 公式计算类
//        FelEngine fel = new FelEngineImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //在表中存放查询到的数据放入对应的列
        for (SysData sysData : sysDataList) {
            JSONObject jsonObject = JSON.parseObject(sysData.getJson());
            HSSFRow row4 = sheet.createRow(rowNum);
            row4.createCell(0, CellType.STRING).setCellValue(sdf.format(sysData.getCreateTime()));
            //通过公式的数量进行循环
            int i = 1;
            for (String key : resultMap.keySet()){
                String formula = formulaList.get(i-1).getFormula();
                //通过每个公式对应的变量数循环
                for (FormulaVariableData formulaVariableData : resultMap.get(key)){
                    formula = this.fillValues(formula,formulaVariableData,jsonObject);
                }
                //row4.createCell(i).setCellValue(this.getResult(formula,fel));
                row4.createCell(i).setCellValue(FormulaUtil.getResult(formula));
                i++;
            }
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将公式中的变量名替换为JSON数据中的具体数值
     * @param formula
     * @param formulaVariableData
     * @param jsonObject
     * @return 替换后的计算表达式
     */
    private String fillValues(String formula,FormulaVariableData formulaVariableData,JSONObject jsonObject){
        JSONObject paramFirstCoding = jsonObject.getJSONObject(formulaVariableData.getParamFirstCoding());
        JSONObject param = paramFirstCoding.getJSONObject("REG_VAL");
        //存储变量对应的数值
        String value = "";
        if (param.get(formulaVariableData.getParamSecondCoding()) != null) {
            value = param.get(formulaVariableData.getParamSecondCoding()).toString();
        }else {
            value = "0";
        }
        //将公式中的变量替换为相应的数值
        formula = formula.replaceAll(formulaVariableData.getVariable(),value);
        return formula;
    }

//    /**
//     * 通过Fel公式计算引擎计算出最终结果
//     * @param formula
//     * @param fel
//     * @return 表达式计算后的结果
//     */
//    private String getResult(String formula, FelEngine fel) {
//        // 结果
//        String eval = "";
//        // 判断表达式是否由数字和运算符号组成
//        boolean flag = true;
//        String[] split = formula.split("");
//        for (String s : split) {
//            if (!s.matches("\\d|\\(|\\)|\\+|\\-|\\*|/|%|\\.")) {
//                flag = false;
//                break;
//            }
//        }
//        // 计算出公式的最终结果
//        if (flag) {
//            Object result = fel.eval(formula);
//            if (result != null) {
//                eval = result.toString();
//            }
//        }else {
//            eval = null;
//        }
//        return eval;
//    }
}
