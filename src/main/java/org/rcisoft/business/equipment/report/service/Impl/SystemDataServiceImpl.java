package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.base.util.FormulaUtil;
import org.rcisoft.base.util.ZhnyUtils;
import org.rcisoft.business.equipment.report.dao.SystemDataDao;
import org.rcisoft.business.equipment.report.entity.*;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.rcisoft.dao.SysDataDao;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.HOUR_OF_DAY;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/
@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataDao systemDataDao;
    @Autowired
    private SysDataDao sysDataDao;

    /**
     * 下载数据文档
     */
    @Override
    public void downlDataDocument(HttpServletResponse response,String paramSecondIds,String proId,String date){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); fdate.format(new Date())
        // 设置要导出的文件的名字
        String fileName = date + ".xls";
        //处理二级参数id格式
        StringBuilder secondIds = new StringBuilder();
        String[] ids = paramSecondIds.split(",");
        for (String id : ids) {
            secondIds.append("'");
            secondIds.append(id);
            secondIds.append("'");
            secondIds.append(",");
        }
        //删除末尾的逗号
        secondIds.deleteCharAt(secondIds.length()-1);
        //获取参数信息
        List<ParamSecondWithFirst> secondWithFirstList = systemDataDao.querySecondWithFirst(secondIds.toString());
        //获取data数据
        String beginTime = date + "00:00:00";
        String endTime = date + "23:59:59";
        List<SysData> sysDataList = sysDataDao.queryDataByProIdAndTime(proId,beginTime,endTime);
        //创建分页名
        String sheetName = "系统数据";
        HSSFSheet sheet = workbook.createSheet(sheetName);
        List<String> headlist = new ArrayList<>();
        headlist.add("时间");
        secondWithFirstList.forEach(paramSecondWithFirst -> headlist.add(paramSecondWithFirst.getFirstName()));
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headlist.size(); i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headlist.get(i));
            cell.setCellValue(text);
        }
        //根据数据条数循环导入数据
        SimpleDateFormat fdates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int rowNum = 1;
        for (SysData sysData : sysDataList) {
            int i = 1;
            //新建数据行
            HSSFRow row1 = sheet.createRow(rowNum);
            JSONObject jsonObject = JSON.parseObject(sysData.getJson());
            row1.createCell(0).setCellValue(fdates.format(sysData.getCreateTime()));
            //每列导入参数数据
            for (ParamSecondWithFirst paramSecondWithFirst : secondWithFirstList) {
                JSONObject paramFirst = jsonObject.getJSONObject(paramSecondWithFirst.getFirstCode());
                JSONObject paramSecond = paramFirst.getJSONObject("REG_VAL");
                row1.createCell(i).setCellValue(paramSecond.getString(paramSecondWithFirst.getSecondCode()));
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
     * 查询图表数据
     */
    @Override
    public List<Object> queryEchartData(String paramSecondIds,String proId,String date){
        List<Object> resultList = new ArrayList<>();
        //处理二级参数id格式
        StringBuilder secondIds = new StringBuilder();
        String[] ids = paramSecondIds.split(",");
        for (String id : ids) {
            secondIds.append("'");
            secondIds.append(id);
            secondIds.append("'");
            secondIds.append(",");
        }
        if (!StringUtils.isNullOrEmpty(secondIds.toString())) {
            //删除末尾的逗号
            secondIds.deleteCharAt(secondIds.length() - 1);
            //获取参数信息
            List<ParamSecondWithFirst> secondWithFirstList = systemDataDao.querySecondWithFirst(secondIds.toString());
            if (secondWithFirstList.size() <= 0){
                return null;
            }
            //获取data数据
            String beginTime = date + " 00:00:00";
            String endTime = date + " 23:59:59";
            //从sys_data表查询对应日期的所有记录
            List<SysData> sysDataList = sysDataDao.queryDataByTime(proId, beginTime, endTime);
            if (sysDataList.size() <= 0){
                return null;
            }
            //日期进行操作的类
            Calendar cal = Calendar.getInstance();
            List<String> nameList = new ArrayList<>();
            for (ParamSecondWithFirst paramSecondWithFirst : secondWithFirstList) {
                List<Object> list = Arrays.asList(new Object[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                for (SysData sysData : sysDataList) {
                    //得到json对象
                    JSONObject jsonObject = JSONObject.parseObject(sysData.getJson());
                    cal.setTime(sysData.getCreateTime());
                    JSONObject paramFirst = jsonObject.getJSONObject(paramSecondWithFirst.getFirstCode());
                    JSONObject paramSecond = paramFirst.getJSONObject("REG_VAL");
                    //整点数据
                    if (cal.get(Calendar.MINUTE) == 0) {
                        //得到小时
                        int hour = cal.get(HOUR_OF_DAY);
                        //参数数值
                        String paramValue = paramSecond.getString(paramSecondWithFirst.getSecondCode());
                        list.set(hour, paramValue);
                    }
                }
                resultList.add(list);
                nameList.add(paramSecondWithFirst.getSecondName());
            }
            resultList.add(nameList);
        }
        return resultList;
    }

    @Override
    public List<ReturnSystemData> listSystemData(SystemDataDto systemDataDto) {
        //要返回的数据
        List<ReturnSystemData> returnSystemDataList = new ArrayList<>();

        //获得参数列表
        List<FirstCodeAndSecondCode> firstCodeAndSecondCodeList = systemDataDto.getFirstCodeAndSecondCodeList();

        //得到网关原始数据
        List<SysData> sysDataList = systemDataDao.listDataByProIdAndDate(systemDataDto.getProjectId(), systemDataDto.getDate());

        //将原始数据按照每小时一次分组
        Long time = 3600000L;
        Map<Long,String> statusMap = ZhnyUtils.groupSysDataByTime(systemDataDto.getDate(),sysDataList,time);

        //设备名前缀数字
        int numFlag = 1;

        if (firstCodeAndSecondCodeList .size()>0){
            for (FirstCodeAndSecondCode firstCodeAndSecondCode : firstCodeAndSecondCodeList){
                ReturnSystemData returnSystemData = new ReturnSystemData();

                List<CodeValueAndHour> codeValueAndHours = new ArrayList<>();

                statusMap.forEach((k,v) ->{
                    CodeValueAndHour codeValueAndHour = new CodeValueAndHour();
                    //获得当前参数的值
                    String secondParamValue = FormulaUtil.getValueFromJson(firstCodeAndSecondCode.getFirstCode(), firstCodeAndSecondCode.getSecondCode(), v);
                    //获得当前是第几个小时
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date(k));
                    int hour = cal.get(Calendar.HOUR_OF_DAY);

                    codeValueAndHour.setSecondCodeValue(secondParamValue);
                    codeValueAndHour.setHour(hour);
                    codeValueAndHours.add(codeValueAndHour);
                });
                //封装返回值
                returnSystemData.setDeviceName(numFlag+"-"+firstCodeAndSecondCode.getDeviceName());
                returnSystemData.setCodeValueAndHours(codeValueAndHours);
                returnSystemDataList.add(returnSystemData);
                //让返回值前缀增加1
                numFlag++;
            }
        }
        return returnSystemDataList;
    }
}
