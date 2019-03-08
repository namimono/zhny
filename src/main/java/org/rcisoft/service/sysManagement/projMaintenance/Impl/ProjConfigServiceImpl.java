package org.rcisoft.service.sysManagement.projMaintenance.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.dao.*;
import org.rcisoft.dao.sysManagement.projMaintenance.ProjConfigDao;
import org.rcisoft.entity.BusProject;
import org.rcisoft.service.sysManagement.projMaintenance.ProjConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:39
 **/

@Service
//@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
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
    public List<Map<String,Object>> queryAllInfo(){
        return busProjectDao.queryAllInfo();
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
    public List<Map<String,Object>> queryProvinceInfo(){
        return sysProvinceDao.queryProvinceInfo();
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
    public List<Map<String,Object>> queryOutTeamInfo(){
        return busTeamDao.queryOutTeamInfo();
    }

    /**
     * 获取线上团队信息
     */
    @Override
    public List<Map<String,Object>> queryOnTeamInfo(){
        return busTeamDao.queryOnTeamInfo();
    }

    /**
     * 获取巡查员信息
     */
    @Override
    public List<Map<String,Object>> queryInspectorInfo(){
        return sysInspectorDao.queryInspectorInfo();
    }

    /**
     * 获取建筑类型信息
     */
    @Override
    public List<Map<String,Object>> queryBuildingInfo(){
        return busBuildingDao.queryBuildingInfo();
    }

    /**
     * 获取建筑分区(气候分区)信息
     */
    @Override
    public List<Map<String,Object>> queryBuildZoneInfo(){
        return busBuildingZoneDao.queryBuildZoneInfo();
    }

    /**
     * 获取业主信息
     */
    @Override
    public List<Map<String,Object>> queryOwnerInfo(){
        return sysUserDao.queryOwnerInfo();
    }

    /**
     * 查询项目简要信息
     */
    @Override
    public List<Map<String,Object>> queryBriefInfo(){
        return busProjectDao.queryBriefInfo();
    }
}
