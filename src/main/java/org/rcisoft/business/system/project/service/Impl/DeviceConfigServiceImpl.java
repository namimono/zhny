package org.rcisoft.business.system.project.service.Impl;

import com.google.zxing.WriterException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.result.Result;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.QRCodeUtils;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.common.service.CommonService;
import org.rcisoft.business.system.project.dao.DeviceConfigDao;
import org.rcisoft.business.system.project.entity.BatchParams;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.business.system.project.entity.ParamFirstContainSecond;
import org.rcisoft.business.system.project.entity.ParamResult;
import org.rcisoft.business.system.project.service.DeviceConfigService;
import org.rcisoft.dao.*;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    /** url */
    @Value("${location.url}")
    String url;
    /** 根路径 */
    @Value("${location.path}")
    String path;
    /** 设备图片文件夹 */
    @Value("${location.device}")
    String device;
    /**
     * 二维码存放路径
     */
    @Value("${location.qrcode}")
    String qrcodeImgPath;

    @Autowired
    private CommonService commonServiceImpl;

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
        String id = UuidUtil.create32();
        busDevice.setId(id);
        busDevice.setCreateTime(this.getNowTime());
        busDevice.setQrcodeUrl(id+".JPG");
        try {
            //生成二维码
            QRCodeUtils.createQRCodeFile(id,path+qrcodeImgPath+"/"+busDevice.getProjectId(),id);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return busDeviceDao.insertSelective(busDevice);
    }

    /**
     * 删除设备信息(谨慎!)
     */
    @Transactional
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
    public ParamResult queryParamInfo(String deviceId){
        // 最后的返回值
        ParamResult result = new ParamResult();
        // 一二级参数返回值
        List<ParamFirstContainSecond> paramFirstContainSecondList = result.getList();
        // 固定参数返回值
        List<BusParamSecond> fixedList = result.getFixedList();
        // 查询数据
        List<BusParamFirst> paramFirstList = busParamFirstDao.queryParamFirstByDevId(deviceId);
        List<BusParamSecond> paramSecondList = busParamSecondDao.queryParamSecondByDevId(deviceId);
        //分组存储二级参数信息
        Map<String,List<BusParamSecond>> resultMap = new HashMap<>(16);
        /*
        将所有二级参数信息通过一级参数ID进行分组，存于resultMap中
         */
        for(BusParamSecond busParamSecond : paramSecondList){
            // 如果是固定参数，放入固定参数返回值
            if (busParamSecond.getSourceId() == 4) {
                fixedList.add(busParamSecond);
            } else {
                if (resultMap.containsKey(busParamSecond.getParamFirstId())){
                    resultMap.get(busParamSecond.getParamFirstId()).add(busParamSecond);
                }else {
                    List<BusParamSecond> list = new ArrayList<>();
                    list.add(busParamSecond);
                    resultMap.put(busParamSecond.getParamFirstId(),list);
                }
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
        return result;
    }

    /**
     * 批量增删改一二级参数信息
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Result batchOperationParams(BatchParams batchParams){
        // 操作结果
        int result = 0;
        // 获取参数
        List<ParamFirstContainSecond> batchList = batchParams.getBatchList();
        String paramFirstIds = batchParams.getParamFirstIds();
        String paramSecondIds = batchParams.getParamSecondIds();
        // 1.删除一级二级
        if (StringUtils.isNotEmpty(paramFirstIds)) {
            String[] split = paramFirstIds.split(",");
            for (String paramFirstId : split) {
                result += busParamFirstDao.deleteByPrimaryKey(paramFirstId);
            }
        }
        if (StringUtils.isNotEmpty(paramSecondIds)) {
            String[] split = paramSecondIds.split(",");
            for (String paramSecondId : split) {
                result += busParamSecondDao.deleteByPrimaryKey(paramSecondId);
            }
        }
        // 删除其他一二级相关的表
        commonServiceImpl.deleteFirstAndSecondTable(paramFirstIds, paramSecondIds);
        // 保存or修改数据
        if (batchList.size() > 0) {
            List<BusParamFirst> addFirstList = new ArrayList<>();
            List<BusParamFirst> updateFirstList = new ArrayList<>();
            List<BusParamSecond> addSecondList = new ArrayList<>();
            List<BusParamSecond> updateSecondList = new ArrayList<>();
            // 循环数据
            for (ParamFirstContainSecond paramFirstContainSecond : batchList) {
                BusParamFirst busParamFirst = paramFirstContainSecond.getBusParamFirst();
                String firstId = busParamFirst.getId();
                // 固定参数不需要添加一级
                if (busParamFirst.getSourceId() != 4) {
                    if (StringUtils.isEmpty(firstId)) {
                        // id为空，新增
                        firstId = UuidUtil.create32();
                        busParamFirst.setId(firstId);
                        addFirstList.add(busParamFirst);
                    } else {
                        // 不为空，更新
                        updateFirstList.add(busParamFirst);
                    }
                }
                List<BusParamSecond> secondary = paramFirstContainSecond.getSecondary();
                for (BusParamSecond busParamSecond : secondary) {
                    if (StringUtils.isEmpty(busParamSecond.getId())) {
                        busParamSecond.setId(UuidUtil.create32());
                        addSecondList.add(busParamSecond);
                        busParamSecond.setParamFirstId(firstId);
                    } else {
                        updateSecondList.add(busParamSecond);
                    }
                }
            }
            if (addFirstList.size() > 0) {
                result += busParamFirstDao.insertListUseAllCols(addFirstList);
            }
            if (updateFirstList.size() > 0) {
                result += deviceConfigDao.updateAllParamFirst(updateFirstList);
            }
            if (addSecondList.size() > 0) {
                result += busParamSecondDao.insertListUseAllCols(addSecondList);
            }
            if (updateSecondList.size() > 0) {
                result += deviceConfigDao.updateAllParamSecond(updateSecondList);
            }
        }
        // 返回值
        String message = "";
        if (result > 0) {
            message = "参数配置成功";
        } else {
            message = "参数配置无变化";
        }
        return new Result(result, message, null);
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
            String myUrl = url + device + "/" + busDevicePicture.getProjectId() + "/" + busDevicePicture.getUrl();
            myUrl=myUrl.replace("\\", "/");
            busDevicePicture.setUrl(myUrl);
        });
        return busDevicePictureList;
    }
}
