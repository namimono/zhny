package org.rcisoft.business.system.project.service.Impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 10:45
 **/
@Service
public class TopologicalServiceImpl implements TopologicalService {

    /** url */
    @Value("${location.url}")
    String url;
    /** 根路径 */
    @Value("${location.path}")
    String path;
    /** 设备图片文件夹 */
    @Value("${location.topology}")
    String topology;

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
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int addTopologyNode(MultipartFile file, String proId){
        // 返回值
        int result = 0;
        // 后缀
        String suffix = "";
        String[] fileNameArray = StringUtils.split(file.getOriginalFilename(), ".");
        if (fileNameArray.length > 1) {
            suffix = "." + fileNameArray[fileNameArray.length - 1];
        }
        // 新的文件名
        String fileName = UuidUtil.create32() + suffix;

        BusTopologyNode busTopologyNode = new BusTopologyNode();
        busTopologyNode.setId(UuidUtil.create32());
        busTopologyNode.setProjectId(proId);
        busTopologyNode.setUrl(fileName);

        try {
            result = busTopologyNodeDao.insert(busTopologyNode);
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + topology + "/" + proId + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        busTopologyNode.setId(UuidUtil.create32());
        return result;
    }

    /**
     * 删除拓扑图节点图片信息
     */
    @Override
    public int deleteTopologyNode(String nodeId){
        BusTopologyNode busTopologyNode = busTopologyNodeDao.selectByPrimaryKey(nodeId);
        File file = new File(path + topology + "/" + busTopologyNode.getProjectId() + "/" + busTopologyNode.getUrl());
        if (file.exists()) {
            file.delete();
        }
        return busTopologyNodeDao.deleteByPrimaryKey(nodeId);
    }

    /**
     * 查询拓扑图节点图片信息
     */
    @Override
    public List<BusTopologyNode> queryTopologyNode(String proId){
        List<BusTopologyNode> busTopologyNodeList = busTopologyNodeDao.queryTopologyNode(proId);
        busTopologyNodeList.forEach(busTopologyNode -> {
            String myUrl = url + topology + "/" + busTopologyNode.getProjectId() + "/" + busTopologyNode.getUrl();
            myUrl=myUrl.replace("\\", "/");
            busTopologyNode.setUrl(myUrl);
        });
        return busTopologyNodeList;
    }

    /**
     * 根据图片ID查询设备信息
     */
    @Override
    public List<BusDevice> queryDeviceByPicId(String picId, String sysId){
        return busTopologyNodeDao.queryDeviceByPicId(picId, sysId);
    }
}
