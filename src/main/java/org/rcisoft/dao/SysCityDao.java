package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.whole.head.entity.CityInfo;
import org.rcisoft.entity.BusTemperature;
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
     * 根据项目id查询城市代码
     * @param proId
     */
    @Select("<script>SELECT bp.code,sc.name as cityName FROM bus_project bp,sys_city sc " +
            "WHERE bp.id=#{proId} and bp.code = sc.coding</script>")
    CityInfo queryCityInfoByName(@Param("proId") String proId);

    /**
     * 查询所有城市code
     */
    @Select("<script>select code from bus_project group by code</script>")
    List<String> queryCoding();
    /**
     * 查询天气插入温度表
     */
    @Insert("<script><foreach collection = \"WeatherList\" item = \"item\" separator=\";\"> " +
            "insert into bus_temperature(id,create_time,temperature,coding,humidity,wind) " +
            "VALUES(#{item.id},#{item.createTime},#{item.temperature},#{item.coding},#{item.humidity},#{item.wind})</foreach></script>")
    Integer saveWeather(@Param("WeatherList")List<BusTemperature> WeatherList);

}
