package org.rcisoft.business.system.project.service;

import org.rcisoft.base.result.Result;
import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStandard;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:27
 **/

public interface BasicDataService {

    /**
     *新增水电气24小时单价信息
     */
    int addPerHourPrice(List<EnergyPrice> list);

    /**
     *修改水电气24小时单价信息
     */
    int updatePerHourPrice(List<EnergyPrice> list);

    /**
     *查询水电气24小时单价信息
     */
    List<EnergyPrice> queryPerHourPrice(String proId);

    /**
     * 新增能源标准
     */
    int addEnergyStandard(List<EnergyStandard> list);

    /**
     * 修改能源标准
     */
    int updateEnergyStandard(List<EnergyStandard> list);

    /**
     * 查询能源标准
     */
    List<EnergyStandard> queryEnergyStandard(String proId);

    /**
     * 上传基准碳排放量模板
     */
    Result upload(MultipartFile file, String projectId);

    /**
     * 下载基准碳排放量模板
     */
    String downloadFile(HttpServletRequest request, HttpServletResponse response);

    /**
     * 下载基准碳排放量模板
     */
    void downloadSave(HttpServletRequest request, HttpServletResponse response);

    /**
     * 上传节省模板
     */
    Result uploadSave(MultipartFile file, String projectId);

    /**
     * 下载设备模板
     * @param request
     * @param response
     */
    void downloadDevice(HttpServletRequest request, HttpServletResponse response);

    /**
     * 上传设备模板
     * @param file
     * @param projectId
     * @return
     */
    Result uploadDevice(MultipartFile file, String projectId, String systemId, String deviceId);

}
