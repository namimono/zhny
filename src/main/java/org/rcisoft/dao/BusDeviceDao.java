package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
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
}
