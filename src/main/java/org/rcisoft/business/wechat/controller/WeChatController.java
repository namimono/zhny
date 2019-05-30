package org.rcisoft.business.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.wechat.entity.DeviceDetailed;
import org.rcisoft.business.wechat.service.WeChatService;
import org.rcisoft.entity.BusInspection;
import org.rcisoft.entity.BusPushRecord;
import org.rcisoft.entity.SysInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GaoLiwei
 * @date 2019/5/29
 */
@Api(tags = "微信相关接口")
@RestController
@RequestMapping("/wx/weChat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;
    @Autowired
    private WxMpService wxMpService;


    /**
     * 判断openId是否已经与某一个巡检员绑定了
     * @param openId
     * @return Result
     */
    @GetMapping("/openIdBindingFlag")
    public Result openIdBindingFlag(@RequestParam String openId){
        return weChatService.openIdBindingFlag(openId);
    }


    /**
     * 巡检员登陆
     * @param sysInspector
     * @return Result
     */
    @PostMapping("/loginWechat")
    public Result loginWechat(@RequestBody SysInspector sysInspector){
        return weChatService.loginWechat(sysInspector);
    }

    /**
     * 获得配置信息
     * @param url
     * @return Result
     */
    @GetMapping("/getConfig")
    public Result getConfig(String url){
        try {
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
            Map<String, Object> map = new HashMap<>(4);
            map.put("timestamp", jsapiSignature.getTimestamp());
            map.put("nonceStr", jsapiSignature.getNonceStr());
            map.put("signature", jsapiSignature.getSignature());
            map.put("appId", jsapiSignature.getAppId());
            return Result.result(map);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得设备信息
     * @param deviceId
     * @return Result
     */
    @GetMapping("/getDeviceInformation/{deviceId}")
    public Result getDeviceInformation(@PathVariable String deviceId){
        DeviceDetailed deviceDetailById = weChatService.getDeviceDetailById(deviceId);
        if (null != deviceDetailById){
            return Result.result(deviceDetailById);
        }
        return Result.result(0,null);
    }

    /**
     * 保存巡检信息
     * @param busInspection
     * @return Result
     */
    @PostMapping("/saveInspection")
    public Result saveInspection(BusInspection busInspection){
        return Result.result(weChatService.saveInspection(busInspection),"成功","失败");
    }

    @PostMapping("/sendMessageToWechat")
    @ApiOperation(value = "发送微信消息", notes = "参数：项目Id,推送内容")
    public Result sendMessageToWechat(@RequestBody BusPushRecord busPushRecord){
        return Result.result(weChatService.sendMessageToWechat(busPushRecord),"发送成功","发送失败");
    }


}
