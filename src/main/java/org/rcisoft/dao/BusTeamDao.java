package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTeam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:21
 **/
@Repository
public interface BusTeamDao extends Mapper<BusTeam> {

    /**
     * 获取线下团队信息
     */
    @Select("SELECT * FROM bus_team WHERE type = '2';")
    @ResultType(BusTeam.class)
    List<BusTeam> queryOutTeamInfo();

    /**
     * 获取线上团队信息
     */
    @Select("SELECT * FROM bus_team WHERE type = '1';")
    List<BusTeam> queryOnTeamInfo();

    /**
     * 获取所有线上团队信息及团队负责人信息
     * @return
     */
    @Select("SELECT * FROM bus_team as bt right JOIN sys_principal as sp on bt.principal_id = sp.id where bt.type = 1")
    List<BusTeam> queryAllOnTeamInfo();

    /**
     * 获取所有线下团队信息及团队负责人信息
     * @return
     */
    @Select("SELECT * FROM bus_team as bt right JOIN sys_principal as sp on bt.principal_id = sp.id where bt.type = 2")
    List<BusTeam> queryAllOutTeamInfo();
}
