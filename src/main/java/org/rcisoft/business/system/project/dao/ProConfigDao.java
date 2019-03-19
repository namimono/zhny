package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.rcisoft.business.system.project.entity.ProjectConfigInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/18 8:54
 **/
@Repository
public interface ProConfigDao extends Mapper<ProjectConfigInfo> {

    /**
     * 项目表、节能改造表联查编辑回显
     */
    @Select("SELECT \n" +
            "a.id,a.name AS 'projectName',a.phones,a.building_id AS 'buildingId',\n" +
            "a.building_area AS 'buildingArea',a.building_local AS 'buildingLocal',\n" +
            "a.building_coordinate AS 'buildingCoordinate',a.building_zone_id AS 'buildingZoneId',\n" +
            "a.user_id AS 'userId',a.province_id AS 'provinceId',a.city_id AS 'cityId',a.code,\n" +
            "a.building_age AS 'buildingAge',a.create_time AS 'createTime',a.equipment_age AS 'equipmentAge',\n" +
            "a.energy_potential AS 'energyPotential',a.online_team_id AS 'onlineTeamId',\n" +
            "a.offline_team_id AS 'offlineTeamId',a.type,a.inspect_ids AS 'inspectIds',\n" +
            "a.system_ids AS 'systemIds',a.online,a.receive,a.save_sign AS 'saveSign',\n" +
            "b.id AS 'savingId',b.project_id AS 'projectId',b.save_content AS 'saveContent',\n" +
            "b.save_cost AS 'saveCost',b.save_share AS 'saveShare',b.save_method AS 'saveMethod',\n" +
            "b.save_estimate AS 'saveEstimate',b.save_cost_id AS 'saveCostId',b.save_energy_id AS 'saveEnergyId'\n" +
            "FROM \n" +
            "bus_project a \n" +
            "LEFT JOIN \n" +
            "bus_project_saving b \n" +
            "ON\n" +
            "a.id = b.project_id\n" +
            "WHERE \n" +
            "a.id = #{projectId};")
    @ResultType(ProjectConfigInfo.class)
    List<ProjectConfigInfo> queryProjectConfigInfo(@Param("projectId") String projectId);

    /**
     * 删除项目信息（谨慎！）
     *
     */
    @Options(statementType = StatementType.CALLABLE)
    @Select("call delete_AllByProId(#{projectId})")
    String deleteAllByProId(@Param("projectId") String projectId);

}
