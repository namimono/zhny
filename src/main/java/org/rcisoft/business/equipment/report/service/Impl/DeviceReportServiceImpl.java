package org.rcisoft.business.equipment.report.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
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
        // 设置要导出的文件的名字
        String fileName = date + ".xls";

        //水平居中
        HSSFCellStyle styleMain = workbook.createCellStyle();
        styleMain.setAlignment(HorizontalAlignment.CENTER);

        //设置单元格内换行可识别\r\n
        HSSFCellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setWrapText(true);

        //设置边框
        HSSFCellStyle setBorder = workbook.createCellStyle();
        setBorder.setBorderBottom(BorderStyle.THIN);
        setBorder.setBorderTop(BorderStyle.THIN);
        setBorder.setBorderRight(BorderStyle.THIN);

        //获取设备一级参数信息
        List<BusDevice> deviceList = busDeviceDao.queryDeviceByProjectId(proId);
        if (deviceList.size() <= 0){
            return;
        }
        //获取data数据
        List<SysData> sysDataList;
        if (date.length() > 7){
            String beginTime = date + " 00:00:00";
            String endTime = date + " 23:59:59";
            sysDataList = sysDataDao.queryDataByProIdAndTime(proId,beginTime,endTime);
        }else {
            String[] dates = date.split("-");
            String year = dates[0];
            String month = dates[1];
            sysDataList = deviceReportDao.querySysDataByMonth(year,month,proId);
        }
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

            //创建分页名
            String sheetName = busDevice.getName();
            HSSFSheet sheet = workbook.createSheet(sheetName);

            //设置默认列宽
            sheet.setDefaultColumnWidth(13);
            //设置列宽
            sheet.setColumnWidth(0,5200);

            //根据一级参数条数建立分页循环导入数据
            List<String> firstHeadList = new ArrayList<>();
            paramFirstList.forEach(busParamFirst -> firstHeadList.add(busParamFirst.getName()));

            //循环插入二级参数名称作为表头
            List<String> headList = new ArrayList<>();
            for (String key : resultMap.keySet()) {
                resultMap.get(key).forEach(busParamSecond -> headList.add(busParamSecond.getName()));
            }

            //画线(由左上到右下的斜线)  在A1的第一个cell（单位  分类）加入一条对角线
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short)0, 0, (short)0, 1);
            HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
            shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
            shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID) ;

            //设置分类表头
            HSSFRow row0 = sheet.createRow(0);
            CellRangeAddress callRangeAddress1 = new CellRangeAddress(0,1,0,0);
            sheet.addMergedRegion(callRangeAddress1);
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellStyle(cellStyle);
            cell0.setCellValue(new HSSFRichTextString("                     参数名称\r\n 时间"));

            //在excel表中添加第二行表头
            HSSFRow row1 = sheet.createRow(1);
            for (int i = 1; i <= headList.size(); i++) {
                HSSFCell cell = row1.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headList.get(i-1));
                cell.setCellValue(text);
                cell.setCellStyle(setBorder);
            }

            //合并单元格,并插入第一行表头
            int firstCol = 1;
            int lastCol = 0;
            int index = 0;
            for (String key : resultMap.keySet()) {
                lastCol += resultMap.get(key).size();
                //创建合并单元格对象(起始行,结束行,起始列,结束列)
                if (lastCol == 0 || lastCol <= firstCol){continue;}
                CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,firstCol,lastCol);
                //插入第一行表头
                HSSFCell cell = row0.createCell(firstCol);
                HSSFRichTextString text = new HSSFRichTextString(firstHeadList.get(index));
                cell.setCellValue(text);
                //设为水平居中
                cell.setCellStyle(styleMain);
                //加载合并单元格对象
                sheet.addMergedRegion(callRangeAddress);
                //设置合并单元格边框
                RegionUtil.setBorderRight(BorderStyle.THIN,callRangeAddress,sheet);
                firstCol += resultMap.get(key).size();
                index++;
            }

            //设置A1合并单元格边框
            RegionUtil.setBorderBottom(BorderStyle.THIN,callRangeAddress1,sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN,callRangeAddress1,sheet);


            //将二级参数的数值循环插入表格
            int rowNum = 2;
            int flag = 0;
            SimpleDateFormat formatDates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (SysData sysData : sysDataList) {
                int column = 1;
                HSSFRow row2 = sheet.createRow(rowNum);
                JSONObject jsonObject = JSON.parseObject(sysData.getJson());
                for(BusParamFirst busParamFirst : paramFirstList){
                    JSONObject paramFirst = jsonObject.getJSONObject(busParamFirst.getCoding());
                    if (paramFirst == null) {
                        column += resultMap.get(busParamFirst.getId()).size();
                        continue;
                    }
                    JSONObject paramSecond = paramFirst.getJSONObject("REG_VAL");
                    for (String key : resultMap.keySet()) {
                        //循环插入json中对应的二级参数数值
                        if (key.equals(busParamFirst.getId())) {
                            for (BusParamSecond busParamSecond : resultMap.get(key)) {
                                if (paramSecond.getString(busParamSecond.getCoding()) == null) {
                                    column++;
                                    continue;
                                }
                                row2.createCell(column).setCellValue(paramSecond.getString(busParamSecond.getCoding()));
                                column++;
                                flag++;
                            }
                        }
                    }
                    if (flag > 0){
                        row2.createCell(0).setCellValue(formatDates.format(sysData.getCreateTime()));
                    }
                }
                if (flag == 0){
                    rowNum--;
                }
                rowNum++;
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
