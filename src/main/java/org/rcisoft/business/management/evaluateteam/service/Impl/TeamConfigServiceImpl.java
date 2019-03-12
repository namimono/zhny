package org.rcisoft.business.management.evaluateteam.service.Impl;

import org.rcisoft.business.management.evaluateteam.dao.BusTeamAssessmentDao;
import org.rcisoft.business.management.evaluateteam.entity.BusTeamAssessment;
import org.rcisoft.business.management.evaluateteam.service.TeamConfigService;
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
public class TeamConfigServiceImpl implements TeamConfigService {

    @Autowired
    private BusTeamAssessmentDao busTeamAssessmentDao;

    /**
     * 获取所有线上团队信息和团队负责人信息
     */
    @Override
    public List<BusTeamAssessment> queryAllOnTeamInfo() {
        return busTeamAssessmentDao.queryAllOnTeamInfo();
    }
    /**
     * 获取所有线上团队信息和团队负责人信息
     */
    @Override
    public List<BusTeamAssessment> queryAllOutTeamInfo() {
        return busTeamAssessmentDao.queryAllOutTeamInfo();
    }


}
