package org.rcisoft.business.system.project.service;

import org.rcisoft.entity.BusTopology;
import org.rcisoft.entity.BusTopologyNode;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 10:45
 **/

public interface TopologicalService {

    /**
     * 查询拓扑图信息
     */
    List<BusTopology> queryTopology(BusTopology busTopology);

    /**
     * 新增拓扑图信息
     */
    int addTopology(BusTopology busTopology);

    /**
     * 删除拓扑图信息
     */
    int deleteTopology(BusTopology busTopology);

    /**
     * 修改拓扑图信息
     */
    int updateTopology(BusTopology busTopology);

    /**
     * 新增拓扑图节点图片信息
     */
    int addTopologyNode(BusTopologyNode busTopologyNode);

    /**
     * 删除拓扑图节点图片信息
     */
    int deleteTopologyNode(BusTopologyNode busTopologyNode);

    /**
     * 修改拓扑图节点图片信息
     */
    int upadteTopologyNode(BusTopologyNode busTopologyNode);

    /**
     * 查询拓扑图节点图片信息
     */
    List<BusTopologyNode> queryTopologyNode(BusTopologyNode busTopologyNode);
}
