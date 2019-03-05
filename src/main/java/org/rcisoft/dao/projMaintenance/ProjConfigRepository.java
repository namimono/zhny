package org.rcisoft.dao.projMaintenance;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:42
 **/

@Mapper
@Repository
public interface ProjConfigRepository {

    /**
     * 查询全部项目表信息
     */
    @Select("SELECT * FROM bus_project;")
    List<Map<String,Object>> queryAllInfo();

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
//            "(name,province_id,code,id,create_time) \n" +
//            "VALUES \n" +
//            "(#{name},#{province_id},#{code},#{id},#{create_time});\n")
    int setProjConfig(@Param("name")String name,@Param("province_id")String province_id,@Param("code")String code,
                      @Param("city_id")String city_id,@Param("building_local")String building_local,
                      @Param("online_team_id")String online_team_id,@Param("equipment_age")Date equipment_age,
                      @Param("inspect_ids")String inspect_ids,@Param("building_id")String building_id,
                      @Param("building_area")String building_area,@Param("building_coordinate")String building_coordinate,
                      @Param("offline_team_id")String offline_team_id,@Param("building_name")String building_name,
                      @Param("save_sign")String save_sign,@Param("building_zone_id")String building_zone_id,
                      @Param("building_age")Date building_age,@Param("user_id")String user_id,
                      @Param("phones")String phones,@Param("type")String type,
                      @Param("id")String id,@Param("create_time") Date create_time);

    /**
     * 获取省份信息及其ID
     */
    @Select("SELECT * FROM sys_province;")
    List<Map<String,Object>> queryProvinceInfo();

    /**
     * 根据省份ID获取城市信息及其code
     */
    @Select("SELECT * FROM sys_city WHERE province_id = #{provinceId};")
    List<Map<String,Object>> queryCityInfo(@Param("provinceId") String provinceId);

    /**
     * 获取线下团队信息
     */
    @Select("SELECT * FROM bus_team WHERE type = '2';")
    List<Map<String,Object>> queryOutTeamInfo();

    /**
     * 获取线上团队信息
     */
    @Select("SELECT * FROM bus_team WHERE type = '1';")
    List<Map<String,Object>> queryOnTeamInfo();

}
