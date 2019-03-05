package org.rcisoft.dao.sysManagement.projMaintenance;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusProject;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:42
 **/

@Repository
public interface ProjConfigDao {

    /**
     * 设置项目配置信息
     */
    @Insert("INSERT INTO bus_project \n" +
            "(name,province_id,code,city_id,building_local,\n" +
            "online_team_id,equipment_age,inspect_ids,building_id,building_area,\n" +
            "building_coordinate,offline_team_id,building_name,save_sign,building_zone_id,\n" +
            "building_age,user_id,phones,type,id,create_time) \n" +
            "VALUES \n" +
            "(#{name},#{province_id},#{code},#{city_id},#{building_local},\n" +
            "#{online_team_id},#{equipment_age},#{inspect_ids},#{building_id},#{building_area},\n" +
            "#{building_coordinate},#{offline_team_id},#{building_name},#{save_sign},#{building_zone_id},\n" +
            "#{building_age},#{user_id},#{phones},#{type},#{id},#{create_time});\n")
//    @Insert("INSERT INTO bus_project \n" +
//            "(name,province_id,code,id,building_age) \n" +
//            "VALUES \n" +
//            "(#{name},#{provinceId},#{code},#{id},#{buildingAge});\n")
    int setProjConfig(BusProject busProject);

}
