package org.rcisoft.business.management.evaluateteam.service;

import org.rcisoft.business.management.evaluateteam.entity.BusTeamAssessment;
import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.BusTeam;

import java.util.List;
import java.util.Map;

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
