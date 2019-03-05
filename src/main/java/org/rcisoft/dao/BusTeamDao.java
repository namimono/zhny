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
    List<Map<String,Object>> queryOutTeamInfo();

    /**
     * 获取线上团队信息
     */
    @Select("SELECT * FROM bus_team WHERE type = '1';")
    List<Map<String,Object>> queryOnTeamInfo();
}
