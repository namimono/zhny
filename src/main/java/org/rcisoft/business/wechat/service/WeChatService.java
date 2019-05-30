package org.rcisoft.business.wechat.service;

import org.rcisoft.base.result.Result;
import org.rcisoft.business.wechat.entity.DeviceDetailed;
import org.rcisoft.entity.BusInspection;
import org.rcisoft.entity.SysInspector;

/**
 * @author GaoLiwei
 * @date 2019/5/29
 */
public interface WeChatService {

    /**
     * 判断openId是否已经跟某一个巡检员绑定
     * @param openId
     * @return Result
     */
    Result openIdBindingFlag(String openId);

    /**
     * 巡检员微信端登陆
     * @param sysInspector
     * @return Result
     */
    Result loginWechat(SysInspector sysInspector);

    /**
     * 根据设备Id,查出设备信息
     * @param deviceId
     * @return DeviceDetail
     */
    DeviceDetailed getDeviceDetailById(String deviceId);

    /**
     * 保存巡检记录
     * @param busInspection
     * @return Integer
     */
    Integer saveInspection(BusInspection busInspection);




}
