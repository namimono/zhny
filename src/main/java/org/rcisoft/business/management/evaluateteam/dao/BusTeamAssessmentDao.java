package org.rcisoft.business.management.evaluateteam.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.management.evaluateteam.entity.BusTeamAssessment;
import org.rcisoft.entity.BusTeam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by Minghui Xu
 * Time：2019/3/5 13:21
 **/
@Repository
public interface BusTeamAssessmentDao extends Mapper<BusTeam> {



    /**
     * 获取所有线上团队信息及团队负责人信息(团队评估)
     * @return
     */
    @Select("SELECT bt.id as teamId ,bt.name as teamName,bt.online_time as onlineTime,bt.introduction as introduction," +
            "bt.principal_id as principalId,bt.recommend as recommend, bt.type as type,bt.create_time as teamCreateTime," +
            "bt.resource as resource,sp.id as id,sp.name as principalName,sp.job as job,sp.job_title as jobTitle," +
            "sp.employment_time as employmentTime,sp.honor as honor,sp.create_time as createTime" +
            " FROM bus_team as bt right JOIN sys_principal as sp on bt.principal_id = sp.id where bt.type = 1")
    List<BusTeamAssessment> queryAllOnTeamInfo();

    /**
     * 获取所有线下团队信息及团队负责人信息(团队评估)
     * @return
     */
    @Select("SELECT bt.id as teamId ,bt.name as teamName,bt.online_time as onlineTime,bt.introduction as introduction," +
            "bt.principal_id as principalId,bt.recommend as recommend, bt.type as type,bt.create_time as teamCreateTime," +
            "bt.resource as resource,sp.id as id,sp.name as principalName,sp.job as job,sp.job_title as jobTitle," +
            "sp.employment_time as employmentTime,sp.honor as honor,sp.create_time as createTime" +
            " FROM bus_team as bt right JOIN sys_principal as sp on bt.principal_id = sp.id where bt.type = 2")
    List<BusTeamAssessment> queryAllOutTeamInfo();
}
