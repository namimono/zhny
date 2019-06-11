package org.rcisoft.business.system.project.service.Impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.BasicDataDao;
import org.rcisoft.business.system.project.service.BasicDataService;
import org.rcisoft.dao.EnergyCarbonPlanDao;
import org.rcisoft.dao.EnergyPriceDao;
import org.rcisoft.dao.EnergyStandardDao;
import org.rcisoft.entity.EnergyCarbonPlan;
import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStandard;
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
import java.util.ArrayList;
import java.util.List;

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
    public String upload(MultipartFile file, String projectId) {

        //要保存到数据库中的数据
        List<EnergyCarbonPlan> saveEnergyCarbonPlanList = new ArrayList<>();

        if (file.isEmpty()) {
            return "文件为空";
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
                        return "上传成功";
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "请上传指定格式文件";
        }

        return "请上传指定格式文件";
    }

    /**
     * 下载基准碳排放量模板
     */
    @Override
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件名，根据业务需要替换成要下载的文件名
        String fileName = "碳排放量.xls";
        if (fileName != null) {
            //设置文件路径
            String realPath = "src/main/resources/excel/";
            File file = null;
            try {
                file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/碳排放量.xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
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
}
