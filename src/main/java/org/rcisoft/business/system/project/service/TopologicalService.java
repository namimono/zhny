package org.rcisoft.business.system.project.service;

import org.rcisoft.entity.BusDevice;
import org.rcisoft.entity.BusTopology;
import org.rcisoft.entity.BusTopologyNode;
import org.rcisoft.entity.BusTypeFirst;
import org.springframework.web.multipart.MultipartFile;

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
    int addTopologyNode(MultipartFile file,String proId);

    /**
     * 删除拓扑图节点图片信息
     */
    int deleteTopologyNode(String nodeId);

    /**
     * 查询拓扑图节点图片信息
     */
    List<BusTopologyNode> queryTopologyNode(String proId);

    /**
     * 根据图片ID查询设备信息
     */
    List<BusDevice> queryDeviceByPicId(String picId, String sysId);
}
