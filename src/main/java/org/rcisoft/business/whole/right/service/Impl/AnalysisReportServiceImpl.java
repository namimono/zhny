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

    @Autowired
    private AnalysisReportDao analysisReportDao;


    @Override
    public List<BusReport> queryAnalysisReport() {
        return analysisReportDao.queryAnalysisReport();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public ServiceResult uploadAnalysisReport(MultipartFile file, String proId, int year, int month) {
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
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + analysisReport + "/" + year + "/" + fileName ));
            result = analysisReportDao.insert(new BusReport(UuidUtil.create32(), proId, year, month, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceResult(result, path + analysisReport + "/" + year + "/" + fileName);
    }

    @Override
    public String downloadAnalysisReport(HttpServletRequest request, HttpServletResponse response, String proId, int year, int month) throws Exception {
        String fileName = analysisReportDao.queryFileName(year, month);
        String filePath = path + analysisReport + "/" + year + "/" + fileName;
        System.out.println(filePath);
        File file = new File(filePath);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName="+URLEncoder.encode(fileName, "UTF-8"));
        int index=0;
        InputStream input=new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buff =new byte[1024];
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }

        return null;
        /*String fileName = analysisReportDao.queryFileName(year, month);
        if (fileName != null) {
            //设置文件路径
            String realPath = "D://";
            File file = new File(realPath, fileName);
            if(!file.exists()){
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try { //创建文件
                     file.createNewFile();
                } catch (IOException e)
                { e.printStackTrace();
                }
            }else{
                try(OutputStream outputStream = response.getOutputStream()) {
                    //获取文件字节
                    byte[] bytes = FileUtils.readFileToByteArray(file);
                    //设置下载头
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "iso-8859-1"));
                    outputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;*/




        /*BusReport busReport = new BusReport();
        busReport.setProjectId(proId);
        busReport.setTimeYear(year);
        busReport.setTimeMonth(month);
        busReport = analysisReportDao.selectOne(busReport);
        String path = busReport.getUrl();
        String fileName = busReport.getTimeMonth() + path.substring(path.lastIndexOf("."));
        if (fileName != null) {
            //设置文件路径
            String realPath = "D://";
            File file = new File(realPath, fileName);
            if(!file.exists()){
                //先得到文件的上级目录，并创建上级目录，在创建文件
                file.getParentFile().mkdir();
                try { //创建文件
                    file.createNewFile();
                } catch (IOException e)
                { e.printStackTrace();
                }
            }else{
                try(OutputStream outputStream = response.getOutputStream()) {
                    //获取文件字节
                    byte[] bytes = FileUtils.readFileToByteArray(new File(path));
                    //设置下载头
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "iso-8859-1"));
                    outputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;*/


    }
}

