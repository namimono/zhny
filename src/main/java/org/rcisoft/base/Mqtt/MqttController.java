package org.rcisoft.base.Mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者推送测试
 * @author Hukaihan
 */
@RestController
public class MqttController {
    @Autowired
    private MqttGateway mqttGateway;
    @RequestMapping("/sendToMqtt")
    public String sendData(@RequestParam("message") String message,@RequestParam("topic") String topic){
        mqttGateway.sendToMqtt(message,topic);
        return "OK";
    }

}
