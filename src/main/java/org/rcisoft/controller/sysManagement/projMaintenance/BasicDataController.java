package org.rcisoft.controller.sysManagement.projMaintenance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStandard;
import org.rcisoft.service.sysManagement.projMaintenance.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:23
 **/
@Api(tags = "项目编辑-基础数据")
@RestController
@RequestMapping("basicData")
public class BasicDataController {

    @Autowired
    private BasicDataService basicDataServiceImpl;

    @ApiOperation(value="新增水电气24小时单价信息", notes="新增水电气24小时单价信息")
    @PostMapping("/addPerHourPrice")
    public Result addPerHourPrice(@RequestBody List<EnergyPrice> list){
        return Result.result(1, basicDataServiceImpl.addPerHourPrice(list));
    }

    @ApiOperation(value="新增能源标准", notes="新增能源标准")
    @PostMapping("/addEnergyStandard")
    public Result addEnergyStandard(@RequestBody List<EnergyStandard> list){
        return Result.result(1, basicDataServiceImpl.addEnergyStandard(list));
    }

    @ApiOperation(value="上传基准碳排放量模板", notes="上传基准碳排放量模板")
    @PostMapping(value = "/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //String fileNames = file.getName();
            //logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //logger.info("文件的后缀名为：" + suffixName);

            // 设置文件存储路径
            String filePath = "G:/IDEA_Proj/zhny-back-2nd/src/main/resources/excel/";
            String path = filePath + fileName;// + suffixName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @ApiOperation(value="下载基准碳排放量模板", notes="下载基准碳排放量模板")
    @GetMapping(value = "/fileDownload")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String fileName = "碳排放量.xls"; //"新建文本文档.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "G:/IDEA_Proj/zhny-back-2nd/src/main/resources/excel/";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" +  java.net.URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
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
