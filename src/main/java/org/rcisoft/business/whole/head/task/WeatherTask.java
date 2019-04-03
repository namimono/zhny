package org.rcisoft.business.whole.head.task;

import org.rcisoft.business.whole.head.service.SysCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 17:28 2019/4/1
 */
@Component
public class WeatherTask {
    @Autowired
    private SysCityService sysCityService;

    /**
     * 定时获取天气
     * 每小时第53分钟执行一次
     */
    @Scheduled(cron = "0 09 * * * ?")
    public void temperature(){
        sysCityService.queryCityWeather();
    }
}
