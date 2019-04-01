package org.rcisoft.business.equipment.fault.service.impl;

import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.result.PageUtil;
import org.rcisoft.base.util.ExcelUtil;
import org.rcisoft.business.equipment.fault.dao.FaultDao;
import org.rcisoft.business.equipment.fault.entity.FaultCountResult;
import org.rcisoft.business.equipment.fault.entity.FaultResult;
import org.rcisoft.business.equipment.fault.service.FaultService;
import org.rcisoft.dao.BusMalfunctionDao;
import org.rcisoft.entity.BusMalfunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by JiChao on 2019/3/29.
 */
@Service
public class FaultServiceImpl implements FaultService {

    @Autowired
    FaultDao faultDao;
    @Autowired
    BusMalfunctionDao busMalfunctionDao;

    @Override
    public int[] queryFaultCount(String projectId, String typeFirstId, Integer year, Integer month) {
        // 当月天数
        int date = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        // 返回值
        int[] result = new int[date];
        List<FaultCountResult> list = faultDao.queryFaultCount(projectId, typeFirstId, year + "-" + month);
        list.forEach(faultCountResult -> {
            result[faultCountResult.getTime()] = faultCountResult.getCount();
        });
        return result;
    }

    @Override
    public PageInfo<FaultResult> queryFaults(String projectId, String typeFirstId, Integer year, Integer month) {
        List<FaultResult> list = faultDao.queryFaults(projectId, typeFirstId, year + "-" + month);
        return PageUtil.pageResult(list);
    }

    @Transactional
    @Override
    public int updateMalfunction(BusMalfunction busMalfunction) {
        return busMalfunctionDao.updateByPrimaryKeySelective(busMalfunction);
    }

    @Override
    public void downloadFaults(HttpServletRequest request, HttpServletResponse response, String projectId, String typeFirstId, Integer year, Integer month) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 结果集
        List<FaultResult> list = faultDao.queryFaults(projectId, typeFirstId, year + "-" + month);
        // 创建excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        Row titleRow = sheet.createRow(0);
        if (list == null || list.size() == 0) {
            titleRow.createCell(0, CellType.STRING).setCellValue("没有数据");
        } else {
            String[] titles = new String[]{"时间", "设备类型", "设备名称", "故障内容", "处理状态", "处理结果", "负责人"};
            for (int i = 0; i < titles.length; i++) {
                titleRow.createCell(i, CellType.STRING).setCellValue(titles[i]);
            }
            // 循环结果集
            for (int i = 0; i < list.size(); i++) {
                FaultResult faultResult = list.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0, CellType.STRING).setCellValue(format.format(faultResult.getCreateTime()));
                row.createCell(1, CellType.STRING).setCellValue(faultResult.getTypeFirstName());
                row.createCell(2, CellType.STRING).setCellValue(faultResult.getDeviceName());
                row.createCell(3, CellType.STRING).setCellValue(faultResult.getContent());
                row.createCell(4, CellType.STRING).setCellValue(faultResult.getStatus() == 0 ? "未处理" : "已处理");
                row.createCell(5, CellType.STRING).setCellValue(faultResult.getResult());
                row.createCell(6, CellType.STRING).setCellValue(faultResult.getPrincipal());
            }
        }
        ExcelUtil.downloadExcel(request, response, "故障报告", workbook);
    }
}
