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
     * 新增室内环境信息
     */
    @Override
    public int addIndoorInfo(BusIndoor busIndoor){
        busIndoor.setId(UuidUtil.create32());
        return busIndoorDao.insertSelective(busIndoor);
    }

    /**
     * 删除室内环境信息
     */
    @Override
    public int deleteIndoorInfo(String indoorId){
        return busIndoorDao.deleteByPrimaryKey(indoorId);
    }

    /**
     * 修改室内环境信息
     */
    @Override
    public int updateIndoorInfo(BusIndoor busIndoor){
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
     * 新增室内环境参数
     */
    @Override
    public int addIndoorParamInfo(BusIndoorParam busIndoorParam){
        busIndoorParam.setId(UuidUtil.create32());
        return busIndoorParamDao.insertSelective(busIndoorParam);
    }

    /**
     * 删除室内环境参数
     */
    @Override
    public int deleteIndoorParamInfo(String indoorParamId){
        return busIndoorParamDao.deleteByPrimaryKey(indoorParamId);
    }

    /**
     * 修改室内环境参数
     */
    @Override
    public int updateIndoorParamInfo(BusIndoorParam busIndoorParam){
        return busIndoorParamDao.updateByPrimaryKeySelective(busIndoorParam);
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
