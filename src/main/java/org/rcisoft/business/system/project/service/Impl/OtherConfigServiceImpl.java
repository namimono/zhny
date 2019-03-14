package org.rcisoft.business.system.project.service.Impl;

import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.service.OtherConfigService;
import org.rcisoft.dao.BusParamFirstDao;
import org.rcisoft.dao.BusParamLibraryDao;
import org.rcisoft.dao.EnergyConfigDao;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamLibrary;
import org.rcisoft.entity.EnergyConfig;
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
        return busParamLibraryDao.deleteByPrimaryKey(busParamFirstDao);
    }

//    /**
//     * 导出模板（项目维护-其他配置-参数库）
//     */
//    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        //SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd"); fdate.format(new Date())
//        String fileName = date + ".xls";//设置要导出的文件的名字
//        List<String> DnameList = totalDataServiceImpl.getDeviceId(proId);
//        for (int i = 0; i<DnameList.size(); i++) {
//            String deviceId = DnameList.get(i);
//            String deviceParam[] = deviceId.split("_",2);
//            String devicePId = deviceParam[1];
//            List<Map<String,Object>> TimeAndName = totalDataServiceImpl.getTimeAndName(deviceId);
//            String sheetName = TimeAndName.get(0).get("DEV_NM").toString();
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
//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
//    }
}
