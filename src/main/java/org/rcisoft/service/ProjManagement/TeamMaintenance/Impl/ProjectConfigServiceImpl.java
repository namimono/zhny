package org.rcisoft.service.ProjManagement.TeamMaintenance.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.dao.*;
import org.rcisoft.dao.sysManagement.projMaintenance.ProjConfigDao;
import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;
import org.rcisoft.service.ProjManagement.TeamMaintenance.ProjectConfigService;
import org.rcisoft.service.sysManagement.projMaintenance.ProjConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:39
 **/

@Service
//@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class ProjectConfigServiceImpl implements ProjectConfigService {

    @Autowired
    private ProjConfigDao projConfigDao;
    @Autowired
    private BusTeamDao busTeamDao;
    @Autowired
    private BusProjectDao busProjectDao;




    /**
     * 获取所有线上团队信息和团队负责人信息
     */
    @Override
    public List<Map<String, Object>> queryAllOnTeamInfo() {
        return busTeamDao.queryAllOnTeamInfo();
    }
    /**
     * 获取所有线上团队信息和团队负责人信息
     */
    @Override
    public List<Map<String, Object>> queryAllOutTeamInfo() {
        return busTeamDao.queryAllOutTeamInfo();
    }

    /**
     * 获取关于项目的所有信息
     */
    @Override
    public List<Map<String, Object>> queryAllProjInfo() {
        return busProjectDao.queryAllProjInfo();
    }
}
