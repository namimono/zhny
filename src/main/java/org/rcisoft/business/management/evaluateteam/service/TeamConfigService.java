package org.rcisoft.business.management.evaluateteam.service;

import org.rcisoft.business.management.evaluateteam.entity.BusTeamAssessment;

import java.util.List;

/**
 * Created by Minghui Xu on 2019-3-4
 */
public interface TeamConfigService {



    /**
     * 获取所有线上团队信息及团队负责人信息
     */
    List<BusTeamAssessment> queryAllOnTeamInfo();
    /**
     * 获取所有线下团队信息及团队负责人信息
     */
    List<BusTeamAssessment> queryAllOutTeamInfo();


}
