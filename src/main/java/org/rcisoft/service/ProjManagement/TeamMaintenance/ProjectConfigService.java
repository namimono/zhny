package org.rcisoft.service.ProjManagement.TeamMaintenance;

import org.rcisoft.entity.BusBuilding;
import org.rcisoft.entity.BusBuildingZone;
import org.rcisoft.entity.BusProject;

import java.util.List;
import java.util.Map;

/**
 * Created by 土豆儿 on 2019-3-4
 */
public interface ProjectConfigService {



    /**
     * 获取所有线上团队信息及团队负责人信息
     */
    List<Map<String,Object>> queryAllOnTeamInfo();
    /**
     * 获取所有线下团队信息及团队负责人信息
     */
    List<Map<String,Object>> queryAllOutTeamInfo();

    /**
     * 获取关于项目的所有信息
     */
    List<Map<String,Object>> queryAllProjInfo();
}
