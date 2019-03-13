package org.rcisoft.business.system.team.service;

import com.github.pagehelper.PageInfo;
import org.rcisoft.business.system.team.entity.Team;
import org.rcisoft.entity.BusTeam;
import org.rcisoft.entity.SysPrincipal;

import java.util.List;

/**
 * Created by JiChao on 2019/3/12.
 * 系统管理--团队维护
 */
public interface TeamService {

    /**
     * 分页查询线上、线下团队列表
     * @param type 1：线上，2：线下
     * @return 分页的团队列表
     */
    PageInfo<Team> queryTeamByType(Integer type);

    /**
     * 根据id查询线上、线下团队
     * @param id 团队id
     * @return 团队对象
     */
    Team queryTeamById(String id);

    /**
     * 新增团队
     * @param busTeam 团队对象
     * @return
     */
    Integer insertTeam(BusTeam busTeam);

    /**
     * 修改团队
     * @param busTeam 团队对象
     * @return
     */
    Integer updateTeam(BusTeam busTeam);

    /**
     * 根据id删除团队对象
     * @param id 团队id
     * @return
     */
    Integer deleteTeamById(String id);

    /**
     * 查询团队负责人id、姓名 列表
     * @return
     */
    List<SysPrincipal> queryPrincipalList();

}
