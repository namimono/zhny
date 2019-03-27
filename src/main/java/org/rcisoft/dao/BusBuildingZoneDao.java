package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusBuildingZone;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:27
 **/
@Repository
public interface BusBuildingZoneDao extends Mapper<BusBuildingZone>{

    /**
     * 获取建筑分区(气候分区)信息
     */
    @Select("SELECT * FROM bus_building_zone;")
    @ResultType(BusBuildingZone.class)
    List<BusBuildingZone> queryBuildZoneInfo();
}
