package org.rcisoft.business.whole.head.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.business.whole.head.service.SysCityService;
import org.rcisoft.dao.SysCityDao;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:40 2019/3/8
 */
@Service
public class SysCityServiceImpl implements SysCityService {
    @Autowired
    SysCityDao sysCityDao;

    /**
     * 根据城市名称获取城市天气信息
     * @param city
     * @return
     */
    @Override
    public BusTemperature queryCityByName(String city) {
        BusTemperature busTemperature = new BusTemperature();
        try{
            String cityName = StringUtils.isEmpty(city)?null:StringUtils.substring(city,0,city.length());
            System.out.println(cityName);
            if(cityName == null) return  busTemperature;
            SysCity sysCity = new SysCity();
            sysCity = sysCityDao.queryCityInfoByName(cityName);
            String code = sysCity.getCoding();
            JSONObject weatherJson = this.getWeatherMessage(code);
            //湿度
            String sd =  (String)weatherJson.get("SD");
            int Humidity = Integer.parseInt(sd.replaceAll("%", ""));
            //温度
            String wd = (String)weatherJson.get("temp");
            //风速
            String fs = (String)weatherJson.get("WD") + (String) weatherJson.get("WS");
            BigDecimal temp = new BigDecimal(wd);
           busTemperature.setWind(fs);
           busTemperature.setTemperature(temp);
           busTemperature.setHumidity(Humidity);
           busTemperature.setHumidityPercent(sd);
           busTemperature.setCoding(code);
        }catch(Exception e){
            e.printStackTrace();
        }
        return busTemperature;
    }

    /**
     * 根据城市代码生成天气json值
     * @param code
     * @return
     */
    private JSONObject getWeatherMessage(String code){
        BusTemperature busTemp = new BusTemperature();
        String apiUrl = String.format("http://weatherapi.market.xiaomi.com/wtr-v2/temp/realtime?cityId=%s", code);
        URL url = null;
        URLConnection open = null;
        InputStream input = null;
        try {
            url = new URL(apiUrl);
            open = url.openConnection();
            open.setConnectTimeout(10000);
            input = open.getInputStream();
            String result = IOUtils.toString(input,"UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            return (JSONObject)jsonObject.get("weatherinfo");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }


}
