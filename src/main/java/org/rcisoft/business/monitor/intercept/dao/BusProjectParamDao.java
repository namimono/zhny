package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.BusProjectParam;
import org.rcisoft.business.monitor.intercept.entity.DeviceFixValue;
import org.rcisoft.business.monitor.intercept.entity.EnergyParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:30 2019/3/18
 */
public interface BusProjectParamDao extends Mapper<BusProjectParam> {
    /**
     * 查询设备一二级参数
     * @return
     */
    @Select("<script>select bp.id,bpf.coding as coding_first,bps.coding as coding_second from bus_param_first bpf,bus_param_second bps,bus_project bp " +
            "where bp.id = bpf.project_id and bps.param_first_id = bpf.id and bps.project_id = bp.id</script>")
    BusProjectParam queryParam();

    /**
     * 查询左侧边栏标题名称
     * @return
     */
    @Select("<script>select name from sys_system</script>")
    List<String> queryModelName();

    /**
     * 查询能源参数
     * @param deviceId
     * @return
     */
    @Select("<script>select ec.elec_type as elecType,bpf.coding as codingFirst,bps.coding as codingSecond " +
            "from energy_config ec,bus_param_first bpf,bus_param_second bps " +
            "WHERE ec.param_first_id = bpf.id and ec.param_second_id = bps.id and ec.energy_type_id = 2 and ec.device_id = #{deviceId}</script>")
    List<EnergyParam> queryEnergyParam(String deviceId);

    @Select("<script>select name,coding,unit,fix_value as fixValue from bus_param_fixed where device_id = #{deviceId}</script>")
    List<DeviceFixValue> queryDeviceFixParam(String deviceId);
}
