package org.rcisoft.business.management.evaluateproj.service;

import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.BusTeam;

import java.util.List;
import java.util.Map;

/**
 * Created by Minghui Xu on 2019-3-4
 */
public interface ProConfigService {





    /**
     * 获取关于项目的所有信息
     */
    List<ProjectAssessment> queryAllProjInfo();
}
