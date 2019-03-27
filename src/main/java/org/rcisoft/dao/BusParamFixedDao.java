package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 9:53
 **/
@Repository
public interface BusParamFixedDao extends Mapper<BusParamFixed> {

    /**
     * 根据项目、设备、系统ID查询固定参数信息
     */
    @Select("SELECT * FROM bus_param_fixed \n" +
            "WHERE project_id = #{projectId}\n" +
            "AND device_id = #{deviceId}\n" +
            "AND system_id = #{systemId};")
    @ResultType(BusParamFixed.class)
    List<BusParamFixed> queryParamFixed(BusParamFixed busParamFixed);
}
