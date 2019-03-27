package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysCity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 13:19
 **/
@Repository
public interface SysCityDao extends Mapper<SysCity> {

    /**
     * 根据省份ID获取城市信息及其code
     */
    @Select("SELECT * FROM sys_city WHERE province_id = #{provinceId};")
    @ResultType(SysCity.class)
    List<Map<String,Object>> queryCityInfo(@Param("provinceId") String provinceId);

    /**
     * 根据城市名称查询城市信息
     * @param name
     */
    @Select("SELECT * FROM sys_city WHERE name=#{name};")
    @ResultType(SysCity.class)
    SysCity queryCityInfoByName(@Param("name") String name);

}
