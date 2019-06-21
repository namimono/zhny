package org.rcisoft.business.system.project.service.Impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.result.Result;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.BasicDataDao;
import org.rcisoft.business.system.project.service.BasicDataService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:27
 **/
@Service
public class BasicDataServiceImpl implements BasicDataService {

    @Autowired
    private BasicDataDao basicDataDao;
    @Autowired
    private EnergyStandardDao energyStandardDao;
    @Autowired
    private EnergyPriceDao energyPriceDao;
    @Autowired
    private EnergyCarbonPlanDao energyCarbonPlanDao;
    @Autowired
    private EnergySavePlanDao energySavePlanDao;
    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;

    /**
     * 新增水电气24小时单价信息
     */
    @Override
    public int addPerHourPrice(List<EnergyPrice> list) {
        list.forEach(energyPrice -> energyPrice.setId(UuidUtil.create32()));
        return energyPriceDao.insertListUseAllCols(list);
    }

    /**
     * 修改水电气24小时单价信息
     */
    @Override
    public int updatePerHourPrice(List<EnergyPrice> list) {
        Example example = new Example(EnergyPrice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", list.get(0).getProjectId());
        energyPriceDao.deleteByExample(example);
        list.forEach(energyPrice -> energyPrice.setId(UuidUtil.create32()));
        return energyPriceDao.insertListUseAllCols(list);
    }

    /**
     * 查询水电气24小时单价信息
     */
    @Override
    public List<EnergyPrice> queryPerHourPrice(String proId) {
        Example example = new Example(EnergyPrice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", proId);
        return energyPriceDao.selectByExample(example);
    }

    /**
     * 新增能源标准
     */
    @Override
    public int addEnergyStandard(List<EnergyStandard> list) {
        list.forEach(energyStandard -> energyStandard.setId(UuidUtil.create32()));
        return energyStandardDao.insertListUseAllCols(list);
    }

    /**
     * 修改能源标准
     */
    @Override
    public int updateEnergyStandard(List<EnergyStandard> list) {
        Example example = new Example(EnergyStandard.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", list.get(0).getProjectId());
        energyStandardDao.deleteByExample(example);
        list.forEach(energyStandard -> energyStandard.setId(UuidUtil.create32()));
        return energyStandardDao.insertListUseAllCols(list);
    }

    /**
     * 查询能源标准
     */
    @Override
    public List<EnergyStandard> queryEnergyStandard(String proId) {
        Example example = new Example(EnergyStandard.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", proId);
        return energyStandardDao.selectByExample(example);
    }

    /**
     * 上传基准碳排放量模板
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result upload(MultipartFile file, String projectId) {

        //要保存到数据库中的数据
        List<EnergyCarbonPlan> saveEnergyCarbonPlanList = new ArrayList<>();

        if (file.isEmpty()) {
            return Result.result(0,"文件为空");
        }

        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            //获得sheet个数
            int sheetNum = workbook.getNumberOfSheets();
            //如果有sheet则继续
            if (sheetNum > 0) {
                //获得第一个sheet
                Sheet sheet = workbook.getSheetAt(0);
                //要计算的行数
                int rowNum = 31;
                for (int i = 0; i <= rowNum; i++) {
                    //获得当前行的数据
                    Row row = sheet.getRow(i);
                    //如果当前行数据为空或者是第一行，则进行下一行
                    if (i == 0 || null == row) {
                        continue;
                    }
                    //获得一共多少列
                    int cellNum = row.getLastCellNum();
                    for (int j = 0; j < cellNum; j++) {
                        //获得当前行的当前列
                        Cell cell = row.getCell(j);
                        //如果当前行的当前列位空或者是第一列，则进行下一列
                        if (0.0 == cell.getNumericCellValue() || j == 0) {
                            continue;
                        }
                        EnergyCarbonPlan energyCarbonPlan = new EnergyCarbonPlan();
                        energyCarbonPlan.setId(UuidUtil.create32());
                        energyCarbonPlan.setProjectId(projectId);
                        energyCarbonPlan.setTimeMonth(j);
                        energyCarbonPlan.setTimeDay(i);
                        energyCarbonPlan.setCarbon(new BigDecimal(cell.getNumericCellValue()));
                        saveEnergyCarbonPlanList.add(energyCarbonPlan);
                    }
                }

                if (saveEnergyCarbonPlanList.size() > 0){
                    //删除原来的
                    EnergyCarbonPlan delEnergyCarbonPlan = new EnergyCarbonPlan();
                    delEnergyCarbonPlan.setProjectId(projectId);
                    energyCarbonPlanDao.delete(delEnergyCarbonPlan);

                    int flag = energyCarbonPlanDao.saveEnergyCarbonPlan(saveEnergyCarbonPlanList);
                    if (flag > 0) {
                        return Result.result(1,"上传成功");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(0,"请上传指定格式文件");
        }
        return Result.result(0,"请填写数据后上传");
    }

    /**
     * 下载基准碳排放量模板
     */
    @Override
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        // 设置文件名，根据业务需要替换成要下载的文件名
        String fileName = "碳排放量.xls";
        if (fileName != null) {
            //设置文件路径
            File file = null;
            try {
                file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/" + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                try {
                    response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void downloadSave(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "累计节省";
        try (OutputStream outputStream = response.getOutputStream()) {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/" + fileName +".xls");
            byte[] bytes = FileUtils.readFileToByteArray(file);
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result uploadSave(MultipartFile file, String projectId) {
        // 待保存
        List<EnergySavePlan> saveList = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            //获得sheet个数
            int sheetNum = workbook.getNumberOfSheets();
            //如果有sheet则继续
            if (sheetNum > 0) {
                //获得第一个sheet
                Sheet sheet = workbook.getSheetAt(0);
                //要计算的行数
                int rowNum = 31;
                for (int i = 0; i <= rowNum; i++) {
                    //获得当前行的数据
                    Row row = sheet.getRow(i);
                    //如果当前行数据为空或者是第一行，则进行下一行
                    if (i == 0 || null == row) {
                        continue;
                    }
                    for (int j = 1; j < 13; j++) {
                        //获得当前行的当前列
                        Cell cell = row.getCell(j);
                        //如果当前行的当前列位空或者是第一列，则进行下一列
                        if (0.0 == cell.getNumericCellValue()) {
                            continue;
                        }
                        EnergySavePlan energySavePlan = new EnergySavePlan(UuidUtil.create32(), projectId, j, i, new BigDecimal(cell.getNumericCellValue()));

                        saveList.add(energySavePlan);
                    }
                }

                if (saveList.size() > 0){
                    //删除原来的
                    EnergySavePlan energySavePlan = new EnergySavePlan();
                    energySavePlan.setProjectId(projectId);
                    energySavePlanDao.delete(energySavePlan);

                    int flag = energySavePlanDao.batchSave(saveList);
                    if (flag > 0) {
                        return Result.result(1,"上传成功");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(0,"请上传指定格式文件");
        }
        return Result.result(0,"请填写数据后上传");
    }

    @Override
    public void downloadDevice(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "设备模板";
        try (OutputStream outputStream = response.getOutputStream()) {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/" + fileName +".xlsx");
            byte[] bytes = FileUtils.readFileToByteArray(file);
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public Result uploadDevice(MultipartFile file, String projectId, String systemId, String deviceId) {
        // 如果存在参数，禁止下载
        BusParamFirst bf = new BusParamFirst();
        bf.setDeviceId(deviceId);
        int exist = busParamFirstDao.selectCount(bf);
        if (exist > 0) {
            return Result.result(0, null, "已经存在配置的参数，请删除后再上传", null);
        }
        int result = 0;
        List<BusParamFirst> firstList = new ArrayList<>();
        List<BusParamSecond> secondList = new ArrayList<>();
        String checkFirstCoding = "";
        String firstId = "";
        int seq = 1;
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            // 从第三列开始获取数据
            for (int i = 3; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                // 如果第一列取到空值，认为结束
                if (row != null) {
                    String first = row.getCell(0).getStringCellValue();
                    if (StringUtils.isEmpty(first)) {
                        break;
                    }
                    // 一级参数
                    String firstName = row.getCell(1).getStringCellValue();
                    // 参数来源，如果没有相应的来源，跳过这一条记录
                    int source = this.sourceType(firstName);
                    if (source == 0) {
                        continue;
                    }
                    String firstCoding = row.getCell(2).getStringCellValue();
                    // 如果不等于
                    if (!StringUtils.equals(firstCoding, checkFirstCoding)) {
                        checkFirstCoding = firstCoding;
                        firstId = UuidUtil.create32();
                        // 新建一级参数
                        BusParamFirst f = new BusParamFirst();
                        f.setId(firstId);
                        f.setDeviceId(deviceId);
                        f.setSystemId(systemId);
                        f.setProjectId(projectId);
                        f.setName(firstName);
                        f.setCoding(firstCoding);
                        f.setSourceId(source);
                        firstList.add(f);
                        // 重置二级的序列
                        seq = 1;
                    }
                    // 二级参数
                    String secondName = row.getCell(3).getStringCellValue();
                    String secondCoding = row.getCell(4).getStringCellValue();
                    String secondUnit = row.getCell(5).getStringCellValue();
                    Double secondValue = row.getCell(6).getNumericCellValue();
                    String secondFault = row.getCell(7).getStringCellValue();
                    Double secondMax = row.getCell(8).getNumericCellValue();
                    Double secondMin = row.getCell(9).getNumericCellValue();
                    String secondContent = row.getCell(10).getStringCellValue();
                    String secondEnergyType = row.getCell(11).getStringCellValue();
                    String secondElecType = row.getCell(12).getStringCellValue();
                    String secondParam = row.getCell(13).getStringCellValue();
                    String secondFirst = row.getCell(14).getStringCellValue();
                    String secondTopo = row.getCell(15).getStringCellValue();
                    BusParamSecond s = new BusParamSecond();
                    s.setId(UuidUtil.create32());
                    s.setParamFirstId(firstId);
                    s.setProjectId(projectId);
                    s.setSystemId(systemId);
                    s.setDeviceId(deviceId);
                    s.setSourceId(source);
                    s.setName(secondName);
                    s.setCoding(secondCoding);
                    s.setUnit(secondUnit);
                    s.setValue(new BigDecimal(secondValue));
                    s.setSequence(seq++);
                    s.setFaultStatus(this.trueOrFalse(secondFault));
                    s.setMinValue(new BigDecimal(secondMin));
                    s.setMaxValue(new BigDecimal(secondMax));
                    s.setContent(secondContent);
                    s.setEnergyTypeId(this.energyType(secondEnergyType));
                    s.setElecType(this.elecType(secondElecType));
                    s.setFirstSign(this.firstSign(secondParam, secondFirst));
                    s.setShowStatus(this.trueOrFalse(secondTopo));
                    secondList.add(s);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Result.result(0, null, "模板填写有误", null);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 保存
        if (firstList.size() > 0) {
            result += busParamFirstDao.insertListUseAllCols(firstList);
        }
        if (secondList.size() > 0) {
            result += busParamSecondDao.insertListUseAllCols(secondList);
        }
        return Result.result(result, "上传设备模板成功", "上传设备模板失败", null);
    }

    private int sourceType(String value) {
        int result = 0;
        switch (value) {
            case "设备":
                result = 1;
                break;
            case "计量表":
                result = 2;
                break;
            case "传感器":
                result = 3;
                break;
            case "固定参数":
                result = 4;
                break;
            default:
                break;
        }
        return result;
    }

    private int energyType(String value) {
        int result = 0;
        switch (value) {
            case "水":
                result = 1;
                break;
            case "电":
                result = 2;
                break;
            case "气":
                result = 3;
                break;
            default:
                break;
        }
        return result;
    }

    private int elecType(String value) {
        int result = 0;
        switch (value) {
            case "电度":
                result = 0;
                break;
            case "功率":
                result = 1;
                break;
            default:
                break;
        }
        return result;
    }

    private int firstSign(String param, String first) {
        if (StringUtils.equals(param, "主参数")) {
            if (StringUtils.equals(first, "是")) {
                return 1;
            } else {
                return 2;
            }
        } else if (StringUtils.equals(param, "副参数")) {
            if (StringUtils.equals(first, "是")) {
                return 3;
            } else {
                return 4;
            }
        } else {
            return 0;
        }
    }

    private int trueOrFalse(String value) {
        if (StringUtils.equals(value, "是")) {
            return 1;
        } else {
            return 0;
        }
    }
}
