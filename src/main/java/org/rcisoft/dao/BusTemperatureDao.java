package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTemperature;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/22 10:45
 **/
@Repository
public interface BusTemperatureDao extends Mapper<BusTemperature> {

    @Select("SELECT create_time, temperature FROM bus_temperature WHERE date_format(create_time, \"%Y-%m-%d\") = #{time} " +
            "AND coding = #{coding} order by create_time asc")
    @ResultType(BusTemperature.class)
    List<BusTemperature> queryTemp(@Param("time") String time, @Param("coding") String coding);

}
