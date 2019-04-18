package org.rcisoft.business.system.project.service.Impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.dao.DeviceConfigDao;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.ParamFirstContainSecond;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:13
 **/
@Service
public class DeviceConfigServiceImpl implements DeviceConfigService {

    /** 根路径 */
    @Value("${location.path}")
    String path;
    /** 背景图文件夹 */
    @Value("${location.device}")
    String device;

    @Autowired
    private BusDeviceDao busDeviceDao;
    @Autowired
    private DeviceConfigDao deviceConfigDao;
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
    @Autowired
    private BusFactoryDao busFactoryDao;
    @Autowired
    private BusDeviceTypeDao busDeviceTypeDao;
    @Autowired
    private BusDevicePictureDao busDevicePictureDao;

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

//    /**
//     * 处理一、二级设备类型下拉菜单级联格式
//     */
//    @Override
//    public List<Map<String,Object>> processTypeFormat(String systemId){
//        List<Map<String,Object>> data = new ArrayList<>();
//        List<TypeFirstAndSecond> typeSecondList = busTypeSecondDao.queryTypeSecondInfo(systemId);
//        Map<String,List<TypeFirstAndSecond>> resultMap = new HashMap<>(16);
//        /*
//        将所有二级设备信息数据通过一级设备ID分组，存于resultMap中
//         */
//        for(TypeFirstAndSecond typeFirstAndSecond : typeSecondList){
//            if (resultMap.containsKey(typeFirstAndSecond.getFirstId())){
//                resultMap.get(typeFirstAndSecond.getFirstId()).add(typeFirstAndSecond);
//            }else {
//                List<TypeFirstAndSecond> list = new ArrayList<>();
//                list.add(typeFirstAndSecond);
//                resultMap.put(typeFirstAndSecond.getFirstId(),list);
//            }
//        }
//        /*
//        对每组数据进行进一步格式处理
//         */
//        for(String key : resultMap.keySet()){
//            Map<String,Object> firstMap = new HashMap<>(16);
//            firstMap.put("firstId",resultMap.get(key).get(0).getFirstId());
//            firstMap.put("firstName",resultMap.get(key).get(0).getFirstName());
//            List<Map<String,String>> secondListMap = new ArrayList<>();
//            for(TypeFirstAndSecond typeFirstAndSecond : resultMap.get(key)){
//                Map<String,String> secondMap = new HashMap<>(16);
//                secondMap.put("secondId",typeFirstAndSecond.getSecondId());
//                secondMap.put("secondName",typeFirstAndSecond.getSecondName());
//                secondListMap.add(secondMap);
//            }
//            firstMap.put("child",secondListMap);
//            data.add(firstMap);
//        }
//        return data;
//    }

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
    //@Override
    private int deleteParamFirst(String paramFirstId){
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
    //@Override
    private int deleteParamSecond(String paramSecondId){
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
        return busParamSecondDao.deleteByPrimaryKey(paramSecondId);
    }

//    /**
//     * 修改一级参数信息
//     */
//    @Override
//    public int updateParamFirst(BusParamFirst busParamFirst){
//        return busParamFirstDao.updateByPrimaryKeySelective(busParamFirst);
//    }

    /**
     * 批量更新一级参数
     */
    //@Override
    private int updateAllParamFirst(List<BusParamFirst> list){
        return deviceConfigDao.updateAllParamFirst(list);
    }

//    /**
//     * 修改二级参数信息
//     */
//    @Override
//    public int updateParamSecond(BusParamSecond busParamSecond){
//        return busParamSecondDao.updateByPrimaryKeySelective(busParamSecond);
//    }

    /**
     * 批量更新二级参数
     */
    //@Override
    private int updateAllParamSecond(List<BusParamSecond> list){
        list.forEach(busParamSecond -> {
            if (busParamSecond.getFaultStatus() == null){ busParamSecond.setFaultStatus(0);}
            if (busParamSecond.getEnergyTypeId() == null){ busParamSecond.setEnergyTypeId(0);}
            if (busParamSecond.getElecType() == null){ busParamSecond.setElecType(0);}
            if (busParamSecond.getFirstSign() == null){ busParamSecond.setFirstSign(0);}

        });
        return deviceConfigDao.updateAllParamSecond(list);
    }

//    /**
//     * 新增一二级参数信息
//     */
//    @Override
//    public int addParamInfo(BusParamFirst busParamFirst,BusParamSecond busParamSecond){
//        String id = UuidUtil.create32();
//        busParamFirst.setId(id);
//        busParamFirstDao.insertSelective(busParamFirst);
//
//        busParamSecond.setId(UuidUtil.create32());
//        busParamSecond.setParamFirstId(id);
//        return busParamSecondDao.insertSelective(busParamSecond);
//    }
//
//    /**
//     * 新增二级参数信息
//     */
//    @Override
//    public int addParamSecond(BusParamSecond busParamSecond){
//        busParamSecond.setId(UuidUtil.create32());
//        return busParamSecondDao.insertSelective(busParamSecond);
//    }

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

    /**
     * 批量增删改一二级参数信息
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ServiceResult batchOperationParams(List<ParamFirstContainSecond> list,String paramFirstIds,String paramSecondIds){

        //判断新增一级参数信息是否重复
        for (ParamFirstContainSecond paramFirstContainSecond : list){
            if (paramFirstContainSecond.getBusParamFirst().getId() == null || "".equals(paramFirstContainSecond.getBusParamFirst().getId())){
                int flag = deviceConfigDao.queryRepeatNum(paramFirstContainSecond.getBusParamFirst().getName(),paramFirstContainSecond.getBusParamFirst().getCoding(),paramFirstContainSecond.getBusParamFirst().getProjectId());
                if(flag > 0){
                    return new ServiceResult(flag,"error");
                }
            }
        }

        int sum = 0;
        //批量删除
        String[] firstIds = paramFirstIds.split(",");
        String[] secondIds = paramSecondIds.split(",");
        if (!"0".equals(firstIds[0])){
            for (String firstId : firstIds) {
                sum += this.deleteParamFirst(firstId);
            }
        }
        if (!"0".equals(secondIds[0])){
            for (String secondId : secondIds) {
                sum += this.deleteParamSecond(secondId);
            }
        }
        //批量修改或新增一二级参数
        List<BusParamFirst> addParamFirstList = new ArrayList<>();
        List<BusParamSecond> addParamSecondList = new ArrayList<>();
        List<BusParamFirst> updateParamFirstList = new ArrayList<>();
        List<BusParamSecond> updateParamSecondList = new ArrayList<>();
        if(list.size() > 0){
            list.forEach(paramFirstContainSecond -> {
                if (paramFirstContainSecond.getBusParamFirst().getId() == null || "".equals(paramFirstContainSecond.getBusParamFirst().getId())){
                    String id = UuidUtil.create32();
                    paramFirstContainSecond.getBusParamFirst().setId(id);
                    addParamFirstList.add(paramFirstContainSecond.getBusParamFirst());
                    //循环添加二级参数id和其一级参数id字段信息
                    paramFirstContainSecond.getSecondary().forEach(busParamSecond -> {
                        busParamSecond.setId(UuidUtil.create32());
                        if (busParamSecond.getSourceId() != 4) {
                            busParamSecond.setParamFirstId(id);
                        }
                        addParamSecondList.add(busParamSecond);
                    });
                }else {
                    paramFirstContainSecond.getSecondary().forEach(busParamSecond -> {
                        if (busParamSecond.getId() == null || "".equals(busParamSecond.getId())){
                            busParamSecond.setId(UuidUtil.create32());
                            addParamSecondList.add(busParamSecond);
                        }else {
                            updateParamSecondList.add(busParamSecond);
                        }
                    });
                    updateParamFirstList.add(paramFirstContainSecond.getBusParamFirst());
                }
            });
        }
        //执行批量更新操作
        if(updateParamFirstList.size() > 0){
            sum += updateAllParamFirst(updateParamFirstList);
        }
        if (updateParamSecondList.size() > 0){
            sum += updateAllParamSecond(updateParamSecondList);
        }
        //执行批量新增操作
        if(addParamFirstList.size() > 0){
            sum += busParamFirstDao.insertListUseAllCols(addParamFirstList);
        }
        if (addParamSecondList.size() > 0){
            sum += busParamSecondDao.insertListUseAllCols(addParamSecondList);
        }
        return new ServiceResult(sum,"success");
    }

    /**
     * 新增厂家信息
     */
    @Override
    public int addFactory(BusFactory busFactory){
        busFactory.setId(UuidUtil.create32());
        return busFactoryDao.insertSelective(busFactory);
    }

    /**
     * 删除厂家信息
     */
    @Override
    public int deleteFactory(String factoryId){
        return busFactoryDao.deleteByPrimaryKey(factoryId);
    }

    /**
     * 修改厂家信息
     */
    @Override
    public int updateFactory(BusFactory busFactory){
        return busFactoryDao.updateByPrimaryKeySelective(busFactory);
    }

    /**
     * 查询厂家信息
     */
    @Override
    public List<BusFactory> queryFactory(){
        return busFactoryDao.selectAll();
    }

    /**
     * 新增设备类型
     */
    @Override
    public int addDeviceType(BusDeviceType busDeviceType){
        busDeviceType.setId(UuidUtil.create32());
        return busDeviceTypeDao.insertSelective(busDeviceType);
    }

    /**
     * 删除设备类型
     */
    @Override
    public int deleteDeviceType(String typeId){
        return busDeviceTypeDao.deleteByPrimaryKey(typeId);
    }

    /**
     * 修改设备类型
     */
    @Override
    public int updateDeviceType(BusDeviceType busDeviceType){
        return busDeviceTypeDao.updateByPrimaryKeySelective(busDeviceType);
    }

    /**
     * 查询设备类型
     */
    @Override
    public List<BusDeviceType> queryDeviceType(){
        return busDeviceTypeDao.selectAll();
    }

    /**
     * 新增设备图片
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ServiceResult addTypeImage(MultipartFile file,String proId,String name){
        // 返回值
        Integer result = 0;
        // 后缀
        String suffix = "";
        String[] fileNameArray = StringUtils.split(file.getOriginalFilename(), ".");
        if (fileNameArray.length > 1) {
            suffix = "." + fileNameArray[fileNameArray.length - 1];
        }
        // 新的文件名
        String fileName = UuidUtil.create32() + suffix;

        BusDevicePicture busDevicePicture = new BusDevicePicture();
        busDevicePicture.setId(UuidUtil.create32());
        busDevicePicture.setProjectId(proId);
        busDevicePicture.setName(name);
        busDevicePicture.setUrl(fileName);

        try {
            result = busDevicePictureDao.insert(busDevicePicture);
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + device + "/" + proId + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServiceResult(result,fileName);
    }

    /**
     * 删除设备图片
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int deleteDevicePic(String picId){
        BusDevicePicture busDevicePicture = busDevicePictureDao.selectByPrimaryKey(picId);
        File file = new File(path + device + "/" + busDevicePicture.getProjectId() + "/" + busDevicePicture.getUrl());
        if (file.exists()) {
            file.delete();
        }
        return busDevicePictureDao.deleteByPrimaryKey(picId);
    }

    /**
     * 修改设备图片
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ServiceResult updateTypeImage(MultipartFile file,String picId,String name){
        if (file == null){
            BusDevicePicture busDevicePicture = new BusDevicePicture();
            busDevicePicture.setId(picId);
            busDevicePicture.setName(name);
            return new ServiceResult(busDevicePictureDao.updateByPrimaryKeySelective(busDevicePicture),"null");
        }else {
            //先删除原有图片
            BusDevicePicture busDevicePicture = busDevicePictureDao.selectByPrimaryKey(picId);
            File file0 = new File(path + device + "/" + busDevicePicture.getProjectId() + "/" + busDevicePicture.getUrl());

            // 返回值
            Integer result = 0;
            // 后缀
            String suffix = "";
            String[] fileNameArray = StringUtils.split(file.getOriginalFilename(), ".");
            if (fileNameArray.length > 1) {
                suffix = "." + fileNameArray[fileNameArray.length - 1];
            }
            // 新的文件名
            String fileName = UuidUtil.create32() + suffix;

            busDevicePicture.setId(picId);
            busDevicePicture.setName(name);
            busDevicePicture.setUrl(fileName);

            try {
                result = busDevicePictureDao.updateByPrimaryKeySelective(busDevicePicture);
                if (file0.exists()) {
                    file0.delete();
                }
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path + device + "/" + busDevicePicture.getProjectId() + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ServiceResult(result,fileName);
        }
    }


    /**
     * 查询设备图片
     */
    @Override
    public List<BusDevicePicture> queryDevicePic(String proId){
        List<BusDevicePicture> busDevicePictureList = busDevicePictureDao.queryDevicePicByProId(proId);
        busDevicePictureList.forEach(busDevicePicture -> {
            String url = path + device + "/" + busDevicePicture.getProjectId() + "/" + busDevicePicture.getUrl();
            url=url.replace("\\", "/");
            busDevicePicture.setUrl(url);
        });
        return busDevicePictureList;
    }
}
