package org.rcisoft.business.whole.right.service;

import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.entity.BusReport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:12 2019/4/12
 */
public interface AnalysisReportService {
    List<BusReport> queryAnalysisReport();
    ServiceResult uploadAnalysisReport(MultipartFile file,String proId,int year,int month);
    String downloadAnalysisReport(HttpServletRequest request, HttpServletResponse response, String id, int year, int month) throws Exception;
}
