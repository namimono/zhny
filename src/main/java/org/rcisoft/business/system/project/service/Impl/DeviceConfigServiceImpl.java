package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.DeviceConfigDao;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.rcisoft.business.system.project.service.DeviceConfigService;
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
    private DeviceConfigDao deviceConfigDao;
    @Autowired
    private BusTypeSecondDao busTypeSecondDao;
    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private BusParamFixedDao busParamFixedDao;
    @Autowired
    private MidDeviceParamFirstDao midDeviceParamFirstDao;
    @Autowired
    private MidDeviceParamSecondDao midDeviceParamSecondDao;

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
        /*SET @projectId=21;
CALL delete_AllByProId(@projectId) ;
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
     * 根据项目、设备、系统ID查询固定参数信息
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

    /**
     * 增加设备一级参数中间表信息
     */
    @Override
    public int addMidDeviceFirstInfo(MidDeviceParamFirst midDeviceParamFirst){
        midDeviceParamFirst.setId(UuidUtil.create32());
        return midDeviceParamFirstDao.insertSelective(midDeviceParamFirst);
    }

    /**
     * 删除设备一级参数中间表信息
     */
    @Override
    public int deleteMidDeviceFirstInfo(MidDeviceParamFirst midDeviceParamFirst){
        return midDeviceParamFirstDao.deleteByPrimaryKey(midDeviceParamFirst);
    }

    /**
     * 增加设备二级参数中间表信息
     */
    @Override
    public int addMidDeviceSecondInfo(MidDeviceParamSecond midDeviceParamSecond){
        midDeviceParamSecond.setId(UuidUtil.create32());
        return midDeviceParamSecondDao.insertSelective(midDeviceParamSecond);
    }

    /**
     * 删除设备二级参数中间表信息
     */
    @Override
    public int deleteMidDeviceSecondInfo(MidDeviceParamSecond midDeviceParamSecond){
        return midDeviceParamSecondDao.deleteByPrimaryKey(midDeviceParamSecond);
    }

    /**
     * 修改设备一级参数中间表信息
     */
    @Override
    public int updateMidDeviceFirstInfo(MidDeviceParamFirst midDeviceParamFirst){
        return midDeviceParamFirstDao.updateByPrimaryKeySelective(midDeviceParamFirst);
    }

    /**
     * 修改设备二级参数中间表信息
     */
    @Override
    public int updateMidDeviceSecondInfo(MidDeviceParamSecond midDeviceParamSecond){
        return midDeviceParamSecondDao.updateByPrimaryKeySelective(midDeviceParamSecond);
    }

    /**
     * 删除设备信息(谨慎!)
     */
    @Override
    public String deleteDevice(String deviceId){
        return deviceConfigDao.deleteAllByDevId(deviceId);
    }

}
