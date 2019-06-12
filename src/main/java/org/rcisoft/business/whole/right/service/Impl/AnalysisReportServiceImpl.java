package org.rcisoft.business.whole.right.service.Impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.whole.right.dao.AnalysisReportDao;
import org.rcisoft.business.whole.right.service.AnalysisReportService;
import org.rcisoft.entity.BusReport;
import org.rcisoft.entity.SysImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:13 2019/4/12
 */
@Service
public class AnalysisReportServiceImpl implements AnalysisReportService {

    @Value("${location.path}")
    private String path;
    @Value("${location.analysisReport}")
    private String analysisReport;
    @Value("${location.url}")
    private String url;

    @Autowired
    private AnalysisReportDao analysisReportDao;


    @Override
    public List<BusReport> queryAnalysisReport(int year) {
        List<BusReport> list = analysisReportDao.queryAnalysisReport(year);
        list.forEach(busReport -> {
            busReport.setUrl(this.url + this.analysisReport + "/" + year + "/" + busReport.getUrl());
        });
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public ServiceResult uploadAnalysisReport(MultipartFile file, String proId, int year, int month) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        // 返回值
        Integer result = 0;
        // 后缀
        String suffix = "";
        String[] fileNameArray = StringUtils.split(file.getOriginalFilename(), ".");
        if (fileNameArray.length > 1) {
            suffix = "." + fileNameArray[fileNameArray.length - 1];
        }
        // 新的文件名
        String fileName = month + suffix;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + analysisReport + "/" + proId + "/" + year + "/" + fileName ));
            result = analysisReportDao.insert(new BusReport(UuidUtil.create32(), proId,now, year, month, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceResult(result, path + analysisReport + "/" + proId + "/" + year + "/" + fileName);
    }

    @Override
    public void downloadAnalysisReport(HttpServletRequest request, HttpServletResponse response, String proId, int year, int month) {
        String fileName = analysisReportDao.queryFileName(year, month);
        String filePath = path + analysisReport + "/" + proId + "/" + year + "/" + fileName;

        try (OutputStream outputStream = response.getOutputStream()) {
            byte[] bytes = FileUtils.readFileToByteArray(new File(filePath));
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer deleteAnalysisReport(String id) {
        BusReport busReport = analysisReportDao.queryFile(id);
        int year = busReport.getTimeYear();
        int month = busReport.getTimeMonth();

        String fileName = analysisReportDao.queryFileName(year, month);
        String filePath = path + analysisReport + "/" + year + "/" + fileName;
        System.out.println(filePath);
        File file = new File(filePath);
        if(file.exists()&&file.isFile()){
            file.delete();
        }
        Integer status = analysisReportDao.deleteByPrimaryKey(id);
        return status;
    }
}

