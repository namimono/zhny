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

    /**
     * 根据城市code查询时间段内的整点温度信息
     */
    @Select("SELECT * FROM bus_temperature WHERE create_time BETWEEN #{beginTime} AND #{endTime}\n" +
            "AND RIGHT(create_time,5)='00:00' AND coding = #{code};")
    @ResultType(BusTemperature.class)
    List<BusTemperature> queryTemperature(@Param("beginTime") String beginTime
            ,@Param("endTime") String endTime,@Param("code") String code);
}
