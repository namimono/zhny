package org.rcisoft.service.projMaintenance;

import com.github.pagehelper.PageInfo;
import org.rcisoft.entity.BusProject;
import org.rcisoft.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by 土豆儿 on 2019-3-4
 */
public interface ProjConfigService {

    /**
     * 查询全部项目表信息
     */
    List<Map<String,Object>> queryAllInfo();

//    /**
//     * 设置项目配置信息
//     */
//    int setProjConfig(String name,String province_id,String code,String city_id,String building_local,
//                      String online_team_id,String equipment_age,String inspect_ids,String building_id,String building_area,
//                      String building_coordinate,String offline_team_id,String building_name,String save_sign,String building_zone_id,
//                      String building_age,String user_id,String phones,String type,String id,String create_time);

    /**
     * 设置项目配置信息
     */
    int setProjConfig(BusProject busProject);

    /**
     * 获取省份信息及其ID
     */
    List<Map<String,Object>> queryProvinceInfo();

    /**
     * 根据省份ID获取城市信息及其code
     */
    List<Map<String,Object>> queryCityInfo(String provinceId);

    /**
     * 获取线下团队信息
     */
    List<Map<String,Object>> queryOutTeamInfo();

    /**
     * 获取线上团队信息
     */
    List<Map<String,Object>> queryOnTeamInfo();

    /**
     * 获取巡查员信息
     */
    List<Map<String,Object>> queryInspectorInfo();

    /**
     * 获取建筑类型信息
     */
    List<Map<String,Object>> queryBuildingInfo();

    /**
     * 获取建筑分区(气候分区)信息
     */
    List<Map<String,Object>> queryBuildZoneInfo();

    /**
     * 获取业主信息
     */
    List<Map<String,Object>> queryOwnerInfo();
}
