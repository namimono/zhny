package org.rcisoft.business.system.project.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.DeviceConfigDao;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.ParamFirstContainSecond;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private BusParamSecondDao busParamSecondDao;
    @Autowired
    private SysSystemDao sysSystemDao;
    @Autowired
    private EnergyTypeDao energyTypeDao;
    @Autowired
    private SysSourceDao sysSourceDao;
    @Autowired
    private BusVariableDao busVariableDao;
    @Autowired
    private BusTitleParamDao busTitleParamDao;

    /**
     * 获取当前系统时间
     */
    private Date getNowTime(){
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = null;
        try {
            nowTime = fdate.parse(fdate.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowTime;
    }

    /**
     * 新增设备配置信息
     */
    @Override
    public int addDeviceConfigInfo(BusDevice busDevice){
        busDevice.setId(UuidUtil.create32());
        busDevice.setCreateTime(this.getNowTime());
        return busDeviceDao.insertSelective(busDevice);
    }

    /**
     * 删除设备信息(谨慎!)
     */
    @Override
    public int deleteDevice(String deviceId){
        return deviceConfigDao.deleteAllByDevId(deviceId);
    }

    /**
     * 修改设备信息
     */
    @Override
    public int updateDevice(BusDevice busDevice){
        return busDeviceDao.updateByPrimaryKeySelective(busDevice);
    }

    /**
     * 根据设备ID查询设备信息
     */
    @Override
    public List<BusDevice> queryDeviceInfo(String deviceId){
        return busDeviceDao.queryDeviceInfo(deviceId);
    }

    /**
     * 查询设备简要信息（设备配置）
     */
    @Override
    public List<DeviceBriefInfo> queryDeviceBriefInfo(String systemId,String projectId){
        if ("0".equals(systemId)){
            return busDeviceDao.queryDeviceBriefByProID(projectId);
        }else {
            return busDeviceDao.queryDeviceBriefInfo(systemId,projectId);
        }
    }

    /**
     * 查询系统类型
     */
    @Override
    public List<SysSystem> querySystem(){
        return sysSystemDao.querySysSystemInfo();
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
     * 查询参数来源
     */
    @Override
    public List<SysSource> querySource(){
        return sysSourceDao.querySourceInfo();
    }

    /**
     * 查询能耗分类
     */
    @Override
    public List<EnergyType> queryEnergyType(){
        return energyTypeDao.queryEnergyType();
    }

    /**
     * 删除一级参数信息
     */
    @Override
    public int deleteParamFirst(String paramFirstId){
        //删除一级参数关联的二级参数
        Example paramExample = new Example(BusParamSecond.class);
        Example.Criteria paramCriteria = paramExample.createCriteria();
        paramCriteria.andEqualTo("paramFirstId",paramFirstId);
        busParamSecondDao.deleteByExample(paramExample);

        //删除一级参数关联的变量
        Example variableExample = new Example(BusVariable.class);
        Example.Criteria variableCriteria = variableExample.createCriteria();
        variableCriteria.andEqualTo("paramFirstId",paramFirstId);
        busVariableDao.deleteByExample(variableExample);

        //删除一级参数关联的自定义参数
        Example example = new Example(BusVariable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("paramFirstId",paramFirstId);
        busTitleParamDao.deleteByExample(example);

        //删除一级参数
        return busParamFirstDao.deleteByPrimaryKey(paramFirstId);
    }

    /**
     * 删除二级参数信息
     */
    @Override
    public int deleteParamSecond(String paramSecondId){
        //删除二级参数关联的变量
        Example variableExample = new Example(BusVariable.class);
        Example.Criteria variableCriteria = variableExample.createCriteria();
        variableCriteria.andEqualTo("paramSecondId",paramSecondId);
        busVariableDao.deleteByExample(variableExample);

        //删除一级参数关联的自定义参数
        Example example = new Example(BusVariable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("paramSecondId",paramSecondId);
        busTitleParamDao.deleteByExample(example);

        //删除二级参数
        busParamSecondDao.deleteByPrimaryKey(paramSecondId);
        return 0;
    }

    /**
     * 修改一级参数信息
     */
    @Override
    public int updateParamFirst(BusParamFirst busParamFirst){
        return busParamFirstDao.updateByPrimaryKeySelective(busParamFirst);
    }

    /**
     * 修改二级参数信息
     */
    @Override
    public int updateParamSecond(BusParamSecond busParamSecond){
        return busParamSecondDao.updateByPrimaryKeySelective(busParamSecond);
    }

    /**
     * 新增一二级参数信息
     */
    @Override
    public int addParamInfo(BusParamFirst busParamFirst,BusParamSecond busParamSecond){
        String id = UuidUtil.create32();
        busParamFirst.setId(id);
        busParamFirstDao.insertSelective(busParamFirst);

        busParamSecond.setId(UuidUtil.create32());
        busParamSecond.setParamFirstId(id);
        return busParamSecondDao.insertSelective(busParamSecond);
    }

    /**
     * 新增二级参数信息
     */
    @Override
    public int addParamSecond(BusParamSecond busParamSecond){
        busParamSecond.setId(UuidUtil.create32());
        return busParamSecondDao.insertSelective(busParamSecond);
    }

    /**
     * 查询一二级参数信息
     */
    @Override
    public List<ParamFirstContainSecond> queryParamInfo(String deviceId){
        List<ParamFirstContainSecond> paramFirstContainSecondList = new ArrayList<>();
        List<BusParamFirst> paramFirstList = busParamFirstDao.queryParamFirstByDevId(deviceId);
        List<BusParamSecond> paramSecondList = busParamSecondDao.queryParamSecondByDevId(deviceId);

        //分组存储二级参数信息
        Map<String,List<BusParamSecond>> resultMap = new HashMap<>(16);
        /*
        将所有二级参数信息通过一级参数ID进行分组，存于resultMap中
         */
        for(BusParamSecond busParamSecond : paramSecondList){
            if (resultMap.containsKey(busParamSecond.getParamFirstId())){
                resultMap.get(busParamSecond.getParamFirstId()).add(busParamSecond);
            }else {
                List<BusParamSecond> list = new ArrayList<>();
                list.add(busParamSecond);
                resultMap.put(busParamSecond.getParamFirstId(),list);
            }
        }

        //循环将一级参数和其对应的二级参数装入paramFirstContainSecondList
        for(String key : resultMap.keySet()){
            paramFirstList.forEach(busParamFirst -> {
                if (busParamFirst.getId().equals(key)){
                    ParamFirstContainSecond paramFirstContainSecond = new ParamFirstContainSecond();
                    paramFirstContainSecond.setBusParamFirst(busParamFirst);
                    paramFirstContainSecond.setSecondary(resultMap.get(key));
                    paramFirstContainSecondList.add(paramFirstContainSecond);
                }
            });
        }

        return paramFirstContainSecondList;
    }
}
