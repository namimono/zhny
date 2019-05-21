package org.rcisoft.business.system.project.service;

import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.business.system.project.entity.PositionInfo;
import org.rcisoft.business.system.project.entity.ProjectBriefInfo;
import org.rcisoft.business.system.project.entity.ProjectConfigInfo;
import org.rcisoft.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 土豆儿 on 2019-3-4
 */
public interface ProjConfigService {

    /**
     * 查询全部项目表信息
     */
    List<ProjectAssessment> queryAllInfo();

    /**
     * 新增项目配置信息和节能改造信息
     */
    ServiceResult addProjConfig(ProjectConfigInfo projectConfigInfo);

    /**
     * 修改项目配置信息
     */
    int updateProjConfig(ProjectConfigInfo projectConfigInfo);

    /**
     * 获取省份、城市及其code信息
     */
    List<PositionInfo> queryProvinceInfo();

    /**
     * 处理省份、城市及其code信息的格式为List<Map<string,List<Map<string,string>>>>
     */
    List<Map<String,Object>> processingFormat();
//
//    /**
//     * 根据省份ID获取
//     */
//    List<Map<String,Object>> queryCityInfo(String provinceId);

    /**
     * 获取线下团队信息
     */
    List<BusTeam> queryOutTeamInfo();

    /**
     * 获取线上团队信息
     */
    List<BusTeam> queryOnTeamInfo();

    /**
     * 获取巡查员信息
     */
    List<SysInspector> queryInspectorInfo();

    /**
     * 获取建筑类型信息
     */
    List<BusBuilding> queryBuildingInfo();

    /**
     * 获取建筑分区(气候分区)信息
     */
    List<BusBuildingZone> queryBuildZoneInfo();

    /**
     * 获取业主信息
     */
    List<SysUser> queryOwnerInfo();

    /**
     * 查询项目简要信息
     */
    List<ProjectBriefInfo> queryBriefInfo(Integer online);

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
     * 查询认定员信息
     */
    List<SysAuthenticator> queryAuthenticator();

    /**
     * 删除项目信息（谨慎！）
     *
     */
    int deleteAllByProId(String projectId);

    /**
     * 项目表、节能改造表联查编辑回显
     */
    List<ProjectConfigInfo> queryProjectConfigInfo(String projectId);

    /**
     * 查询系统类型
     */
    List<SysSystem> querySystemType();

    /**
     * 新增系统类型
     */
    int addSysSystem(SysSystem sysSystem);

    /**
     * 删除系统类型
     */
    int deleteSysSystem(String systemId);

    /**
     * 修改系统类型
     */
    int updateSysSystem(SysSystem sysSystem);

    /**
     * 修改项目的接收状态
     */
    int updateProjectReceiveAndOnline(ProjectConfigInfo projectConfigInfo);
}
