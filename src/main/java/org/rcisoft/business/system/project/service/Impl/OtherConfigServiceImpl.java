package org.rcisoft.business.system.project.service.Impl;

import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.OtherConfigDao;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.dao.BusParamFirstDao;
import org.rcisoft.dao.BusParamLibraryDao;
import org.rcisoft.dao.EnergyConfigDao;
import org.rcisoft.dao.EnergyParamLibraryDao;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamLibrary;
import org.rcisoft.entity.EnergyConfig;
import org.rcisoft.entity.EnergyParamLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<LibraryAndParam> libraryAndParamList = otherConfigDao.queryLibraryAndParam(libraryAndParam);
        //设置要导出的文件的名字
        String fileName = model + ".xls";
        String[] header1 = {"设备型号"," "," ","年份"," "};
        String[] header2 = {"参数","二级参数名称","二级参数编码","来源","类型"};
        String[] header3 = {" "," "," ","主参数"," ","其他参数"};
        String[] header4 = {"参数值列表","功率（kw）","用气速率（m3/h）","参数1值","参数2值","参数3值","参数4值"};
        header1[1] = model;
        header1[4] = year;
        //设置列宽
        sheet.setColumnWidth(0,3000);
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,4300);
        sheet.setColumnWidth(3,3000);
        sheet.setColumnWidth(4,3000);
        sheet.setColumnWidth(5,3000);
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
        //添加第一行表头
        HSSFRow row3 = sheet.createRow(rowNum+1);
        //在excel表中添加表头
        for(int i=0;i<header3.length;i++){
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header3[i]);
            cell.setCellValue(text);
        }
        //添加第二行表头(表格第三行开始)
        HSSFRow row4 = sheet.createRow(rowNum+2);
        for(int i=0;i<header4.length;i++){
            HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header4[i]);
            cell.setCellValue(text);
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
}
