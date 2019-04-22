package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.TitleAndSysName;
import org.rcisoft.business.system.project.entity.TitleParamAndParam;
import org.rcisoft.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:48
 **/

public interface OtherConfigService {

    /**
     * 导出模板（项目维护-其他配置-参数库）
     */
    void downloadLibraryTemplate(HttpServletResponse response,String deviceId,String deviceName);

    /**
     * 导入参数库模板数据
     */
    int importData(MultipartFile file, String deviceId, String projectId);

    /**
     * 增加自定义标题信息
     */
    int addTitleInfo(BusTitle busTitle);

    /**
     * 删除自定义标题信息
     */
    int deleteTitleInfo(BusTitle busTitle);


    /**
     * 修改自定义标题信息
     */
    int updateTitleInfo(BusTitle busTitle);

    /**
     * 根据项目ID查询自定义标题信息
     */
    List<TitleAndSysName> queryTitleInfo(String projectId,String systemId);

    /**
     * 增加自定义参数信息
     */
    int addTitleParamInfo(BusTitleParam busTitleParam);

    /**
     * 删除自定义参数信息
     */
    int deleteTitleParamInfo(BusTitleParam busTitleParam);

    /**
     * 修改自定义参数信息
     */
    int updateTitleParamInfo(BusTitleParam busTitleParam);

    /**
     * 根据自定义标题ID查询自定义参数信息
     */
    List<TitleParamAndParam> queryTitleParamsInfo(String titleId);

}
