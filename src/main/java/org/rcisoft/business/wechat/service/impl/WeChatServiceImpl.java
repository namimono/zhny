package org.rcisoft.business.wechat.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.result.Result;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.wechat.entity.DeviceDetailed;
import org.rcisoft.business.wechat.service.WeChatService;
import org.rcisoft.business.whole.right.dao.BusPushRecordDao;
import org.rcisoft.dao.BusInspectionDao;
import org.rcisoft.dao.BusProjectDao;
import org.rcisoft.dao.SysInspectorDao;
import org.rcisoft.entity.BusInspection;
import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.BusPushRecord;
import org.rcisoft.entity.SysInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private BusPushRecordDao busPushRecordDao;
    @Autowired
    private BusProjectDao busProjectDao;
    @Autowired
    private WxMpService wxMpService;


    @Override
    public Result openIdBindingFlag(String openId) {

        SysInspector sysInspector = new SysInspector();
        sysInspector.setOpenId(openId);
        List<SysInspector> sysInspectorList = sysInspectorDao.select(sysInspector);
        if (sysInspectorList.size() > 0) {
            Map<String, String> map = new HashMap<>(2);
            map.put("realName", sysInspectorList.get(0).getRealName());
            map.put("inspectorId", sysInspectorList.get(0).getId());
            return Result.result(1, map);
        }
        return Result.result(0, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result loginWechat(SysInspector sysInspector) {
        //首先验证该用户是否存在
        SysInspector useSysInspector = new SysInspector();
        useSysInspector.setName(sysInspector.getName());
        List<SysInspector> sysInspectorList = sysInspectorDao.select(useSysInspector);
        //如果存在则继续处理，不存在则直接返回
        if (sysInspectorList.size() == 1) {
            //判断密码是否正确,如果正确，则进行微信绑定，不正确则返回
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (bCryptPasswordEncoder.matches(sysInspector.getPassword(), sysInspectorList.get(0).getPassword())) {
                sysInspector.setId(sysInspectorList.get(0).getId());
                int flag = sysInspectorDao.insertSelective(sysInspector);
                Map<String, String> map = new HashMap<>(2);
                map.put("realName", sysInspector.getRealName());
                map.put("inspectorId", sysInspector.getId());
                if (flag > 0) {
                    return Result.result(1, map);
                }
            }
            return Result.result(0, "登陆成功", "密码错误，登陆失败！");
        }
        return Result.result(0, "登陆成功", "用户不存在！");
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer sendMessageToWechat(BusPushRecord busPushRecord) {
        busPushRecord.setId(UuidUtil.create32());
        busPushRecord.setPushTime(new Date());
        int insertFlag = busPushRecordDao.insert(busPushRecord);
        //成功插入数据库时
        if (insertFlag > 0) {
            //查出这个项目拥有的巡检员
            BusProject project = busProjectDao.selectByPrimaryKey(busPushRecord.getProjectId());
            List<SysInspector> sysInspectorList = sysInspectorDao.listSysInspectorByIds(Arrays.asList(project.getInspectIds().split(",")));
            //返回标志位
            int flag = 0;
            for (SysInspector sysInspector : sysInspectorList) {
                flag += sendMessage(project.getName(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), busPushRecord.getContent(), sysInspector.getOpenId());
            }
            if (flag > 0) {
                return 1;
            }
            return 0;
        }
        return 0;
    }

    /**
     * 发送模版消息（此方法参数需要修改，需要替换几个参数在此处需要传入几个参数，但是openId是必须的）
     *
     * @param message
     * @param openId
     * @return
     */
    private Integer sendMessage(String proNm, String time, String message, String openId) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId("LwdzUKkiSezoITPvgV3bhW7ANnX7f965DBA4FdRkqIw")
//				.url(" ")//详情连接
                .build();

        // 模版中有几个需要替换的参数templateMessage就添加几个WxMpTemplateData
        templateMessage
                .addData(new WxMpTemplateData("first", "调整设备参数", "#FF00FF"))
                .addData(new WxMpTemplateData("keyword1", time, "#FF00FF"))
                .addData(new WxMpTemplateData("keyword2", proNm, "#FF00FF"))
                .addData(new WxMpTemplateData("keyword3", message, "#FF00FF"));
//                .addData(new WxMpTemplateData("remark", message, "#FF00FF"));
        // 消息发送完之后返回的消息id，用于判断是否发送成功
        String msgId;
        try {
            // 发送消息
            msgId = this.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            // 此处暂时抛出异常，正式需要注释掉，否则前台不会接到返回值
            e.printStackTrace();
            msgId = "";
        }
        // 判断msgId是否为空，来判断是否发送成功
        if (StringUtils.isNotEmpty(msgId)) {
            return 1;
        } else {
            return 0;
        }

    }


}
