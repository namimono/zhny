package org.rcisoft.service.ProjManagement.TeamMaintenance.Impl;

import org.rcisoft.dao.*;
import org.rcisoft.entity.BusTeam;
import org.rcisoft.service.ProjManagement.TeamMaintenance.ProjectConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by MingHui Xu
 **/

@Service
public class ProjectConfigServiceImpl implements ProjectConfigService {

    @Autowired
    private BusTeamDao busTeamDao;
    @Autowired
    private BusProjectDao busProjectDao;

    /**
     * 获取所有线上团队信息和团队负责人信息
     */
    @Override
    public List<BusTeam> queryAllOnTeamInfo() {
        return busTeamDao.queryAllOnTeamInfo();
    }
    /**
     * 获取所有线上团队信息和团队负责人信息
     */
    @Override
    public List<BusTeam> queryAllOutTeamInfo() {
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
