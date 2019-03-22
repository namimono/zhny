package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.DeviceInfo;
import org.rcisoft.business.monitor.intercept.entity.DeviceParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:57 2019/3/21
 */
public interface DeviceParamDao extends Mapper<DeviceParam> {
    /**
     * 查询设备一二级参数及二级名称
     * @param device_id
     * @return
     */
    @Select("<script>select bps.coding as coding_second ,bps.name as name_second,bpf.coding as coding_first from mid_device_param_second mdps,bus_param_second bps,bus_param_first bpf " +
            "where mdps.device_id = #{device_id} and mdps.param_second_id = bps.id and bps.param_first_id = bpf.id limit 4</script>")
    List<DeviceParam> queryDeviceParam(String device_id);

    /**
     * 查询设备列表标题
     * @return
     */
    @Select("<script>select name from bus_type_first</script>")
    List<String> queryDeviceTitle();

    /**
     * 查询当天日期中的故障设备
     * @return
     */
    @Select("<script>select device_id from bus_malfunction where to_days(create_time) = to_days(now())</script>")
    List<String> queryMalfunction();

    @Select("<script>select id,name,runtime from bus_device where type_first_id = #{typeFirstId}</script>")
    List<DeviceInfo> queryDeviceInfo(String typeFirstId);
}
