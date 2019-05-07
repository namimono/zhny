package org.rcisoft.business.operation.parameter.service.Impl;

import org.apache.poi.hssf.usermodel.*;
import org.rcisoft.business.operation.establishment.dao.DevicePlanningRepository;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.DeviceNameAndSeq;
import org.rcisoft.business.operation.parameter.dao.ParameterDao;
import org.rcisoft.business.operation.parameter.service.ParameterService;
import org.rcisoft.business.system.auth.entity.DeviceParamNameValue;
import org.rcisoft.business.system.auth.entity.DeviceParamValue;
import org.rcisoft.business.system.project.dao.OtherConfigDao;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.dao.EnergyParamLibraryDao;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.entity.EnergyParamLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/2 14:47
 **/
@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private OtherConfigDao otherConfigDao;
    @Autowired
    private EnergyParamLibraryDao energyParamLibraryDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private DevicePlanningRepository devicePlanningRepository;

    /**
     * 查询设备简要信息（参数库）
     */
    @Override
    public List<DeviceBriefInfo> queryDeviceBriefByType(String systemId, String projectId, String deviceTypeId) {

        return parameterDao.queryDeviceBriefBySys(systemId,projectId,deviceTypeId);

    }

    /**
     * 导出参数库数据
     */
    @Override
    public void downloadParameter(HttpServletResponse response, String deviceId, String deviceName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("参数库数据");

        /*设置单元格格式为文本格式*/
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本" cell.setCellStyle(textStyle);
        sheet.setDefaultColumnStyle(0, textStyle);
        sheet.setDefaultColumnStyle(1, textStyle);
        sheet.setDefaultColumnStyle(2, textStyle);
        sheet.setDefaultColumnStyle(3, textStyle);
        sheet.setDefaultColumnStyle(4, textStyle);
        sheet.setDefaultColumnStyle(5, textStyle);
        sheet.setDefaultColumnStyle(6, textStyle);
        sheet.setDefaultColumnStyle(7, textStyle);

        List<BusParamSecond> paramSecondList = otherConfigDao.queryParamsSecondByDevId(deviceId);
        //设置要导出的文件的名字
        String fileName = deviceName + "数据";
        String[] header1 = {"设备型号", " "};
        String[] header2 = {"参数", "二级参数名称", "二级参数编码", "来源", "类型"};
        String[] header3 = {"主参数", " ", "其他参数"};
        String[] header4 = {"参数1值", "参数2值", "参数3值", "参数4值", "功率（kw）", "用气速率（m3/h）", "电费用", "气费用"};
        header1[1] = deviceName;
        //设置列宽
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 4300);
        sheet.setColumnWidth(6, 3000);
        //添加第一行表头
        HSSFRow row0 = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < header1.length; i++) {
            HSSFCell cell = row0.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header1[i]);
            cell.setCellValue(text);
        }
        //添加第二行表头(表格第三行开始)
        HSSFRow row1 = sheet.createRow(2);
        for (int i = 0; i < header2.length; i++) {
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
            if (busParamSecond.getSourceId() == 1) {
                souce = "设备";
            } else if (busParamSecond.getSourceId() == 2) {
                souce = "计量表";
            } else if (busParamSecond.getSourceId() == 3) {
                souce = "传感器";
            } else {
                souce = "无";
            }
            if (busParamSecond.getFirstSign() == 3 || busParamSecond.getFirstSign() == 4) {
                type = "副参数";
            } else if (busParamSecond.getFirstSign() == 1 || busParamSecond.getFirstSign() == 2) {
                type = "主参数";
            } else {
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
        HSSFRow row3 = sheet.createRow(rowNum + 1);
        //在excel表中添加表头
        for (int i = 0; i < header3.length; i++) {
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header3[i]);
            cell.setCellValue(text);
        }
        //添加第四行表头
        HSSFRow row4 = sheet.createRow(rowNum + 2);
        for (int i = 0; i < header4.length; i++) {
            HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header4[i]);
            cell.setCellValue(text);
        }
        //根据设备ID获取参数库数据
        Example example = new Example(EnergyParamLibrary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deviceId", deviceId);
        List<EnergyParamLibrary> paramLibraryList = energyParamLibraryDao.selectByExample(example);
        int rowsNum = rowNum + 3;
        //循环填入参数库数据
        for (EnergyParamLibrary energyParamLibrary : paramLibraryList) {
            HSSFRow row5 = sheet.createRow(rowsNum);
            row5.createCell(0).setCellValue(energyParamLibrary.getMainValue().toString());
            row5.createCell(1).setCellValue(energyParamLibrary.getMainValue2().toString());
            row5.createCell(2).setCellValue(energyParamLibrary.getParamValue().toString());
            row5.createCell(3).setCellValue(energyParamLibrary.getParamValue2().toString());
            row5.createCell(4).setCellValue(energyParamLibrary.getEnergyElec().toString());
            row5.createCell(5).setCellValue(energyParamLibrary.getEnergyGas().toString());
            row5.createCell(6).setCellValue(energyParamLibrary.getMoneyElec().toString());
            row5.createCell(7).setCellValue(energyParamLibrary.getMoneyGas().toString());
            rowsNum++;
        }

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
     * 查询参数库参数数据
     */
    @Override
    public DeviceParamNameValue queryParamLibrary(String deviceId) {
        //要返回的数据
        DeviceParamNameValue returnDeviceParamNameValue = new DeviceParamNameValue();
        List<DeviceParamValue> returnDeviceParamValueList = new ArrayList<>();

        EnergyParamLibrary energyParamLibrary = new EnergyParamLibrary();
        energyParamLibrary.setDeviceId(deviceId);
        //查出设备参数的值
        List<EnergyParamLibrary> energyParamLibraryList = energyParamLibraryDao.select(energyParamLibrary);

        ConditionDto conditionDto = new ConditionDto();
        conditionDto.setDevId(deviceId);
        //查出设备参数的名称
        List<DeviceNameAndSeq> deviceNameInfoList = devicePlanningRepository.listDeviceParamNameAndSeqByDevId(conditionDto);

        //这个设备有几个参数（2个，3个，4个）
        int flag = deviceNameInfoList.size();
        switch (flag){
            //当有2个参数的时候，第一个主参数作为X轴，第一个副参数作为Y轴，Z轴为0
            case 2:
                //设置参数名
                returnDeviceParamNameValue.setZzName("无参数");
                for (DeviceNameAndSeq deviceNameAndSeq : deviceNameInfoList){
                    //1代表主参数1；2代表主参数2；3代表副参数；4代表副参数2
                    switch (deviceNameAndSeq.getFirstSign()){
                        case 1:
                            returnDeviceParamNameValue.setXzName(deviceNameAndSeq.getDevParamName());
                            break;
                        case 3:
                            returnDeviceParamNameValue.setYzName(deviceNameAndSeq.getDevParamName());
                            break;
                        default:
                            break;
                    }
                }
                //设置参数值
                for (EnergyParamLibrary energyParam : energyParamLibraryList){
                    DeviceParamValue deviceParamValue = new DeviceParamValue();
                    deviceParamValue.setXzValue(energyParam.getMainValue());
                    deviceParamValue.setYzValue(energyParam.getParamValue());
                    deviceParamValue.setZzValue(new BigDecimal(0));
                    returnDeviceParamValueList.add(deviceParamValue);
                }
                break;
            //当有3个参数的时候，第一个主参数作为X轴，第一个副参数作为Y轴，第二个副参数作为Z轴
            case 3:
                //设置参数名
                for (DeviceNameAndSeq deviceNameAndSeq : deviceNameInfoList){
                    //1代表主参数1；2代表主参数2；3代表副参数；4代表副参数2
                    switch (deviceNameAndSeq.getFirstSign()){
                        case 1:
                            returnDeviceParamNameValue.setXzName(deviceNameAndSeq.getDevParamName());
                            break;
                        case 3:
                            returnDeviceParamNameValue.setYzName(deviceNameAndSeq.getDevParamName());
                            break;
                        case 4:
                            returnDeviceParamNameValue.setZzName(deviceNameAndSeq.getDevParamName());
                            break;
                        default:
                            break;
                    }
                }
                //设置参数值
                for (EnergyParamLibrary energyParam : energyParamLibraryList){
                    DeviceParamValue deviceParamValue = new DeviceParamValue();
                    deviceParamValue.setXzValue(energyParam.getMainValue());
                    deviceParamValue.setYzValue(energyParam.getParamValue());
                    deviceParamValue.setZzValue(energyParam.getParamValue2());
                    returnDeviceParamValueList.add(deviceParamValue);
                }
                break;
            //当有4个参数的时候，第一个主参数作为X轴，第二个主参数作为Y轴，第一个副参数作为Z轴，舍弃第二个副参数
            case 4:
                //设置参数名
                for (DeviceNameAndSeq deviceNameAndSeq : deviceNameInfoList){
                    //1代表主参数1；2代表主参数2；3代表副参数；4代表副参数2
                    switch (deviceNameAndSeq.getFirstSign()){
                        case 1:
                            returnDeviceParamNameValue.setXzName(deviceNameAndSeq.getDevParamName());
                            break;
                        case 2:
                            returnDeviceParamNameValue.setYzName(deviceNameAndSeq.getDevParamName());
                            break;
                        case 3:
                            returnDeviceParamNameValue.setZzName(deviceNameAndSeq.getDevParamName());
                            break;
                        default:
                            break;
                    }
                }
                //设置参数值
                for (EnergyParamLibrary energyParam : energyParamLibraryList){
                    DeviceParamValue deviceParamValue = new DeviceParamValue();
                    deviceParamValue.setXzValue(energyParam.getMainValue());
                    deviceParamValue.setYzValue(energyParam.getMainValue2());
                    deviceParamValue.setZzValue(energyParam.getParamValue());
                    returnDeviceParamValueList.add(deviceParamValue);
                }
                break;
            default:
                break;
        }
        returnDeviceParamNameValue.setDeviceParamValueList(returnDeviceParamValueList);
        return returnDeviceParamNameValue;
    }
}
