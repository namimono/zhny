package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.base.util.SpecialBatchMapper;
import org.rcisoft.business.equipment.report.entity.ParamFirst;
import org.rcisoft.entity.BusParamFirst;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 15:46
 **/
@Repository
public interface BusParamFirstDao extends Mapper<BusParamFirst>, SpecialBatchMapper<BusParamFirst> {

    /**
     * 根据设备ID查询一级参数信息
     */
    @Select("SELECT * FROM bus_param_first WHERE device_id = #{deviceId};")
    @ResultType(BusParamFirst.class)
    List<BusParamFirst> queryParamFirstByDevId(@Param("deviceId") String deviceId);

    /**
     * 根据项目id查询所有一级参数
     * @param projectId
     * @return
     */
    @Select("select id, name, coding, device_id from bus_param_first where project_id = #{projectId}")
    @ResultType(ParamFirst.class)
    List<ParamFirst> queryParamFirstByProjectId(@Param("projectId") String projectId);

}
