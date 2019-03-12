package org.rcisoft.business.system.project.service;

import org.rcisoft.entity.EnergyPrice;
import org.rcisoft.entity.EnergyStandard;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * 新增能源标准
     */
    int addEnergyStandard(List<EnergyStandard> list);

    /**
     * 上传基准碳排放量模板
     */
    String upload(MultipartFile file);

    /**
     * 下载基准碳排放量模板
     */
    String downloadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

}