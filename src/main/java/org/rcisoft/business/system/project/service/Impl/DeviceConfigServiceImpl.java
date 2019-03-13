package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
import org.rcisoft.dao.*;
import org.rcisoft.entity.BusDevice;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamFixed;
import org.rcisoft.entity.BusTypeFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:13
 **/
@Service
public class DeviceConfigServiceImpl implements DeviceConfigService {

    @Autowired
    private BusDeviceDao busDeviceDao;
    @Autowired
    private BusTypeSecondDao busTypeSecondDao;
    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private BusParamFixedDao busParamFixedDao;

    /**
     * 新增设备配置信息
     */
    @Override
    public int addDeviceConfigInfo(BusDevice busDevice){
        busDevice.setId(UuidUtil.create32());
        return busDeviceDao.insertSelective(busDevice);
    }

    /**
     * 根据系统类型查询设备信息
     */
    @Override
    public List<BusDevice> queryDeviceInfo(BusDevice busDevice){
        return busDeviceDao.queryDeviceInfo(busDevice);
    }

    /**
     * 查询设备简要信息
     */
    @Override
    public List<DeviceBriefInfo> queryDeviceBriefInfo(BusDevice busDevice){
        return busDeviceDao.queryDeviceBriefInfo(busDevice);
    }

    /**
     * 根据项目ID和子系统ID查询未关联一级参数信息
     */
    @Override
    public List<BusParamFirst> queryParamFirstInfoBySysId(BusParamFirst busParamFirst){
        return busParamFirstDao.queryParamFirstInfoBySysId(busParamFirst);
    }

    /**
     * 根据系统类型ID和一级设备类型ID查询二级设备类型信息
     */
    @Override
    public List<TypeFirstAndSecond> queryTypeSecondInfo(String systemId){
      return busTypeSecondDao.queryTypeSecondInfo(systemId);
    }

    /**
     * 处理一、二级设备类型下拉菜单级联格式
     */
    @Override
    public List<Map<String,Object>> processTypeFormat(String systemId){
        List<Map<String,Object>> data = new ArrayList<>();
        List<TypeFirstAndSecond> typeSecondList = busTypeSecondDao.queryTypeSecondInfo(systemId);
        Map<String,List<TypeFirstAndSecond>> resultMap = new HashMap<>(16);
        /*
        将所有二级设备信息数据通过一级设备ID分组，存于resultMap中
         */
        for(TypeFirstAndSecond typeFirstAndSecond : typeSecondList){
            if (resultMap.containsKey(typeFirstAndSecond.getFirstId())){
                resultMap.get(typeFirstAndSecond.getFirstId()).add(typeFirstAndSecond);
            }else {
                List<TypeFirstAndSecond> list = new ArrayList<>();
                list.add(typeFirstAndSecond);
                resultMap.put(typeFirstAndSecond.getFirstId(),list);
            }
        }
        /*
        对每组数据进行进一步格式处理
         */
        for(String key : resultMap.keySet()){
            Map<String,Object> firstMap = new HashMap<>(16);
            firstMap.put("firstId",resultMap.get(key).get(0).getFirstId());
            firstMap.put("firstName",resultMap.get(key).get(0).getFirstName());
            List<Map<String,String>> secondListMap = new ArrayList<>();
            for(TypeFirstAndSecond typeFirstAndSecond : resultMap.get(key)){
                Map<String,String> secondMap = new HashMap<>(16);
                secondMap.put("secondId",typeFirstAndSecond.getSecondId());
                secondMap.put("secondName",typeFirstAndSecond.getSecondName());
                secondListMap.add(secondMap);
            }
            firstMap.put("child",secondListMap);
            data.add(firstMap);
        }
        return data;
    }

    /**
     * 添加固定参数表信息
     */
    @Override
    public int addParamFixed(BusParamFixed busParamFixed){
        busParamFixed.setId(UuidUtil.create32());
        return busParamFixedDao.insert(busParamFixed);
    }

    /**
     * 查询固定参数信息
     */
    @Override
    public List<BusParamFixed> queryParamFixed(BusParamFixed busParamFixed){
        return busParamFixedDao.queryParamFixed(busParamFixed);
    }

    /**
     * 修改固定参数表信息
     */
    @Override
    public int updateParamFixed(BusParamFixed busParamFixed){
        return busParamFixedDao.updateByPrimaryKeySelective(busParamFixed);
    }

    /**
     * 删除固定参数表信息
     */
    @Override
    public int deleteParamFixed(BusParamFixed busParamFixed){
        return busParamFixedDao.deleteByPrimaryKey(busParamFixed);
    }

}
