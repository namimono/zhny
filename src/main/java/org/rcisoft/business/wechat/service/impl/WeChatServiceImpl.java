package org.rcisoft.business.wechat.service.impl;

import org.rcisoft.base.result.Result;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.wechat.entity.DeviceDetailed;
import org.rcisoft.business.wechat.service.WeChatService;
import org.rcisoft.dao.BusInspectionDao;
import org.rcisoft.dao.SysInspectorDao;
import org.rcisoft.entity.BusInspection;
import org.rcisoft.entity.SysInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GaoLiwei
 * @date 2019/5/29
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private SysInspectorDao sysInspectorDao;
    @Autowired
    private BusInspectionDao busInspectionDao;


    @Override
    public Result openIdBindingFlag(String openId) {

        SysInspector sysInspector = new SysInspector();
        sysInspector.setOpenId(openId);
        List<SysInspector> sysInspectorList = sysInspectorDao.select(sysInspector);
        if (sysInspectorList.size() >0){
            Map<String,String> map = new HashMap<>(2);
            map.put("realName",sysInspectorList.get(0).getRealName());
            map.put("inspectorId",sysInspectorList.get(0).getId());
            return Result.result(1,map);
        }
        return Result.result(0,null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result loginWechat(SysInspector sysInspector) {
        //首先验证该用户是否存在
        SysInspector useSysInspector = new SysInspector();
        useSysInspector.setName(sysInspector.getName());
        List<SysInspector> sysInspectorList = sysInspectorDao.select(useSysInspector);
        //如果存在则继续处理，不存在则直接返回
        if (sysInspectorList.size() == 1){
            //判断密码是否正确,如果正确，则进行微信绑定，不正确则返回
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (bCryptPasswordEncoder.matches(sysInspector.getPassword(),sysInspectorList.get(0).getPassword())){
                sysInspector.setId(sysInspectorList.get(0).getId());
                int flag = sysInspectorDao.insertSelective(sysInspector);
                Map<String,String> map = new HashMap<>(2);
                map.put("realName",sysInspector.getRealName());
                map.put("inspectorId",sysInspector.getId());
                if (flag >0){
                    return Result.result(1,map);
                }
            }
            return Result.result(0,"登陆成功","密码错误，登陆失败！");
        }
        return Result.result(0,"登陆成功","用户不存在！");
    }

    @Override
    public DeviceDetailed getDeviceDetailById(String deviceId) {
        return sysInspectorDao.getDeviceDetailById(deviceId);
    }

    @Override
    public Integer saveInspection(BusInspection busInspection) {
        busInspection.setId(UuidUtil.create32());
        return busInspectionDao.insert(busInspection);
    }


}
