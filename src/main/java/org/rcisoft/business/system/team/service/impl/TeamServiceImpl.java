package org.rcisoft.business.system.team.service.impl;

import com.github.pagehelper.PageInfo;
import org.rcisoft.base.result.PageUtil;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.team.dao.TeamDao;
import org.rcisoft.business.system.team.entity.Team;
import org.rcisoft.business.system.team.service.TeamService;
import org.rcisoft.dao.BusTeamDao;
import org.rcisoft.dao.SysPrincipalDao;
import org.rcisoft.entity.BusTeam;
import org.rcisoft.entity.SysPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by JiChao on 2019/3/12.
 * 系统管理--团队维护
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDao teamDao;

    @Autowired
    BusTeamDao busTeamDao;

    @Autowired
    SysPrincipalDao sysPrincipalDao;

    @Override
    public PageInfo<Team> queryTeamByType(Integer type) {
        List<Team> list = teamDao.queryTeamByType(type);
        return PageUtil.pageResult(list);
    }

    @Override
    public Team queryTeamById(String id) {
        return teamDao.queryTeamById(id);
    }

    @Override
    public Integer insertTeam(BusTeam busTeam) {
        busTeam.setId(UuidUtil.create32());
        busTeam.setCreateTime(new Date());
        return busTeamDao.insert(busTeam);
    }

    @Override
    public Integer updateTeam(BusTeam busTeam) {
        return busTeamDao.updateByPrimaryKey(busTeam);
    }

    @Override
    public Integer deleteTeamById(String id) {
        return busTeamDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysPrincipal> queryPrincipalList() {
        return sysPrincipalDao.queryPrincipalList();
    }
}
