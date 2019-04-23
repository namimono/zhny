package org.rcisoft.business.system.project.service.Impl;

import com.mysql.jdbc.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.OtherConfigDao;
import org.rcisoft.business.system.project.entity.TitleAndSysName;
import org.rcisoft.business.system.project.entity.TitleParamAndParam;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:50
 **/
@Service
public class OtherConfigServiceImpl implements OtherConfigService {

    @Autowired
    private OtherConfigDao otherConfigDao;
    @Autowired
    private EnergyParamLibraryDao energyParamLibraryDao;
    @Autowired
    private BusTitleDao busTitleDao;
    @Autowired
    private BusTitleParamDao busTitleParamDao;

    /**
     * 导出模板（项目维护-其他配置-参数库）
     */
    @Override
    public void downloadLibraryTemplate(HttpServletResponse response,String deviceId,String deviceName){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("参数库模板");

        /*设置单元格格式为文本格式*/
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本" cell.setCellStyle(textStyle);
        sheet.setDefaultColumnStyle(0,textStyle);
        sheet.setDefaultColumnStyle(1,textStyle);
        sheet.setDefaultColumnStyle(2,textStyle);
        sheet.setDefaultColumnStyle(3,textStyle);
        sheet.setDefaultColumnStyle(4,textStyle);
        sheet.setDefaultColumnStyle(5,textStyle);
        sheet.setDefaultColumnStyle(6,textStyle);
        sheet.setDefaultColumnStyle(7,textStyle);

        List<BusParamSecond> paramSecondList = otherConfigDao.queryParamsSecondByDevId(deviceId);
        //设置要导出的文件的名字
        String fileName = deviceName + "模板";
        String[] header1 = {"设备型号"," "};
        String[] header2 = {"参数","二级参数名称","二级参数编码","来源","类型"};
        String[] header3 = {"主参数"," ","其他参数"};
        String[] header4 = {"参数1值","参数2值","参数3值","参数4值","功率（kw）","用气速率（m3/h）","电费用","气费用"};
        header1[1] = deviceName;
        //设置列宽
        sheet.setColumnWidth(0,3000);
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,3000);
        sheet.setColumnWidth(4,3000);
        sheet.setColumnWidth(5,4300);
        sheet.setColumnWidth(6,3000);
        //添加第一行表头
        HSSFRow row0 = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<header1.length;i++){
            HSSFCell cell = row0.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header1[i]);
            cell.setCellValue(text);
        }
        //添加第二行表头(表格第三行开始)
        HSSFRow row1 = sheet.createRow(2);
        for(int i=0;i<header2.length;i++){
            HSSFCell cell = row1.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header2[i]);
            cell.setCellValue(text);
        }
        //新增数据行（从表格第四行开始），并且设置单元格数据
        int rowNum = 3;
        //在表中存放查询到的数据放入对应的列
        int index = 1;
        String souce = "";
        String type = "";
        //循环插入二级参数数据
        for (BusParamSecond busParamSecond : paramSecondList) {
            if (busParamSecond.getSourceId() == 1){
                souce = "设备";
            }else if (busParamSecond.getSourceId() == 2){
                souce = "计量表";
            }else if (busParamSecond.getSourceId() == 3){
                souce = "传感器";
            }else {
                souce = "无";
            }
            if (busParamSecond.getFirstSign() == 3 || busParamSecond.getFirstSign() == 4){
                type = "副参数";
            }else if (busParamSecond.getFirstSign() == 1 || busParamSecond.getFirstSign() == 2){
                type = "主参数";
            }else {
                souce = "无";
            }
            HSSFRow row2 = sheet.createRow(rowNum);
            row2.createCell(0).setCellValue(index);
            row2.createCell(1).setCellValue(busParamSecond.getName());
            row2.createCell(2).setCellValue(busParamSecond.getCoding());
            row2.createCell(3).setCellValue(souce);
            row2.createCell(4).setCellValue(type);
            index++;
            rowNum++;
        }
        //添加第三行表头
        HSSFRow row3 = sheet.createRow(rowNum+1);
        //在excel表中添加表头
        for(int i=0;i<header3.length;i++){
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header3[i]);
            cell.setCellValue(text);
        }
        //添加第四行表头
        HSSFRow row4 = sheet.createRow(rowNum+2);
        for(int i=0;i<header4.length;i++){
            HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header4[i]);
            cell.setCellValue(text);
        }
//        HSSFRow row5 = sheet.createRow(10);
//        for(int i=0;i<8;i++){
//            HSSFCell cell = row5.createCell(i);
//            cell.setCellValue("");
//            cell.setCellStyle(textStyle);
//        }
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xls");
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入参数库模板数据
     */
    @Override
    public int importData(MultipartFile file, String deviceId, String projectId)
    {
        Workbook wb = null;
        try
        {
            if (Objects.requireNonNull(file.getOriginalFilename()).endsWith("xls")) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else {
                wb = new XSSFWorkbook(file.getInputStream());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return 0;
        }
        //获取sheet
        Sheet sheet = wb.getSheetAt(0);
        List<EnergyParamLibrary> paramLibraryList = new ArrayList<>();
//        for (Row rows : sheet) {
//            for (Cell cell : rows) {
//                cell.setCellType(CellType.STRING);
//            }
//        }
        for(int i = 8;i <= sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            } else if (row.getCell(0) == null ||
//                    StringUtils.isNullOrEmpty(row.getCell(0).getStringCellValue())) {
//                continue;
//            }
            if(!"参数1值".equals(row.getCell(0).getStringCellValue()) && !"主参数".equals(row.getCell(0).getStringCellValue())){
                EnergyParamLibrary energyParamLibrary = new EnergyParamLibrary();
                energyParamLibrary.setId(UuidUtil.create32());
                energyParamLibrary.setDeviceId(deviceId);
                energyParamLibrary.setProjectId(projectId);
                //参数值1
                if (row.getCell(0) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(0).getStringCellValue())) {
                        energyParamLibrary.setMainValue(new BigDecimal(row.getCell(0).getStringCellValue()));
                    }
                }
                //参数值2
                if (row.getCell(1) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(1).getStringCellValue())) {
                        energyParamLibrary.setMainValue2(new BigDecimal(row.getCell(1).getStringCellValue()));
                    }
                }
                //参数值3
                if (row.getCell(2) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(2).getStringCellValue())) {
                        energyParamLibrary.setParamValue(new BigDecimal(row.getCell(2).getStringCellValue()));
                    }
                }
                //参数值4
                if (row.getCell(3) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(3).getStringCellValue())) {
                        energyParamLibrary.setParamValue2(new BigDecimal(row.getCell(3).getStringCellValue()));
                    }
                }
                //功率
                if (row.getCell(4) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(4).getStringCellValue())) {
                        energyParamLibrary.setEnergyElec(new BigDecimal(row.getCell(4).getStringCellValue()));
                    }
                }
                //用气速率
                if (row.getCell(5) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(5).getStringCellValue())) {
                        energyParamLibrary.setEnergyGas(new BigDecimal(row.getCell(5).getStringCellValue()));
                    }
                }
                //电费用
                if (row.getCell(6) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(6).getStringCellValue())) {
                        energyParamLibrary.setMoneyElec(new BigDecimal(row.getCell(6).getStringCellValue()));
                    }
                }
                //气费用
                if (row.getCell(7) != null) {
                    if (!StringUtils.isNullOrEmpty(row.getCell(7).getStringCellValue())) {
                        energyParamLibrary.setMoneyGas(new BigDecimal(row.getCell(7).getStringCellValue()));
                    }
                }
                paramLibraryList.add(energyParamLibrary);
            }else {
                continue;
            }
        }

        try
        {
            wb.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Example example = new Example(EnergyParamLibrary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        criteria.andEqualTo("deviceId",deviceId);
        energyParamLibraryDao.deleteByExample(example);
        return energyParamLibraryDao.insertListUseAllCols(paramLibraryList);
    }

    /**
     * 增加自定义标题信息
     */
    @Override
    public int addTitleInfo(BusTitle busTitle){
        busTitle.setId(UuidUtil.create32());
        return busTitleDao.insert(busTitle);
    }

    /**
     * 删除自定义标题信息
     */
    @Override
    public int deleteTitleInfo(BusTitle busTitle){
        return busTitleDao.deleteByPrimaryKey(busTitle);
    }

    /**
     * 修改自定义标题信息
     */
    @Override
    public int updateTitleInfo(BusTitle busTitle){
        return busTitleDao.updateByPrimaryKeySelective(busTitle);
    }

    /**
     * 根据项目ID查询自定义标题信息
     */
    @Override
    public List<TitleAndSysName> queryTitleInfo(String projectId,String systemId){
        if ("0".equals(systemId)){
            return busTitleDao.queryTitleInfo(projectId);
        }else{
            return busTitleDao.queryTitleInfoBySys(projectId,systemId);
        }
    }

    /**
     * 增加自定义参数信息
     */
    @Override
    public int addTitleParamInfo(List<BusTitleParam> titleParamList,String titleId){
        if (titleParamList.size() > 0) {
            Example example = new Example(BusTitleParam.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("titleId",titleId);
            busTitleParamDao.deleteByExample(example);

            titleParamList.forEach(busTitleParam -> busTitleParam.setId(UuidUtil.create32()));
            return busTitleParamDao.insertListUseAllCols(titleParamList);
        }else {
            Example example = new Example(BusTitleParam.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("titleId",titleId);
            return busTitleParamDao.deleteByExample(example);
        }
    }

    /**
     * 根据自定义标题ID查询自定义参数信息
     */
    @Override
    public List<TitleParamAndParam> queryTitleParamsInfo(String titleId){
        return busTitleParamDao.queryTitleParamsInfo(titleId);
    }

}
