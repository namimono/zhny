package org.rcisoft.business.system.project.service.Impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.OtherConfigDao;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.rcisoft.business.system.project.entity.TitleParamAndParam;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:50
 **/
@Service
public class OtherConfigServiceImpl implements OtherConfigService {

    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private EnergyConfigDao energyConfigDao;
    @Autowired
    private BusParamLibraryDao busParamLibraryDao;
    @Autowired
    private EnergyParamLibraryDao energyParamLibraryDao;
    @Autowired
    private OtherConfigDao otherConfigDao;
    @Autowired
    private BusTitleDao busTitleDao;
    @Autowired
    private BusTitleParamDao busTitleParamDao;

    /**
     * 根据参数来源查询表具
     */
    @Override
    public List<BusParamFirst> queryParamFirstBySource(BusParamFirst busParamFirst){
        return busParamFirstDao.queryParamFirstBySource(busParamFirst);
    }

    /**
     * 根据项目设备等ID查询能耗分类信息
     */
    @Override
    public List<EnergyTypeConfig> queryTypeNameByConfig(EnergyTypeConfig energyTypeConfig){
        return energyConfigDao.queryTypeNameByConfig(energyTypeConfig);
    }

    /**
     * 修改能源配置信息
     */
    @Override
    public int updateEnergyConfig(EnergyConfig energyConfig){
        return energyConfigDao.updateByPrimaryKeySelective(energyConfig);
    }

    /**
     * 根据设备ID、二级参数ID查询参数库信息
     */
    @Override
    public List<BusParamLibrary> queryParamLibrary(BusParamLibrary busParamLibrary){
        return busParamLibraryDao.queryParamLibrary(busParamLibrary);
    }

    /**
     * 新增参数库信息
     */
    @Override
    public int addParamLibrary(BusParamLibrary busParamLibrary){
        busParamLibrary.setId(UuidUtil.create32());
        return busParamLibraryDao.insertSelective(busParamLibrary);
    }

    /**
     * 修改参数库信息
     */
    @Override
    public int updateParamLibrary(BusParamLibrary busParamLibrary){
        return busParamLibraryDao.updateByPrimaryKeySelective(busParamLibrary);
    }

    /**
     * 删除参数库信息
     */
    @Override
    public int deleteParamLibrary(BusParamLibrary busParamLibrary){
        return busParamLibraryDao.deleteByPrimaryKey(busParamLibrary);
    }

    /**
     * 新增参数库记录表信息
     */
    @Override
    public int addEnergyParamLibrary(EnergyParamLibrary energyParamLibrary){
        energyParamLibrary.setId(UuidUtil.create32());
        return energyParamLibraryDao.insertSelective(energyParamLibrary);
    }

    /**
     * 删除参数库记录表信息
     */
    @Override
    public int deleteEnergyParamLibrary(EnergyParamLibrary energyParamLibrary){
        return energyParamLibraryDao.deleteByPrimaryKey(energyParamLibrary);
    }

    /**
     * 修改参数库记录表信息
     */
    @Override
    public int updateEnergyParamLibrary(EnergyParamLibrary energyParamLibrary){
        return energyParamLibraryDao.updateByPrimaryKeySelective(energyParamLibrary);
    }

    /**
     * 联查一二级参数和参数库信息
     */
    @Override
    public List<LibraryAndParam> queryLibraryAndParam(LibraryAndParam libraryAndParam){
        return otherConfigDao.queryLibraryAndParam(libraryAndParam);
    }

    /**
     * 导出模板（项目维护-其他配置-参数库）
     */
    @Override
    public void downloadLibraryTemplate(HttpServletResponse response,String year,String model,LibraryAndParam libraryAndParam){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("参数库模板");

        /*设置单元格格式为文本格式*/
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本" cell.setCellStyle(textStyle);

        List<LibraryAndParam> libraryAndParamList = otherConfigDao.queryLibraryAndParam(libraryAndParam);
        //设置要导出的文件的名字
        String fileName = model + ".xls";
        String[] header1 = {"设备型号"," "," ","年份"," "};
        String[] header2 = {"参数","二级参数名称","二级参数编码","来源","类型"};
        String[] header3 = {"主参数"," ","其他参数"};
        String[] header4 = {"参数1值","参数2值","参数3值","参数4值","功率（kw）","用气速率（m3/h）","电费用","气费用"};
        header1[1] = model;
        header1[4] = year;
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
        for (LibraryAndParam libraryAndParams : libraryAndParamList) {
            if ("1".equals(libraryAndParams.getSourceId())){
                souce = "设备";
            }else if ("2".equals(libraryAndParams.getSourceId())){
                souce = "计量表";
            }else if ("3".equals(libraryAndParams.getSourceId())){
                souce = "传感器";
            }else if ("4".equals(libraryAndParams.getSourceId())){
                souce = "固定参数";
            }
            if ((libraryAndParams.getCompareSign()) == 0){
                type = " ";
            }else if ((libraryAndParams.getCompareSign()) == 1){
                type = "主参数";
            }
            HSSFRow row2 = sheet.createRow(rowNum);
            row2.createCell(0).setCellValue(index);
            row2.createCell(1).setCellValue(libraryAndParams.getParamSecondName());
            row2.createCell(2).setCellValue(libraryAndParams.getParamSecondCoding());
            row2.createCell(3).setCellValue(souce);
            row2.createCell(4).setCellValue(type);
            index++;
            rowNum++;
        }
        //添加第三行表头
        HSSFRow row3 = sheet.createRow(8);
        //在excel表中添加表头
        for(int i=0;i<header3.length;i++){
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header3[i]);
            cell.setCellValue(text);
        }
        //添加第四行表头
        HSSFRow row4 = sheet.createRow(9);
        for(int i=0;i<header4.length;i++){
            HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header4[i]);
            cell.setCellValue(text);
        }
        HSSFRow row5 = sheet.createRow(10);
        for(int i=0;i<8;i++){
            HSSFCell cell = row5.createCell(i);
            cell.setCellValue(" ");
            cell.setCellStyle(textStyle);
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            //return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return false;
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
            if (file.getOriginalFilename().endsWith("xls")) {
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
        //获取第一张表
        Sheet sheet = wb.getSheetAt(0);

        EnergyParamLibrary energyParamLibrary = new EnergyParamLibrary();
        energyParamLibrary.setId(UuidUtil.create32());
        energyParamLibrary.setDeviceId(deviceId);
        energyParamLibrary.setProjectId(projectId);

        //获取索引为i的行，以0开始
        Row row = sheet.getRow(10);
        energyParamLibrary.setMainValue(new BigDecimal(row.getCell(0).getStringCellValue()));
        if (!" ".equals(row.getCell(1).getStringCellValue())) {
            energyParamLibrary.setMainValue2(new BigDecimal(row.getCell(1).getStringCellValue()));
        }
        energyParamLibrary.setParamValue(new BigDecimal(row.getCell(2).getStringCellValue()));
        if (!" ".equals(row.getCell(3).getStringCellValue())) {
            energyParamLibrary.setParamValue2(new BigDecimal(row.getCell(3).getStringCellValue()));
        }
        if (!" ".equals(row.getCell(4).getStringCellValue())) {
            energyParamLibrary.setEnergyElec(new BigDecimal(row.getCell(4).getStringCellValue()));
        }
        if (!" ".equals(row.getCell(5).getStringCellValue())) {
            energyParamLibrary.setEnergyGas(new BigDecimal(row.getCell(5).getStringCellValue()));
        }
        if (!" ".equals(row.getCell(6).getStringCellValue())) {
            energyParamLibrary.setMoneyElec(new BigDecimal(row.getCell(6).getStringCellValue()));
        }
        if (!" ".equals(row.getCell(6).getStringCellValue())) {
            energyParamLibrary.setMoneyGas(new BigDecimal(row.getCell(7).getStringCellValue()));
        }
        try
        {
            wb.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return energyParamLibraryDao.insertSelective(energyParamLibrary);
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
    public List<BusTitle> queryTitleInfo(String projectId){
        return busTitleDao.queryTitleInfo(projectId);
    }

    /**
     * 增加自定义参数信息
     */
    @Override
    public int addTitleParamInfo(BusTitleParam busTitleParam){
        busTitleParam.setId(UuidUtil.create32());
        return busTitleParamDao.insertSelective(busTitleParam);
    }

    /**
     * 删除自定义参数信息
     */
    @Override
    public int deleteTitleParamInfo(BusTitleParam busTitleParam){
        return busTitleParamDao.deleteByPrimaryKey(busTitleParam);
    }

    /**
     * 修改自定义参数信息
     */
    @Override
    public int updateTitleParamInfo(BusTitleParam busTitleParam){
        return busTitleParamDao.updateByPrimaryKeySelective(busTitleParam);
    }

    /**
     * 根据自定义标题ID查询自定义参数信息
     */
    @Override
    public List<TitleParamAndParam> queryTitleParamsInfo(String titleId){
        return busTitleParamDao.queryTitleParamsInfo(titleId);
    }
}
