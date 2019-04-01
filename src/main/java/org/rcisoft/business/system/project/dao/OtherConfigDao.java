package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusParamSecond;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/14 15:02
 **/
@Repository
public interface OtherConfigDao {

    /**
     * 根据设备ID查询二级参数信息
     */
    @Select("SELECT * FROM bus_param_second WHERE device_id = #{deviceId}\n" +
            "AND first_sign IN (1,2,3,4)\n" +
            "ORDER BY first_sign ASC;")
    @ResultType(BusParamSecond.class)
    List<BusParamSecond> queryParamsSecondByDevId(@Param("deviceId") String deviceId);

}
