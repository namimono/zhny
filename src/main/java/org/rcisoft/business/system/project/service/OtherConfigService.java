package org.rcisoft.business.system.project.service;

import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamLibrary;
import org.rcisoft.entity.EnergyConfig;
import org.rcisoft.entity.EnergyParamLibrary;

import javax.servlet.http.HttpServletResponse;
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
}
