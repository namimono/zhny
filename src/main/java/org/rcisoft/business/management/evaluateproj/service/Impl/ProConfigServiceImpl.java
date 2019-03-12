package org.rcisoft.business.management.evaluateproj.service.Impl;

import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.business.management.evaluateproj.service.ProConfigService;
import org.rcisoft.business.system.project.service.ProjConfigService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.BusTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by MingHui Xu
 **/

@Service
public class ProConfigServiceImpl implements ProConfigService {

    @Autowired
    private BusTeamDao busTeamDao;
    @Autowired
    private BusProjectDao busProjectDao;

    /**
     * 获取关于项目的所有信息
     */
    @Override
    public List<ProjectAssessment> queryAllProjInfo() {
        return busProjectDao.queryAllProjInfo();
    }


}
