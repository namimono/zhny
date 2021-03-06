package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.PositionInfo;
import org.rcisoft.entity.SysProvince;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/5 13:15
 **/
@Repository
public interface SysProvinceDao extends Mapper<SysProvince> {

    /**
     * 获取省份信息及其ID
     */
    @Select("SELECT a.id AS 'proId',a.name AS 'proName',\n" +
            "b.id AS 'cityId',b.name AS 'cityName',b.coding\n" +
            "FROM sys_province a,sys_city b\n" +
            "WHERE a.id = b.province_id;")
    @ResultType(PositionInfo.class)
    List<PositionInfo> queryProvinceInfo();
}
