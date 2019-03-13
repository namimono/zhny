package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTopology;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 10:49
 **/
@Repository
public interface BusTopologyDao extends Mapper<BusTopology> {

    /**
     * 查询拓扑图信息
     */
    @Select("SELECT * FROM bus_topology WHERE project_id = #{projectId} AND system_id = #[systemId};")
    @ResultType(BusTopology.class)
    List<BusTopology> queryTopology(BusTopology busTopology);
}
