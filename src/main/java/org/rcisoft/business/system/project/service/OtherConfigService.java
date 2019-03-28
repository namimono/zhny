package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.rcisoft.business.system.project.entity.TitleParamAndParam;
import org.rcisoft.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:48
 **/

public interface OtherConfigService {

    /**
     * 根据参数来源查询一级表具参数信息
     */
    List<BusParamFirst> queryParamFirstBySource(BusParamFirst busParamFirst);

    /**
     * 查询设备简要信息（参数库）
     */
    List<DeviceBriefInfo> queryDeviceBrief(String projectId);

    /**
     * 根据设备ID查询二级参数信息（参数库）
     */
    List<BusParamSecond> queryParamSecondByDevId(String deviceId);

    /**
     * 联查一二级参数和参数库信息(用于导出模板)
     */
    List<LibraryAndParam> queryLibraryAndParam(LibraryAndParam libraryAndParam);

    /**
     * 导出模板（项目维护-其他配置-参数库）
     */
    void downloadLibraryTemplate(HttpServletResponse response,String year,String model,LibraryAndParam libraryAndParam);

//    /**
//     * 导入参数库模板数据
//     */
//    int importData(MultipartFile file, String deviceId, String projectId);

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
    List<BusTitle> queryTitleInfo(String projectId);

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
