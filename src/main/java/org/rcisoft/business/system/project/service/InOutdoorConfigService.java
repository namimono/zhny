package org.rcisoft.business.system.project.service;

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
    int addIndoorInfo(BusIndoor busIndoor,BusIndoorParam busIndoorParam);

    /**
     * 删除室内环境信息
     */
    int deleteIndoorInfo(String indoorId);

    /**
     * 修改室内环境信息和室内环境参数
     */
    int updateIndoorInfo(BusIndoor busIndoor,BusIndoorParam busIndoorParam);

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
    int addOutdoorInfo(BusOutdoor busOutdoor);

    /**
     * 修改室外配置
     */
    int updateOutdoorInfo(BusOutdoor busOutdoor);

    /**
     * 查询室外配置
     */
    List<BusOutdoor> queryOutdoorInfo(String projectId);
}
