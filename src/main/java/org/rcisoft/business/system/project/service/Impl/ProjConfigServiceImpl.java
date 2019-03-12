package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.business.system.project.entity.ProjectBriefInfo;
import org.rcisoft.dao.*;
import org.rcisoft.business.system.project.dao.ProjConfigDao;
import org.rcisoft.entity.*;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.rcisoft.business.system.project.entity.PositionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:39
 **/

@Service
public class ProjConfigServiceImpl implements ProjConfigService {

    @Autowired
    private ProjConfigDao projConfigDao;
    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private SysProvinceDao sysProvinceDao;
    @Autowired
    private SysCityDao sysCityDao;
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

    /**
     * 查询全部项目表信息
     */
    @Override
    public List<ProjectAssessment> queryAllInfo(){
        return busProjectDao.queryAllProjInfo();
    }

    /**
     * 新增项目配置信息
     */
    @Override
    public String addProjConfig(BusProject busProject){

//        SimpleDateFormat abc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
//
//        try {
//            busProject.setCreateTime(abc.parse(abc.format(new Date())));
//            busProject.setBuildingAge(sdf.parse(sdf.format(busProject.getBuildingAge())));
//            busProject.setEquipmentAge(fmt.parse(fmt.format(busProject.getEquipmentAge())));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        projConfigDao.setProjConfig(busProject)

        busProject.setId(UuidUtil.create32());
        busProjectDao.insertSelective(busProject);
        return busProject.getId();
    }

    /**
     * 修改项目配置信息
     */
    @Override
    public int updateProjConfig(BusProject busProject){
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
        Map<String,List<PositionInfo>> resultMap1 = resultMap;
        /*
        对每组数据进行进一步格式处理
         */
        for(String key : resultMap.keySet()){
            Map<String,Object> proMap = new HashMap<>(16);
            proMap.put("proId",resultMap.get(key).get(0).getProId());
            proMap.put("proName",resultMap.get(key).get(0).getProName());
            List<Map<String,String>> cityList = new ArrayList<>();
            for(PositionInfo positionInfo : resultMap.get(key)){
                Map<String,String> cityMap = new HashMap<>(16);
                cityMap.put("cityId",positionInfo.getCityId());
                cityMap.put("cityName",positionInfo.getCityName());
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
    public List<ProjectBriefInfo> queryBriefInfo(){
        return busProjectDao.queryBriefInfo();
    }

    /**
     * 新增建筑类型
     */
    @Override
    public int insertBuildType(BusBuilding busBuilding){
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
}
