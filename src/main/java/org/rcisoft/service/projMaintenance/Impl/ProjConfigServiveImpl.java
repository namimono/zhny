package org.rcisoft.service.projMaintenance.Impl;

import org.rcisoft.dao.projMaintenance.ProjConfigRepository;
import org.rcisoft.service.projMaintenance.ProjConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:39
 **/

@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class ProjConfigServiveImpl implements ProjConfigService {

    @Autowired
    private ProjConfigRepository projConfigRepository;

    /**
     * 查询全部项目表信息
     */
    public List<Map<String,Object>> queryAllInfo(){
        return projConfigRepository.queryAllInfo();
    }

//    /**
//     * 设置项目配置信息
//     */
//    public int setProjConfig(String name,String province_id,String code,String city_id,String building_local,
//                             String online_team_id,String equipment_age,String inspect_ids,String building_id,String building_area,
//                             String building_coordinate,String offline_team_id,String building_name,String save_sign,String building_zone_id,
//                             String building_age,String user_id,String phones,String type,String id,String create_time){
//        return projConfigRepository.setProjConfig(name,province_id,code,city_id,building_local,
//                online_team_id,equipment_age,inspect_ids,building_id,building_area,
//                building_coordinate,offline_team_id,building_name,save_sign,building_zone_id,
//                building_age,user_id,phones,type,id,create_time);
//    }

    /**
     * 设置项目配置信息
     */
    public int setProjConfig(Map<String,Object> map){
        String name,province_id,code,city_id,building_local,
                online_team_id,inspect_ids,building_id,building_area,
                building_coordinate,offline_team_id,building_name,save_sign,
                building_zone_id,user_id,phones,type,id;

        Date create_time = new Date();
        Date equipment_age = new Date();
        Date building_age = new Date();

        SimpleDateFormat abc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

        name = map.get("name").toString();
        province_id = map.get("province_id").toString();
        code = map.get("code").toString();
        city_id = map.get("city_id").toString();
        building_local = map.get("building_local").toString();
        online_team_id = map.get("online_team_id").toString();
        inspect_ids = map.get("inspect_ids").toString();
        building_id = map.get("building_id").toString();
        building_area = map.get("building_area").toString();
        building_coordinate = map.get("building_coordinate").toString();
        offline_team_id = map.get("offline_team_id").toString();
        building_name = map.get("building_name").toString();
        save_sign = map.get("save_sign").toString();
        building_zone_id = map.get("building_zone_id").toString();

        user_id = map.get("user_id").toString();
        phones = map.get("phones").toString();
        type = map.get("type").toString();
        id = map.get("id").toString();
        try {
            create_time = abc.parse(abc.format(new Date()));
            building_age = sdf.parse(map.get("building_age").toString());
            equipment_age = fmt.parse(map.get("name").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return projConfigRepository.setProjConfig(name,province_id,code,city_id,building_local,
                online_team_id,equipment_age,inspect_ids,building_id,building_area,
                building_coordinate,offline_team_id,building_name,save_sign,building_zone_id,
                building_age,user_id,phones,type,id,create_time);
    }

    /**
     * 获取省份信息及其ID
     */
    public List<Map<String,Object>> queryProvinceInfo(){
        return projConfigRepository.queryProvinceInfo();
    }

    /**
     * 根据省份ID获取城市信息及其code
     */
    public List<Map<String,Object>> queryCityInfo(String provinceId){
        return projConfigRepository.queryCityInfo(provinceId);
    }

    /**
     * 获取线下团队信息
     */
    public List<Map<String,Object>> queryOutTeamInfo(){
        return projConfigRepository.queryOutTeamInfo();
    }

    /**
     * 获取线上团队信息
     */
    public List<Map<String,Object>> queryOnTeamInfo(){
        return projConfigRepository.queryOnTeamInfo();
    }
}
