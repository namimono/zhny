package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.business.monitor.intercept.entity.BusProjectParam;
import org.rcisoft.business.system.project.entity.ProjectBriefInfo;
import org.rcisoft.business.system.roleuser.entity.ProjectName;
import org.rcisoft.entity.BusProject;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/5 11:32
 **/
@Repository
public interface BusProjectDao extends Mapper<BusProject> {

    /**
     * 查出项目的Id,名称
     * @return List<ProjectName>
     */
    @Select("SELECT bus_project.id AS projectId, bus_project.name AS projectName FROM bus_project")
    List<ProjectName> listProjectName();

    /**
     * 查询全部项目表信息
     */
    @Select("SELECT * FROM bus_project;")
    @ResultType(BusProject.class)
    List<BusProject> queryAllInfo();

    /**
     * 查询线上项目简要信息
     */
    @Select("SELECT a.id as 'proId',a.name as 'proName',a.building_local as 'buildingLocal',\n" +
            "a.building_area as 'buildingArea',b.username as 'userName',c.name AS 'buildTypeName',a.online,a.receive\n" +
            "FROM bus_project a,sys_user b,bus_building c\n" +
            "WHERE a.user_id = b.id AND a.building_id = c.id AND a.online = #{online};")
    @ResultType(ProjectBriefInfo.class)
    List<ProjectBriefInfo> queryBriefInfo(Integer online);

    /**
     *  查询关于项目的所有信息(包含检定员信息)
     */
    @Select("<script>select bp.id,bp.name as projName,bb.name as buildingType,bp.building_local as buildingLocal,bp.building_area as buildingArea,bp.energy_potential as energyPotential," +
            "bp.create_time as createTime,bps.save_content as saveContent,bps.save_estimate as saveEstimate," +
            "bps.save_cost as saveCost,sac.name as saveCostName,sae.name as saveEnergyName,sac.performance as saveCostPerformance,sac.employment_time as saveCostEmploymentTime," +
            "sac.qualification as saveCostQualification,sae.qualification as saveEnergyQualification," +
            "sae.performance as saveEnergyPerformance,sae.employment_time as saveEnergyEmploymentTime " +
            "from bus_project as bp right join bus_project_saving as bps on bp.id = bps.project_id,sys_authenticator sac,sys_authenticator sae,bus_building bb" +
            " where sac.id = bps.save_cost_id and sae.id = bps.save_energy_id and bb.id = bp.building_id and bp.online = '1' and bp.receive = '1'" +
            "</script>")
    @ResultType(ProjectAssessment.class)
    List<ProjectAssessment> queryAllProjInfo();


    /**
     * 查询项目网关
     * @param id
     */
    @Select("<script>select phones from bus_project where id = #{id}</script>")
    String queryPhones(String id);


    /**
     * 查询项目参数
     * @param projectId
     * @return
     */
    @Select("<script>select bpf.name,bps.coding from bus_param_second bps,bus_param_first bpf " +
            "where bps.param_first_id = bpf.id and bps.project_id = #{projectId} </script>")
    List<BusProjectParam> queryParam(String projectId);

    /**
     * 查询城市Code
     */
    @Select("select code from bus_project where id = #{projectId}")
    String queryCityCode(@Param("projectId") String projectId);
}
