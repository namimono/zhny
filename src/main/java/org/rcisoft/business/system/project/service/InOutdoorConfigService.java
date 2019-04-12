package org.rcisoft.business.system.project.service;

import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.business.system.project.entity.IndoorContainParam;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;
import org.rcisoft.entity.BusOutdoor;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/11 16:44
 **/

public interface InOutdoorConfigService {

    /**
     * 新增室内环境信息和室内环境参数
     */
    ServiceResult addIndoorInfo(IndoorContainParam indoorContainParam);

    /**
     * 删除室内环境信息
     */
    int deleteIndoorInfo(String indoorId);

    /**
     * 修改室内环境信息和室内环境参数
     */
    int updateIndoorInfo(IndoorContainParam indoorContainParam);

    /**
     * 查询室内环境信息
     */
    List<BusIndoor> queryIndoorInfo(String projectId);

    /**
     * 查询室内环境参数
     */
    List<BusIndoorParam> queryIndoorParamInfo(String indoorId);

    /**
     * 新增室外配置
     */
    int addOutdoorInfo(List<BusOutdoor> busOutdoorList);

    /**
     * 修改室外配置
     */
    int updateOutdoorInfo(List<BusOutdoor> busOutdoorList);

    /**
     * 查询室外配置
     */
    List<BusOutdoor> queryOutdoorInfo(String projectId);
}
