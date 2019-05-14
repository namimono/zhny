package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.business.system.project.dao.ProConfigDao;
import org.rcisoft.business.system.project.entity.ProjectBriefInfo;
import org.rcisoft.business.system.project.entity.ProjectConfigInfo;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.rcisoft.business.system.project.entity.PositionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:39
 **/

@Service
public class ProjConfigServiceImpl implements ProjConfigService {

    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private SysProvinceDao sysProvinceDao;
    @Autowired
    private BusTeamDao busTeamDao;
    @Autowired
    private SysInspectorDao sysInspectorDao;
    @Autowired
    private BusBuildingDao busBuildingDao;
    @Autowired
    private BusBuildingZoneDao busBuildingZoneDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private BusProjectSavingDao busProjectSavingDao;
    @Autowired
    private SysAuthenticatorDao sysAuthenticatorDao;
    @Autowired
    private ProConfigDao proConfigDao;
    @Autowired
    private SysSystemDao sysSystemDao;

    /**
     * 获取当前系统时间
     */
    private Date getNowTime(){
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = null;
        try {
            nowTime = fdate.parse(fdate.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowTime;
    }

    /**
     * 查询全部项目表信息
     */
    @Override
    public List<ProjectAssessment> queryAllInfo(){
        return busProjectDao.queryAllProjInfo();
    }

    /**
     * 新增项目配置信息和节能改造信息
     */
    @Override
    public ServiceResult addProjConfig(ProjectConfigInfo projectConfigInfo){
        String id = UuidUtil.create32();
        BusProject busProject = new BusProject();

        busProject.setId(id);
        busProject.setName(projectConfigInfo.getProjectName());
        busProject.setPhones(projectConfigInfo.getPhones());
        busProject.setBuildingId(projectConfigInfo.getBuildingId());
        busProject.setBuildingName(projectConfigInfo.getBuildingName());
        busProject.setBuildingArea(projectConfigInfo.getBuildingArea());
        busProject.setBuildingLocal(projectConfigInfo.getBuildingLocal());
        busProject.setBuildingCoordinate(projectConfigInfo.getBuildingCoordinate());
        busProject.setBuildingAge(projectConfigInfo.getBuildingAge());
        busProject.setBuildingZoneId(projectConfigInfo.getBuildingZoneId());

        busProject.setUserId(projectConfigInfo.getUserId());
        busProject.setProvinceId(projectConfigInfo.getProvinceId());
        busProject.setCityId(projectConfigInfo.getCityId());
        busProject.setCode(projectConfigInfo.getCode());
        busProject.setCreateTime(this.getNowTime());
        busProject.setEquipmentAge(projectConfigInfo.getEquipmentAge());
        busProject.setEnergyPotential(projectConfigInfo.getEnergyPotential());
        busProject.setOnlineTeamId(projectConfigInfo.getOnlineTeamId());
        busProject.setOfflineTeamId(projectConfigInfo.getOfflineTeamId());
        busProject.setType(projectConfigInfo.getType());

        busProject.setInspectIds(projectConfigInfo.getInspectIds());
        busProject.setSystemIds(projectConfigInfo.getSystemIds());
        busProject.setOnline(1);
        busProject.setReceive(0);
//        busProject.setOnline(projectConfigInfo.getOnline());
//        busProject.setReceive(projectConfigInfo.getReceive());
        busProject.setSaveSign(projectConfigInfo.getSaveSign());

        int i = busProjectDao.insertSelective(busProject);

        if(projectConfigInfo.getSaveSign() == 1){
            BusProjectSaving busProjectSaving = new BusProjectSaving();

            busProjectSaving.setId(UuidUtil.create32());
            busProjectSaving.setProjectId(id);
            busProjectSaving.setSaveContent(projectConfigInfo.getSaveContent());
            busProjectSaving.setSaveCost(projectConfigInfo.getSaveCost());
            busProjectSaving.setSaveShare(projectConfigInfo.getSaveShare());

            busProjectSaving.setSaveMethod(projectConfigInfo.getSaveMethod());
            busProjectSaving.setSaveEstimate(projectConfigInfo.getSaveEstimate());
            busProjectSaving.setSaveCostId(projectConfigInfo.getSaveCostId());
            busProjectSaving.setSaveEnergyId(projectConfigInfo.getSaveEnergyId());

            busProjectSavingDao.insertSelective(busProjectSaving);
        }
        return new ServiceResult(i, id);
    }

    /**
     * 修改项目配置信息
     */
    @Override
    public int updateProjConfig(ProjectConfigInfo projectConfigInfo){
        BusProject busProject = new BusProject();

        busProject.setId(projectConfigInfo.getId());
        busProject.setName(projectConfigInfo.getProjectName());
        busProject.setPhones(projectConfigInfo.getPhones());
        busProject.setBuildingId(projectConfigInfo.getBuildingId());
        busProject.setBuildingName(projectConfigInfo.getBuildingName());
        busProject.setBuildingArea(projectConfigInfo.getBuildingArea());
        busProject.setBuildingLocal(projectConfigInfo.getBuildingLocal());
        busProject.setBuildingCoordinate(projectConfigInfo.getBuildingCoordinate());
        busProject.setBuildingAge(projectConfigInfo.getBuildingAge());
        busProject.setBuildingZoneId(projectConfigInfo.getBuildingZoneId());

        busProject.setUserId(projectConfigInfo.getUserId());
        busProject.setProvinceId(projectConfigInfo.getProvinceId());
        busProject.setCityId(projectConfigInfo.getCityId());
        busProject.setCode(projectConfigInfo.getCode());
        busProject.setCreateTime(projectConfigInfo.getCreateTime());
        busProject.setEquipmentAge(projectConfigInfo.getEquipmentAge());
        busProject.setEnergyPotential(projectConfigInfo.getEnergyPotential());
        busProject.setOnlineTeamId(projectConfigInfo.getOnlineTeamId());
        busProject.setOfflineTeamId(projectConfigInfo.getOfflineTeamId());
        busProject.setType(projectConfigInfo.getType());

        busProject.setInspectIds(projectConfigInfo.getInspectIds());
        busProject.setSystemIds(projectConfigInfo.getSystemIds());
        busProject.setOnline(projectConfigInfo.getOnline());
        busProject.setReceive(projectConfigInfo.getReceive());
        busProject.setSaveSign(projectConfigInfo.getSaveSign());

        if (busProject.getSaveSign() == 0){
            busProjectSavingDao.deleteByPrimaryKey(projectConfigInfo.getSavingId());
        }else {
            BusProjectSaving busProjectSaving = new BusProjectSaving();

            if (projectConfigInfo.getSavingId() == null) {
                busProjectSaving.setId(UuidUtil.create32());
                busProjectSaving.setProjectId(projectConfigInfo.getProjectId());
                busProjectSaving.setSaveContent(projectConfigInfo.getSaveContent());
                busProjectSaving.setSaveCost(projectConfigInfo.getSaveCost());
                busProjectSaving.setSaveShare(projectConfigInfo.getSaveShare());

                busProjectSaving.setSaveMethod(projectConfigInfo.getSaveMethod());
                busProjectSaving.setSaveEstimate(projectConfigInfo.getSaveEstimate());
                busProjectSaving.setSaveCostId(projectConfigInfo.getSaveCostId());
                busProjectSaving.setSaveEnergyId(projectConfigInfo.getSaveEnergyId());

                busProjectSavingDao.insertSelective(busProjectSaving);
            }else {
                busProjectSaving.setId(projectConfigInfo.getSavingId());
                busProjectSaving.setProjectId(projectConfigInfo.getProjectId());
                busProjectSaving.setSaveContent(projectConfigInfo.getSaveContent());
                busProjectSaving.setSaveCost(projectConfigInfo.getSaveCost());
                busProjectSaving.setSaveShare(projectConfigInfo.getSaveShare());

                busProjectSaving.setSaveMethod(projectConfigInfo.getSaveMethod());
                busProjectSaving.setSaveEstimate(projectConfigInfo.getSaveEstimate());
                busProjectSaving.setSaveCostId(projectConfigInfo.getSaveCostId());
                busProjectSaving.setSaveEnergyId(projectConfigInfo.getSaveEnergyId());
                busProjectSavingDao.updateByPrimaryKeySelective(busProjectSaving);
            }
        }
        return busProjectDao.updateByPrimaryKeySelective(busProject);
    }

    /**
     * 获取省份、城市及其code信息
     */
    @Override
    public List<PositionInfo> queryProvinceInfo(){
        return sysProvinceDao.queryProvinceInfo();
    }

    /**
     * 处理省份、城市及其code信息的格式
     */
    @Override
    public List<Map<String,Object>> processingFormat(){
        List<Map<String,Object>> data = new ArrayList<>();
        List<PositionInfo> positionInfoList = sysProvinceDao.queryProvinceInfo();
        Map<String,List<PositionInfo>> resultMap = new HashMap<>(16);
        /*
        将所有位置信息数据通过省份ID分为34组，存于resultMap中
         */
        for(PositionInfo positionInfo : positionInfoList){
            if (resultMap.containsKey(positionInfo.getProId())){
                resultMap.get(positionInfo.getProId()).add(positionInfo);
            }else {
                List<PositionInfo> list = new ArrayList<>();
                list.add(positionInfo);
                resultMap.put(positionInfo.getProId(),list);
            }
        }
        /*
        对每组数据进行进一步格式处理
         */
        for(String key : resultMap.keySet()){
            Map<String,Object> proMap = new HashMap<>(16);
            proMap.put("value",resultMap.get(key).get(0).getProId());
            proMap.put("label",resultMap.get(key).get(0).getProName());
            List<Map<String,String>> cityList = new ArrayList<>();
            for(PositionInfo positionInfo : resultMap.get(key)){
                Map<String,String> cityMap = new HashMap<>(16);
                cityMap.put("value",positionInfo.getCityId());
                cityMap.put("label",positionInfo.getCityName());
                cityMap.put("coding",positionInfo.getCoding());
                cityList.add(cityMap);
            }
            proMap.put("city",cityList);
            data.add(proMap);
        }
        return data;
    }

//    /**
//     * 根据省份ID获取城市信息及其code
//     */
//    @Override
//    public List<Map<String,Object>> queryCityInfo(String provinceId){
//        return sysCityDao.queryCityInfo(provinceId);
//    }

    /**
     * 获取线下团队信息
     */
    @Override
    public List<BusTeam> queryOutTeamInfo(){
        return busTeamDao.queryOutTeamInfo();
    }

    /**
     * 获取线上团队信息
     */
    @Override
    public List<BusTeam> queryOnTeamInfo(){
        return busTeamDao.queryOnTeamInfo();
    }

    /**
     * 获取巡查员信息
     */
    @Override
    public List<SysInspector> queryInspectorInfo(){
        return sysInspectorDao.queryInspectorInfo();
    }

    /**
     * 获取建筑类型信息
     */
    @Override
    public List<BusBuilding> queryBuildingInfo(){
        return busBuildingDao.queryBuildingInfo();
    }

    /**
     * 获取建筑分区(气候分区)信息
     */
    @Override
    public List<BusBuildingZone> queryBuildZoneInfo(){
        return busBuildingZoneDao.queryBuildZoneInfo();
    }

    /**
     * 获取业主信息
     */
    @Override
    public List<SysUser> queryOwnerInfo(){
        return sysUserDao.queryOwnerInfo();
    }

    /**
     * 查询项目简要信息
     */
    @Override
    public List<ProjectBriefInfo> queryBriefInfo(Integer online){
        return busProjectDao.queryBriefInfo(online);
    }

    /**
     * 新增建筑类型
     */
    @Override
    public int insertBuildType(BusBuilding busBuilding){
        busBuilding.setId(UuidUtil.create32());
        return busBuildingDao.insert(busBuilding);
    }

    /**
     * 修改建筑类型
     */
    @Override
    public int updateBuildType(BusBuilding busBuilding){
        return busBuildingDao.updateByPrimaryKey(busBuilding);
    }

    /**
     * 删除建筑类型
     */
    @Override
    public int deleteBuildType(BusBuilding busBuilding){
        return busBuildingDao.deleteByPrimaryKey(busBuilding);
    }

    /**
     * 新增建筑分区(气候分区)
     */
    @Override
    public int insertBuildZone(BusBuildingZone busBuildingZone){
        busBuildingZone.setId(UuidUtil.create32());
        return busBuildingZoneDao.insert(busBuildingZone);
    }

    /**
     * 修改建筑分区(气候分区)
     */
    @Override
    public int updateBuildZone(BusBuildingZone busBuildingZone){
        return busBuildingZoneDao.updateByPrimaryKey(busBuildingZone);
    }

    /**
     * 删除建筑分区(气候分区)
     */
    @Override
    public int deleteBuildZone(BusBuildingZone busBuildingZone){
        return busBuildingZoneDao.deleteByPrimaryKey(busBuildingZone);
    }

    /**
     * 查询认定员信息
     */
    @Override
    public List<SysAuthenticator> queryAuthenticator(){
        return sysAuthenticatorDao.queryAuthenticator();
    }

    /**
     * 项目表、节能改造表联查编辑回显
     */
    @Override
    public List<ProjectConfigInfo> queryProjectConfigInfo(String projectId){
        return proConfigDao.queryProjectConfigInfo(projectId);
    }

    /**
     * 删除项目信息（谨慎！）
     *
     */
    @Override
    public int deleteAllByProId(String projectId){
        return proConfigDao.deleteAllByProId(projectId);
    }

    /**
     * 查询系统类型
     */
    @Override
    public List<SysSystem> querySystemType(){
        return sysSystemDao.querySysSystemInfo();
    }

    /**
     * 新增系统类型
     */
    @Override
    public int addSysSystem(SysSystem sysSystem){
        sysSystem.setId(UuidUtil.create32());
        return sysSystemDao.insertSelective(sysSystem);
    }

    /**
     * 删除系统类型
     */
    @Override
    public int deleteSysSystem(String systemId){
        return sysSystemDao.deleteByPrimaryKey(systemId);
    }

    /**
     * 修改系统类型
     */
    @Override
    public int updateSysSystem(SysSystem sysSystem){
        return sysSystemDao.updateByPrimaryKeySelective(sysSystem);
    }
    /**
     * 修改系统类型
     */
    @Override
    public int updateProjectReceiveAndOnline(ProjectConfigInfo projectConfigInfo){
        BusProject busProject = new BusProject();
        busProject.setId(projectConfigInfo.getId());
        busProject.setOnline(projectConfigInfo.getOnline());
        busProject.setReceive(projectConfigInfo.getReceive());
        return busProjectDao.updateByPrimaryKeySelective(busProject);
    }

}
