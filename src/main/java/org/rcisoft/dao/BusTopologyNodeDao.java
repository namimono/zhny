package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTopologyNode;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 11:06
 **/
@Repository
public interface BusTopologyNodeDao extends Mapper<BusTopologyNode> {

    /**
     * 查询拓扑图节点图片信息
     */
    @Select("SELECT * FROM bus_topology_node WHERE system_id = #[systemId};")
    @ResultType(BusTopologyNode.class)
    List<BusTopologyNode> queryTopologyNode(BusTopologyNode busTopologyNode);
}
