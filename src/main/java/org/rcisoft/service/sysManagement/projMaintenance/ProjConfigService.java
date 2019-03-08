package org.rcisoft.service.sysManagement.projMaintenance;

import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;
import org.rcisoft.vo.sysManagement.projMaintenance.ProjectBriefInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 土豆儿 on 2019-3-4
 */
public interface ProjConfigService {

    /**
     * 查询全部项目表信息
     */
    List<Map<String,Object>> queryAllInfo();

    /**
     * 新增项目配置信息
     */
    String addProjConfig(BusProject busProject);

    /**
     * 修改项目配置信息
     */
    int updateProjConfig(BusProject busProject);

    /**
     * 获取省份、城市及其code信息
     */
    List<Map<String,Object>> queryProvinceInfo();

    /**
     * 根据省份ID获取
     */
    List<Map<String,Object>> queryCityInfo(String provinceId);

    /**
     * 获取线下团队信息
     */
    List<Map<String,Object>> queryOutTeamInfo();

    /**
     * 获取线上团队信息
     */
    List<Map<String,Object>> queryOnTeamInfo();

    /**
     * 获取巡查员信息
     */
    List<Map<String,Object>> queryInspectorInfo();

    /**
     * 获取建筑类型信息
     */
    List<Map<String,Object>> queryBuildingInfo();

    /**
     * 获取建筑分区(气候分区)信息
     */
    List<Map<String,Object>> queryBuildZoneInfo();

    /**
     * 获取业主信息
     */
    List<Map<String,Object>> queryOwnerInfo();

    /**
     * 查询项目简要信息
     */
    List<Map<String,Object>> queryBriefInfo();

    /**
     * 新增建筑类型
     */
    int insertBuildType(BusBuilding busBuilding);

    /**
     * 修改建筑类型
     */
    int updateBuildType(BusBuilding busBuilding);

    /**
     * 删除建筑类型
     */
    int deleteBuildType(BusBuilding busBuilding);

    /**
     * 新增建筑分区(气候分区)
     */
    int insertBuildZone(BusBuildingZone busBuildingZone);

    /**
     * 修改建筑分区(气候分区)
     */
    int updateBuildZone(BusBuildingZone busBuildingZone);

    /**
     * 删除建筑分区(气候分区)
     */
    int deleteBuildZone(BusBuildingZone busBuildingZone);

    /**
     * 获取所有线上团队信息及团队负责人信息
     */
    List<Map<String,Object>> queryAllOnTeamInfo();
    /**
     * 获取所有线下团队信息及团队负责人信息
     */
    List<Map<String,Object>> queryAllOutTeamInfo();
}
