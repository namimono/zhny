package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.DeviceBriefInfo;
import org.rcisoft.entity.BusDevice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/6 17:22
 **/
@Repository
public interface BusDeviceDao extends Mapper<BusDevice> {

    /**
     * 根据系统类型查询设备信息
     */
    @Select("SELECT * FROM bus_device where system_id = #{systemId}")
    List<BusDevice> queryDeviceInfo(BusDevice busDevice);

    /**
     * 查询设备简要信息
     */
    @Select("SELECT a.id,a.info,a.location,\n" +
            "b.name AS 'type',c.name AS 'factoryName'\n" +
            "FROM bus_device a,bus_type_first b,bus_factory c\n" +
            "where a.system_id = #{systemId} AND a.project_id = #{projectId}\n" +
            "AND a.type_first_id = b.id AND a.factory_id = c.id")
    List<DeviceBriefInfo> queryDeviceBriefInfo(BusDevice busDevice);
}
