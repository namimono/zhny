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
import org.springframework.util.StringUtils;
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
    public ServiceResult addIndoorInfo(IndoorContainParam indoorContainParam){
        //判断新增室内环境信息是否重复
        int flag = inOutDoorConfigDao.queryIndoorRepeatNum(indoorContainParam.getBusIndoor().getFloor(),indoorContainParam.getBusIndoor().getDoor(),indoorContainParam.getBusIndoor().getProjectId());
        if(flag > 0){
            return new ServiceResult(flag,"error");
        }

        //批新增室内环境信息和室内环境参数
        List<BusIndoorParam> addIndoorParamList = new ArrayList<>();
        String id = UuidUtil.create32();
        indoorContainParam.getBusIndoor().setId(id);
        if (indoorContainParam.getIndoorParams().size() > 0) {
            indoorContainParam.getIndoorParams().forEach(busIndoorParam -> {
                busIndoorParam.setId(UuidUtil.create32());
                busIndoorParam.setIndoorId(id);
                addIndoorParamList.add(busIndoorParam);
            });
        }
        if (addIndoorParamList.size() > 0){
            busIndoorParamDao.insertListUseAllCols(addIndoorParamList);
        }
        return new ServiceResult(busIndoorDao.insertSelective(indoorContainParam.getBusIndoor()),"success");
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
    public int updateIndoorInfo(IndoorContainParam indoorContainParam){
        String indoorId = indoorContainParam.getBusIndoor().getId();
        Example example = new Example(BusIndoorParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indoorId",indoorId);
        busIndoorParamDao.deleteByExample(example);
        //批量修改室内环境信息和室内环境参数
        List<BusIndoorParam> addIndoorParamList = new ArrayList<>();
        if (indoorContainParam.getIndoorParams().size() > 0) {
            indoorContainParam.getIndoorParams().forEach(busIndoorParam -> {
                busIndoorParam.setId(UuidUtil.create32());
                busIndoorParam.setIndoorId(indoorId);
                addIndoorParamList.add(busIndoorParam);
            });
            if (addIndoorParamList.size() > 0){
                busIndoorParamDao.insertListUseAllCols(addIndoorParamList);
            }
        }
        return busIndoorDao.updateByPrimaryKey(indoorContainParam.getBusIndoor());
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
        example.setOrderByClause("type asc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indoorId",indoorId);
        return busIndoorParamDao.selectByExample(example);
    }

    /**
     * 新增室外配置
     */
    @Override
    public int addOutdoorInfo(List<BusOutdoor> busOutdoorList){
        int flag = 0;
        for (BusOutdoor busOutdoor : busOutdoorList){
            if (null == busOutdoor){ flag++; }
        }
        if (flag == busOutdoorList.size()){ return 0; }
        busOutdoorList.forEach(busOutdoor -> busOutdoor.setId(UuidUtil.create32()));
        return busOutdoorDao.insertListUseAllCols(busOutdoorList);
    }

//    /**
//     *判断一个实体类对象实例的所有成员变量是否为空
//     *@param obj 校验的类对象实例
//     *@return List
//     *@throws Exception
//     */
//
//    public static  List<String> isObjectFieldEmpty(Object obj) throws Exception {
//        Class<?> clazz=obj.getClass();  //得到类对象
//        Field[] fs=clazz.getDeclareFields(); //得到属性集合
//        List<String> list=new ArrayList<String>();
//        for(Field field:fs){            //遍历属性
//            field.setAccessible(true); //设置属性是可以访问的（私有的也可以）
//            if(field.get(obj)==null||field.get(obj)==""||"null".equalsIngnoreCase(String)field.get(obj))){
//                String name=(String)field.getName();
//                list.add(name);
//            }
//        }
//        return list;
//    }

    /**
     * 修改室外配置
     */
    @Override
    public int updateOutdoorInfo(List<BusOutdoor> busOutdoorList){
        int flag = 0;
        for (BusOutdoor busOutdoor : busOutdoorList){
            if (busOutdoor == null){ flag++; }
        }
        if (flag == busOutdoorList.size()){ return 0; }
        return inOutDoorConfigDao.updateAllOutdoorInfo(busOutdoorList);
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
