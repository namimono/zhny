package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.InOutDoorConfigDao;
import org.rcisoft.business.system.project.entity.IndoorContainParam;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/11 16:44
 **/
@Service
public class InOutdoorConfigServiceImpl implements InOutdoorConfigService {

    @Autowired
    private InOutDoorConfigDao inOutDoorConfigDao;
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
    public ServiceResult addIndoorInfo(List<IndoorContainParam> list,String proId){
        //判断新增室内环境信息是否重复
        for (IndoorContainParam indoorContainParam : list){
            int flag = inOutDoorConfigDao.queryIndoorRepeatNum(indoorContainParam.getBusIndoor().getFloor(),indoorContainParam.getBusIndoor().getDoor(),proId);
            if(flag > 0){
                return new ServiceResult(flag,"error");
            }
        }

        //批新增室内环境信息和室内环境参数
        List<BusIndoorParam> addIndoorParamList = new ArrayList<>();
        list.forEach(indoorContainParam -> {
            String id = UuidUtil.create32();
            indoorContainParam.getBusIndoor().setId(id);
            indoorContainParam.getIndoorParams().forEach(busIndoorParam -> {
                busIndoorParam.setId(UuidUtil.create32());
                busIndoorParam.setIndoorId(id);
                addIndoorParamList.add(busIndoorParam);
            });
        });
        if (addIndoorParamList.size() > 0){
            busIndoorParamDao.insertListUseAllCols(addIndoorParamList);
        }
        return new ServiceResult(busIndoorDao.insertSelective(list.get(0).getBusIndoor()),"success");
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
    public int updateIndoorInfo(List<IndoorContainParam> list){
        //批新增室内环境信息和室内环境参数
        List<BusIndoorParam> updateIndoorParamList = new ArrayList<>();
        list.forEach(indoorContainParam -> {
            String id = UuidUtil.create32();
            indoorContainParam.getBusIndoor().setId(id);
            indoorContainParam.getIndoorParams().forEach(busIndoorParam -> {
                busIndoorParam.setId(UuidUtil.create32());
                busIndoorParam.setIndoorId(id);
                updateIndoorParamList.add(busIndoorParam);
            });
        });
        if (updateIndoorParamList.size() > 0){
            inOutDoorConfigDao.updateAllIndoorParam(updateIndoorParamList);
        }
        return busIndoorDao.updateByPrimaryKeySelective(list.get(0).getBusIndoor());
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
