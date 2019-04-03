package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.business.equipment.report.dao.SystemDataDao;
import org.rcisoft.business.equipment.report.entity.ParamSecondWithFirst;
import org.rcisoft.business.equipment.report.service.SystemDataService;
import org.rcisoft.dao.BusParamSecondDao;
import org.rcisoft.dao.SysDataDao;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/
@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataDao systemDataDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;
    @Autowired
    private SysDataDao sysDataDao;

//    /**
//     * 根据参数来源查询二级参数
//     */
//    @Override
//    public List<BusParamSecond> queryParamSecondBySource(String proId, String sourceId){
//        return busParamSecondDao.queryParamSecondByProId(proId,sourceId);
//    }

    /**
     * 下载数据文档
     */
    @Override
    public void downlDataDocument(HttpServletResponse response,String paramSecondIds,String proId,String beginTime,String endTime){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); fdate.format(new Date())
        // 设置要导出的文件的名字
        String fileName = beginTime + ".xls";
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
        beginTime += "00:00:00";
        endTime += "23:59:59";
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

}
