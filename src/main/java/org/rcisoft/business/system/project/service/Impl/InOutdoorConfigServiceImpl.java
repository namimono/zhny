package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.service.InOutdoorConfigService;
import org.rcisoft.dao.BusIndoorDao;
import org.rcisoft.dao.BusIndoorParamDao;
import org.rcisoft.dao.BusOutdoorDao;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;
import org.rcisoft.entity.BusOutdoor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/11 16:44
 **/
@Service
public class InOutdoorConfigServiceImpl implements InOutdoorConfigService {

    @Autowired
    private BusIndoorDao busIndoorDao;
    @Autowired
    private BusIndoorParamDao busIndoorParamDao;
    @Autowired
    private BusOutdoorDao busOutdoorDao;

    /**
     * 新增室内环境信息和室内环境参数
     */
    @Override
    public int addIndoorInfo(BusIndoor busIndoor,BusIndoorParam busIndoorParam){
        String id = UuidUtil.create32();
        busIndoor.setId(id);
        busIndoorParam.setId(UuidUtil.create32());
        busIndoorParam.setIndoorId(id);
        busIndoorDao.insertSelective(busIndoor);
        return busIndoorParamDao.insertSelective(busIndoorParam);
    }

    /**
     * 删除室内环境信息（同时删除室内环境参数）
     */
    @Override
    public int deleteIndoorInfo(String indoorId){
        Example example = new Example(BusIndoorParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indoorId",indoorId);
        busIndoorParamDao.deleteByExample(example);
        return busIndoorDao.deleteByPrimaryKey(indoorId);
    }

    /**
     * 修改室内环境信息和室内环境参数
     */
    @Override
    public int updateIndoorInfo(BusIndoor busIndoor,BusIndoorParam busIndoorParam){
        busIndoorParamDao.updateByPrimaryKeySelective(busIndoorParam);
        return busIndoorDao.updateByPrimaryKeySelective(busIndoor);
    }

    /**
     * 查询室内环境信息
     */
    @Override
    public List<BusIndoor> queryIndoorInfo(String projectId){
        Example example = new Example(BusIndoor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        return busIndoorDao.selectByExample(example);
    }

    /**
     * 查询室内环境参数
     */
    @Override
    public List<BusIndoorParam> queryIndoorParamInfo(String indoorId){
        Example example = new Example(BusIndoorParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indoorId",indoorId);
        return busIndoorParamDao.selectByExample(example);
    }

    /**
     * 新增室外配置
     */
    @Override
    public int addOutdoorInfo(BusOutdoor busOutdoor){
        busOutdoor.setId(UuidUtil.create32());
        return busOutdoorDao.insertSelective(busOutdoor);
    }

    /**
     * 修改室外配置
     */
    @Override
    public int updateOutdoorInfo(BusOutdoor busOutdoor){
        return busOutdoorDao.updateByPrimaryKeySelective(busOutdoor);
    }

    /**
     * 查询室外配置
     */
    @Override
    public List<BusOutdoor> queryOutdoorInfo(String projectId){
        Example example = new Example(BusIndoor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        return busOutdoorDao.selectByExample(example);
    }

}
