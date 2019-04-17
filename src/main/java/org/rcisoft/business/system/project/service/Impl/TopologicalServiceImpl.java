package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.service.TopologicalService;
import org.rcisoft.dao.BusTopologyDao;
import org.rcisoft.dao.BusTopologyNodeDao;
import org.rcisoft.dao.BusTypeFirstDao;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.entity.BusTopology;
import org.rcisoft.entity.BusTopologyNode;
import org.rcisoft.entity.BusTypeFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 10:45
 **/
@Service
public class TopologicalServiceImpl implements TopologicalService {

    @Autowired
    private BusTopologyDao busTopologyDao;
    @Autowired
    private BusTopologyNodeDao busTopologyNodeDao;
    @Autowired
    private BusTypeFirstDao busTypeFirstDao;

    /**
     * 查询拓扑图信息
     */
    @Override
    public List<BusTopology> queryTopology(BusTopology busTopology){
        return busTopologyDao.queryTopology(busTopology);
    }

    /**
     * 新增拓扑图信息
     */
    @Override
    public int addTopology(BusTopology busTopology){
        busTopology.setId(UuidUtil.create32());
        return busTopologyDao.insert(busTopology);
    }

    /**
     * 删除拓扑图信息
     */
    @Override
    public int deleteTopology(BusTopology busTopology){
        return busTopologyDao.deleteByPrimaryKey(busTopology);
    }

    /**
     * 修改拓扑图信息
     */
    @Override
    public int updateTopology(BusTopology busTopology){
        return busTopologyDao.updateByPrimaryKeySelective(busTopology);
    }

    /**
     * 新增拓扑图节点图片信息
     */
    @Override
    public int addTopologyNode(BusTopologyNode busTopologyNode){
        busTopologyNode.setId(UuidUtil.create32());
        return busTopologyNodeDao.insert(busTopologyNode);
    }

    /**
     * 删除拓扑图节点图片信息
     */
    @Override
    public int deleteTopologyNode(BusTopologyNode busTopologyNode){
        return busTopologyNodeDao.deleteByPrimaryKey(busTopologyNode);
    }

    /**
     * 修改拓扑图节点图片信息
     */
    @Override
    public int upadteTopologyNode(BusTopologyNode busTopologyNode){
        return busTopologyNodeDao.updateByPrimaryKeySelective(busTopologyNode);
    }

    /**
     * 查询拓扑图节点图片信息
     */
    @Override
    public List<BusTopologyNode> queryTopologyNode(String proId){
        return busTopologyNodeDao.queryTopologyNode(proId);
    }

//    /**
//     * 根据系统ID查询设备类型
//     */
//    @Override
//    public List<BusTypeFirst> queryTypeFirstBySysId(String proId, String systemId){
//        Example example = new Example(BusTypeFirst.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("projectId",proId);
//        criteria.andEqualTo("systemId",systemId);
//        return busTypeFirstDao.selectByExample(example);
//    }

    /**
     * 根据图片ID查询设备信息
     */
    @Override
    public List<BusDevice> queryDeviceByPicId(String picId){
        return busTopologyNodeDao.queryDeviceByPicId(picId);
    }
}
