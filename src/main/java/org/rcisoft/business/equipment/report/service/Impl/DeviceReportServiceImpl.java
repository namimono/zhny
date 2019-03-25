package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.business.equipment.report.dao.DeviceReportDao;
import org.rcisoft.business.equipment.report.entity.MidAndParamFirst;
import org.rcisoft.business.equipment.report.service.DeviceReportService;
import org.rcisoft.dao.MidDeviceParamFirstDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:04
 **/
@Service
public class DeviceReportServiceImpl implements DeviceReportService {

    @Autowired
    private DeviceReportDao deviceReportDao;
    @Autowired
    private MidDeviceParamFirstDao midDeviceParamFirstDao;

//    public void downloadAllClassmate(HttpServletResponse response,String deviceId,String date){
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            //SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); fdate.format(new Date())
//            // 设置要导出的文件的名字
//            String fileName = date + ".xls";
//            //获取设备一级参数信息
//            List<MidAndParamFirst> midAndParamFirstList = midDeviceParamFirstDao.queryFirstInfoByMid(deviceId);
//            //将一级参数id合并为一个字符串
//            StringBuffer paramFirstIds = new StringBuffer();
//            midAndParamFirstList.forEach(midAndParamFirst -> {
//                paramFirstIds.append("'");
//                paramFirstIds.append(midAndParamFirst.getParamFirstId());
//                paramFirstIds.append("'");
//                paramFirstIds.append(",");
//            });
//            //删除末尾的逗号
//            paramFirstIds.deleteCharAt(paramFirstIds.length()-1);
//        //
//
//        //根据一级参数条数建立分页循环导入数据
//        for (int i = 0; i<midAndParamFirstList.size(); i++) {
//            String paramFirstId = midAndParamFirstList.get(i).getParamFirstId();
//            String[] deviceParam = deviceId.split("_",2);
//            String devicePId = deviceParam[1];
//            List<Map<String,Object>> TimeAndName = totalDataServiceImpl.getTimeAndName(deviceId);
//            String sheetName = TimeAndName.get(0).get("DEV_NM").toString();
//            //创建分页名
//            HSSFSheet sheet = workbook.createSheet(sheetName);
//            List<Map<String,Object>> ParamName = totalDataServiceImpl.getParamName(proId);
//            List<String> headlist = new ArrayList<>();
//            headlist.add("时间");
//            for (int j = 0; j<ParamName.size(); j++) {
//                headlist.add(ParamName.get(j).get("PARAM_NM").toString());
//            }
//            headlist.add("运行时长");
//            //新增数据行，并且设置单元格数据
//            int rowNum = 1;
//            //headers表示excel表中第一行的表头
//            HSSFRow row = sheet.createRow(0);
//            //在excel表中添加表头
//            for (int j0 = 0; j0 < headlist.size(); j0++) {
//                HSSFCell cell = row.createCell(j0);
//                HSSFRichTextString text = new HSSFRichTextString(headlist.get(j0));
//                cell.setCellValue(text);
//            }
//            //在表中存放查询到的数据放入对应的列
//            List<Map<String,Object>> ParamList = totalDataServiceImpl.getParam(deviceId);
//            List<Map<String,Object>> TMAndJSON = totalDataServiceImpl.getTMAndJSON(proId,date);
//            SimpleDateFormat fdates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            for (int k = 0;k<TMAndJSON.size();k++) {
//                HSSFRow row1 = sheet.createRow(rowNum);
//                JSONObject jsonObject = JSON.parseObject(TMAndJSON.get(k).get("JSON").toString());
//                JSONObject devicePaId = jsonObject.getJSONObject(devicePId);
//                JSONObject param = devicePaId.getJSONObject("REG_VAL");
//                row1.createCell(0).setCellValue(fdates.format(TMAndJSON.get(k).get("TM")));
//                row1.createCell(1).setCellValue(param.getString(ParamList.get(0).get("PARAM").toString()));
//                row1.createCell(2).setCellValue(param.getString(ParamList.get(1).get("PARAM").toString()));
//                row1.createCell(3).setCellValue(param.getString(ParamList.get(2).get("PARAM").toString()));
//                row1.createCell(4).setCellValue(param.getString(ParamList.get(3).get("PARAM").toString()));
//                row1.createCell(5).setCellValue(param.getString(ParamList.get(4).get("PARAM").toString()));
//                row1.createCell(6).setCellValue(param.getString(ParamList.get(5).get("PARAM").toString()));
//                row1.createCell(7).setCellValue(param.getString(ParamList.get(6).get("PARAM").toString()));
//                row1.createCell(8).setCellValue(param.getString(ParamList.get(7).get("PARAM").toString()));
//                row1.createCell(9).setCellValue(param.getString(ParamList.get(8).get("PARAM").toString()));
//                row1.createCell(10).setCellValue(param.getString(ParamList.get(9).get("PARAM").toString()));
//                row1.createCell(11).setCellValue(param.getString(ParamList.get(10).get("PARAM").toString()));
//                row1.createCell(12).setCellValue(param.getString(ParamList.get(11).get("PARAM").toString()));
//                row1.createCell(13).setCellValue(param.getString(ParamList.get(12).get("PARAM").toString()));
//                row1.createCell(14).setCellValue(param.getString(ParamList.get(13).get("PARAM").toString()));
//                rowNum++;
//            }
//        }
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//        try {
//            response.flushBuffer();
//            workbook.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
