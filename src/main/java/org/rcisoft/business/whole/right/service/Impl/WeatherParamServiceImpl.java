package org.rcisoft.business.whole.right.service.Impl;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.util.ExcelUtil;
import org.rcisoft.business.whole.right.dao.WeatherParamDao;
import org.rcisoft.business.whole.right.service.WeatherParamService;
import org.rcisoft.entity.BusTemperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:18 2019/4/12
 */
@Service
public class WeatherParamServiceImpl implements WeatherParamService{
    @Autowired
    private WeatherParamDao weatherParamDao;

    @Override
    public void downloadWeather(HttpServletRequest request,HttpServletResponse response,String proId,String start,String finish) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //创建Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        List<BusTemperature> list = weatherParamDao.downloadWeather(proId,start,finish);

        Row row = sheet.createRow(0);
        String city = weatherParamDao.queryCity(proId);
        row.createCell(0).setCellValue(city + " "+ start + "--" + finish);
        Row rowTitle = sheet.createRow(1);
        rowTitle.createCell(0).setCellValue("时间:");
        rowTitle.createCell(1).setCellValue("温度:");
        rowTitle.createCell(2).setCellValue("湿度:");
        rowTitle.createCell(3).setCellValue("风向:");
        int i = 0;
        for(BusTemperature bt:list){
            Row rowWeather = sheet.createRow(i + 2);
            String time = ft.format(bt.getCreateTime());
            rowWeather.createCell(0).setCellValue(time);
            rowWeather.createCell(1).setCellValue(bt.getTemperature().toString());
            rowWeather.createCell(2).setCellValue(bt.getHumidity().toString() + "%");
            rowWeather.createCell(3).setCellValue(bt.getWind());
            i++;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
            FileUtils.writeByteArrayToFile(new File("D:\\天气记录.xlsx"), os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelUtil.downloadExcel(request, response, "天气记录" , workbook);
    }
}
