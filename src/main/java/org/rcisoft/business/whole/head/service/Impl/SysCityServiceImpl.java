package org.rcisoft.business.whole.head.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.whole.head.dao.HeadDao;
import org.rcisoft.business.whole.head.entity.CityInfo;
import org.rcisoft.business.whole.head.service.SysCityService;
import org.rcisoft.dao.SysCityDao;
import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.BusTemperature;
import org.rcisoft.entity.SysCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:40 2019/3/8
 */
@Service
public class SysCityServiceImpl implements SysCityService {
    @Autowired
    SysCityDao sysCityDao;
    @Autowired
    HeadDao headDao;

    /**
     * 根据城市名称获取城市天气信息
     * @param proId
     * @return
     */
    @Override
    public BusTemperature queryCityByName(String proId) {
        BusTemperature busTemperature = new BusTemperature();
        try{
            String cityCode = StringUtils.isEmpty(proId)?null:StringUtils.substring(proId,0,proId.length());
            if(cityCode == null) return  busTemperature;
            CityInfo cityInfo = new CityInfo();
            cityInfo = sysCityDao.queryCityInfoByName(proId);
            String code = cityInfo.getCode();
            String cityName = cityInfo.getCityName();
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
            busTemperature.setCityName(cityName);
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

    /**
     * 查询所有城市天气信息存入数据库
     * @return
     */
    @Override
    public Integer queryCityWeather() {
        try{
            String id;
            List<BusTemperature> list = new ArrayList<>();
//            SysCity sysCity = new SysCity();
            List<String> codingList = sysCityDao.queryCoding();
            for (String code:codingList){
                if (StringUtils.isNotEmpty(code)) {
                    BusTemperature busTemperature = new BusTemperature();
                    id = UuidUtil.create32();
                    JSONObject weatherJson = this.getWeatherMessage(code);
                    //湿度
                    String sd =  weatherJson.getString("SD");
                    Integer Humidity = Integer.parseInt(sd.replaceAll("%", ""));
                    //温度
                    String wd = weatherJson.getString("temp");
                    //风速
                    String fs = weatherJson.getString("WD") + weatherJson.getString("WS");
                    BigDecimal temp = new BigDecimal(wd);
                    busTemperature.setWind(fs);
                    busTemperature.setTemperature(temp);
                    busTemperature.setHumidity(Humidity);
                    busTemperature.setHumidityPercent(sd);
                    busTemperature.setCoding(code);
                    busTemperature.setId(id);
                    busTemperature.setCreateTime(new Date());
                    list.add(busTemperature);
                }
            }
            Integer saveWeatherFlag = 0;
            if (list.size() > 0) {
                saveWeatherFlag = sysCityDao.saveWeather(list);
            }
            if (saveWeatherFlag != 0 ){
                return 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<BusProject> queryAllProj() {
        return headDao.queryAllProj();
    }
}
