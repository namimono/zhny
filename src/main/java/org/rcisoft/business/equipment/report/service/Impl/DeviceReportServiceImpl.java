package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.business.equipment.report.dao.DeviceReportDao;
import org.rcisoft.business.equipment.report.service.DeviceReportService;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.dao.BusParamFirstDao;
import org.rcisoft.dao.BusParamSecondDao;
import org.rcisoft.dao.SysDataDao;
import org.rcisoft.entity.BusDevice;
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
 * @date 2019/3/19 15:04
 **/
@Service
public class DeviceReportServiceImpl implements DeviceReportService {

    @Autowired
    private DeviceReportDao deviceReportDao;
    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;
    @Autowired
    private BusDeviceDao busDeviceDao;
    @Autowired
    private SysDataDao sysDataDao;

    /**
     * 导出当日设备信息excel
     */
    @Override
    public void downloadDeviceTodayData(HttpServletResponse response,String proId,String date){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd"); fdate.format(new Date())
        // 设置要导出的文件的名字
        String fileName = date + ".xls";
        //获取设备一级参数信息
        List<BusDevice> deviceList = busDeviceDao.queryDeviceByProjectId(proId);
        if (deviceList.size() <= 0){
            return;
        }
        //获取data数据
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        List<SysData> sysDataList = sysDataDao.queryDataByProIdAndTime(proId,beginTime,endTime);
        if (sysDataList.size() <= 0){
            return;
        }
        for (BusDevice busDevice : deviceList) {
            List<BusParamFirst> paramFirstList = busParamFirstDao.queryParamFirstByDevId(busDevice.getId());
            if (paramFirstList.size() <= 0){
                break;
            }
            //将一级参数id合并到一个字符串
            StringBuffer paramFirstIds = new StringBuffer();
            paramFirstList.forEach(busParamFirst -> {
                paramFirstIds.append("'");
                paramFirstIds.append(busParamFirst.getId());
                paramFirstIds.append("'");
                paramFirstIds.append(",");
            });
            //删除末尾的逗号
            paramFirstIds.deleteCharAt(paramFirstIds.length() - 1);
            //获取二级参数信息
            List<BusParamSecond> busParamSecondList = busParamSecondDao.queryParamSecondByIds(paramFirstIds.toString());
            if (busParamSecondList.size() <= 0){
                break;
            }
            //分组存储二级参数信息
            Map<String, List<BusParamSecond>> resultMap = new HashMap<>(16);
            /*
            将所有变量信息数据通过公式ID进行分组，存于resultMap中
             */
            for (BusParamSecond busParamSecond : busParamSecondList) {
                if (resultMap.containsKey(busParamSecond.getParamFirstId())) {
                    resultMap.get(busParamSecond.getParamFirstId()).add(busParamSecond);
                } else {
                    List<BusParamSecond> list = new ArrayList<>();
                    list.add(busParamSecond);
                    resultMap.put(busParamSecond.getParamFirstId(), list);
                }
            }
            String sheetName = busDevice.getName();
            //创建分页名
            HSSFSheet sheet = workbook.createSheet(sheetName);
            //设置默认列宽
            sheet.setDefaultColumnWidth(13);
            //设置列宽
            sheet.setColumnWidth(0,5200);
            //根据一级参数条数建立分页循环导入数据
            int rowNum = 0;
            int flag = 0;
            for (BusParamFirst busParamFirst : paramFirstList) {
                String[] header = {"一级参数名称",""};
                header[1] = busParamFirst.getName();
                List<String> headList = new ArrayList<>();
                headList.add("时间");
                for (String key : resultMap.keySet()) {
                    //循环插入二级参数名称作为表头
                    if (key.equals(busParamFirst.getId())) {
                        resultMap.get(key).forEach(busParamSecond -> {
                            headList.add(busParamSecond.getName());
                        });
                    }
                }
                //headList.add("运行时长");
                //在excel表中添加表头
                if (flag > 0){
                   rowNum += 2;
                }
                HSSFRow row0 = sheet.createRow(rowNum);
                for (int i = 0;i < header.length;i++){
                    HSSFCell cell = row0.createCell(i);
                    HSSFRichTextString text = new HSSFRichTextString(header[i]);
                    cell.setCellValue(text);
                }
                rowNum++;
                HSSFRow row1 = sheet.createRow(rowNum);
                for (int i = 0; i < headList.size(); i++) {
                    HSSFCell cell = row1.createCell(i);
                    HSSFRichTextString text = new HSSFRichTextString(headList.get(i));
                    cell.setCellValue(text);
                }
                //在表中存放查询到的数据放入对应的列
                SimpleDateFormat formatDates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (SysData sysData : sysDataList) {
                    rowNum++;
                    HSSFRow row2 = sheet.createRow(rowNum);
                    JSONObject jsonObject = JSON.parseObject(sysData.getJson());
                    JSONObject paramFirst = jsonObject.getJSONObject(busParamFirst.getCoding());
                    if (paramFirst == null) {
                        rowNum--;
                        continue;
                    }
                    JSONObject paramSecond = paramFirst.getJSONObject("REG_VAL");
                    row2.createCell(0).setCellValue(formatDates.format(sysData.getCreateTime()));
                    for (String key : resultMap.keySet()) {
                        //循环插入json中对应的二级参数数值
                        int k = 1;
                        if (key.equals(busParamFirst.getId())) {
                            for (BusParamSecond busParamSecond : resultMap.get(key)) {
                                if (paramSecond.getString(busParamSecond.getCoding()) == null) {
                                    k++;
                                    continue;
                                }
                                row2.createCell(k).setCellValue(paramSecond.getString(busParamSecond.getCoding()));
                                k++;
                            }
                        }
                    }
                }
                flag++;
            }
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
