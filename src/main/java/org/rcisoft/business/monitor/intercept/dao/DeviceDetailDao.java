package org.rcisoft.business.monitor.intercept.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.DeviceDetail;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:44 2019/3/28
 */
@Repository
public interface DeviceDetailDao extends Mapper<DeviceDetail>{
	/**
	 * 查询设备实时数据，并判断设备状态
	 * @param deviceId
	 * @return
	 */
    @Select("<script>select  s1.coding as condingFirst,s2.coding as codingSecond,s2.value,s2.unit,s2.name,s2.min_value as minVal,s2.max_value as maxVal,s2.energy_type_id as energyTypeId" +
            ",s2.elec_type as elecType,s2.content " +
            "from bus_param_second s2 left join bus_param_first s1 on s2.param_first_id = s1.id and s2.device_id = #{deviceId}</script>")
    public List<DeviceDetail> queryDeviceDetail(String deviceId);
}
