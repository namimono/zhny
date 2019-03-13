package org.rcisoft.business.system.team.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.team.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/12.
 * 系统管理--团队维护
 */
@Repository
public interface TeamDao {

    /**
     * 查询线上、线下团队列表
     * @param type 1：线上，2：线下
     * @return 团队列表
     */
    @Select("<script>" +
            "select t.*, p.name as principalName " +
            "from bus_team t " +
            "left join sys_principal p " +
                "on t.principal_id = p.id " +
            "where t.type = #{type} " +
            "order by t.create_time desc" +
            "</script>")
    @ResultType(Team.class)
    List<Team> queryTeamByType(@Param("type") Integer type);

    /**
     * 根据id查询线上、线下团队
     * @param id 团队id
     * @return 团队对象
     */
    @Select("<script>" +
            "select t.*, p.name as principalName " +
            "from bus_team t " +
            "left join sys_principal p " +
                "on t.principal_id = p.id " +
            "where t.id = #{id}</script>")
    @ResultType(Team.class)
    Team queryTeamById(@Param("id") String id);

}
