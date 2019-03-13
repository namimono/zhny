package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;
import org.rcisoft.entity.BusProject;
import org.rcisoft.business.system.project.entity.ProjectBriefInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 11:32
 **/
@Repository
public interface BusProjectDao extends Mapper<BusProject> {

    /**
     * 查询全部项目表信息
     */
    @Select("SELECT * FROM bus_project;")
    @ResultType(BusProject.class)
    List<BusProject> queryAllInfo();

    /**
     * 查询项目简要信息
     */
    @Select("SELECT a.id,a.name,a.building_local,a.building_area,b.username,c.`name` AS 'buildTypeName' \n" +
            "FROM bus_project a,sys_user b,bus_building c \n" +
            "WHERE a.user_id = b.id AND a.building_id = c.id;")
    @ResultType(ProjectBriefInfo.class)
    List<ProjectBriefInfo> queryBriefInfo();

    /**
     *  查询关于项目的所有信息
     */
    @Select("select * from bus_project as bp right join bus_project_saving as bps on bp.id = bps.project_id")
    @ResultType(ProjectAssessment.class)
    List<ProjectAssessment> queryAllProjInfo();


}
