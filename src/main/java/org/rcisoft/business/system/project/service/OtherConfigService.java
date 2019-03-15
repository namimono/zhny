package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.rcisoft.business.system.project.entity.TitleParamAndParam;
import org.rcisoft.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 14:48
 **/

public interface OtherConfigService {

    /**
     * 根据参数来源查询表具
     */
    List<BusParamFirst> queryParamFirstBySource(BusParamFirst busParamFirst);

    /**
     * 根据项目设备等ID查询能耗分类信息
     */
    List<EnergyTypeConfig> queryTypeNameByConfig(EnergyTypeConfig energyTypeConfig);

    /**
     * 修改能源配置信息
     */
    int updateEnergyConfig(EnergyConfig energyConfig);

    /**
     * 根据设备ID、二级参数ID查询参数库信息
     */
    List<BusParamLibrary> queryParamLibrary(BusParamLibrary busParamLibrary);

    /**
     * 新增参数库信息
     */
    int addParamLibrary(BusParamLibrary busParamLibrary);

    /**
     * 删除参数库信息
     */
    int deleteParamLibrary(BusParamLibrary busParamLibrary);

    /**
     * 修改参数库信息
     */
    int updateParamLibrary(BusParamLibrary busParamLibrary);

    /**
     * 新增参数库记录表信息
     */
    int addEnergyParamLibrary(EnergyParamLibrary energyParamLibrary);

    /**
     * 删除参数库记录表信息
     */
    int deleteEnergyParamLibrary(EnergyParamLibrary energyParamLibrary);

    /**
     * 修改参数库记录表信息
     */
    int updateEnergyParamLibrary(EnergyParamLibrary energyParamLibrary);

    /**
     * 联查一二级参数和参数库信息
     */
    List<LibraryAndParam> queryLibraryAndParam(LibraryAndParam libraryAndParam);

    /**
     * 导出模板（项目维护-其他配置-参数库）
     */
    void downloadLibraryTemplate(HttpServletResponse response,String year,String model,LibraryAndParam libraryAndParam);

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
