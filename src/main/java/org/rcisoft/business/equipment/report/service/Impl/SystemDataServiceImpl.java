package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.util.ExcelUtil;
import org.rcisoft.base.util.FormulaUtil;
import org.rcisoft.base.util.ZhnyUtils;
import org.rcisoft.business.equipment.report.dao.SystemDataDao;
import org.rcisoft.business.equipment.report.entity.*;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.rcisoft.dao.SysDataDao;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /** 根路径 */
    @Value("${location.path}")
    String path;

    /**
     * 下载数据文档
     */
    @Override
    public void downlDataDocument(HttpServletRequest request, HttpServletResponse response, SystemDataDto systemDataDto) {

        //创建excel需要的数据
        List<ReturnSystemData> returnSystemDataList = getData(systemDataDto);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 设置要导出的文件的名字
        String fileName = sdf.format(systemDataDto.getDate()) + "系统数据";

        //要返回的excel
        Workbook workbook = new XSSFWorkbook();
        //创建分页名
        String sheetName = "sheet1";
        Sheet sheet = workbook.createSheet(sheetName);

        //excel的第一行（表头）
        Row headerRow = sheet.createRow(0);
        //在excel表中添加表头数据（一共25列，第一列位空，后面24列对应24个小时）
        for (int i = 0; i <= 24; i++) {
            //第一行的第一列特殊设置
            if (i == 0) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue("");
            } else {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(i - 1 + "时");
            }
        }

        //给第一行之后的行设置数据
        for (int index = 0; index < returnSystemDataList.size(); index++) {
            //新建一行（表头之后的第N行）
            Row otherRow = sheet.createRow(index + 1);
            //对这一行的添加数据
            for (int cellIndex = 0; cellIndex <= 24; cellIndex++) {
                //第一列特殊处理，设置位设备名称
                if (cellIndex == 0) {
                    Cell cell = otherRow.createCell(cellIndex);
                    cell.setCellValue(returnSystemDataList.get(index).getDeviceName());
                } else {//之后的24列分别对应每个小时的数据
                    //这一列要设置的24个小时的数据
                    List<CodeValueAndHour> codeValueAndHourList = returnSystemDataList.get(index).getCodeValueAndHours();
                    for (CodeValueAndHour codeValueAndHour : codeValueAndHourList) {
                        Cell cell = otherRow.createCell(codeValueAndHour.getHour() + 1);
                        cell.setCellValue(codeValueAndHour.getSecondCodeValue());
                    }
                }
            }
        }
        ExcelUtil.downloadExcel(request, response, fileName, workbook);
    }

    /**
     * 查询图表数据
     */
    @Override
    public List<Object> queryEchartData(String paramSecondIds, String proId, String date) {
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
            if (secondWithFirstList.size() <= 0) {
                return null;
            }
            //获取data数据
            String beginTime = date + " 00:00:00";
            String endTime = date + " 23:59:59";
            //从sys_data表查询对应日期的所有记录
            List<SysData> sysDataList = sysDataDao.queryDataByTime(proId, beginTime, endTime);
            if (sysDataList.size() <= 0) {
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
        return getData(systemDataDto);
    }

    /**
     * 返回系统数据
     *
     * @param systemDataDto
     * @return List<ReturnSystemData>
     */
    private List<ReturnSystemData> getData(SystemDataDto systemDataDto) {

        //要返回的数据
        List<ReturnSystemData> returnSystemDataList = new ArrayList<>();

        //获得参数列表
        List<FirstCodeAndSecondCode> firstCodeAndSecondCodeList = systemDataDto.getFirstCodeAndSecondCodeList();

        //得到网关原始数据
        List<SysData> sysDataList = systemDataDao.listDataByProIdAndDate(systemDataDto.getProjectId(), systemDataDto.getDate());

        //将原始数据按照每小时一次分组
        Long time = 3600000L;
        Map<Long, String> statusMap = ZhnyUtils.groupSysDataByTime(systemDataDto.getDate(), sysDataList, time);

        //设备名前缀数字
        int numFlag = 1;

        if (firstCodeAndSecondCodeList.size() > 0) {
            for (FirstCodeAndSecondCode firstCodeAndSecondCode : firstCodeAndSecondCodeList) {
                ReturnSystemData returnSystemData = new ReturnSystemData();

                List<CodeValueAndHour> codeValueAndHours = new ArrayList<>();

                statusMap.forEach((k, v) -> {
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
                returnSystemData.setDeviceName(numFlag + "-" + firstCodeAndSecondCode.getDeviceName());
                returnSystemData.setCodeValueAndHours(codeValueAndHours);
                returnSystemDataList.add(returnSystemData);
                //让返回值前缀增加1
                numFlag++;
            }
        }
        return returnSystemDataList;
    }

}
