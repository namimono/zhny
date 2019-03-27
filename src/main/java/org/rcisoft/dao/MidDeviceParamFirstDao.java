package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.report.entity.MidAndParamFirst;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 10:35
 **/
@Repository
public interface MidDeviceParamFirstDao extends Mapper<MidDeviceParamFirst> {

    /**
     * 根据设备ID查询一级参数信息
     */
    @Select("SELECT a.id AS 'midId',a.param_first_id AS 'paramFirstId',a.sequence,\n" +
            "b.name AS 'paramName',b.coding,b.system_id AS 'systemId',b.source_id AS 'sourceId'\n" +
            "FROM mid_device_param_first a,bus_param_first b\n" +
            "WHERE a.device_id = #{deviceId} AND a.param_first_id = b.id")
    List<MidAndParamFirst> queryFirstInfoByMid(@Param("deviceId") String deviceId);
}
