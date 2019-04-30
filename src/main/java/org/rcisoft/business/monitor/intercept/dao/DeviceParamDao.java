package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.DeviceInfo;
import org.rcisoft.business.monitor.intercept.entity.DeviceParam;
import org.rcisoft.business.monitor.intercept.entity.EnergyEcharts;
import org.rcisoft.business.monitor.intercept.entity.TimeJson;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:57 2019/3/21
 */
@Repository
public interface DeviceParamDao extends Mapper<DeviceParam> {
    /**
     * 查询设备一二级参数及二级名称
     * @param device_id
     * @return
     */
    @Select("<script>select bps.coding as coding_second ,bps.name as name_second,bpf.coding as coding_first from bus_param_second bps,bus_param_first bpf " +
            " where bps.device_id = #{device_id} and bps.param_first_id = bpf.id limit 4 </script>")
    List<DeviceParam> queryDeviceParam(String device_id);

    /**
     * 查询设备列表标题
     * @return
     */
    @Select("<script>select name from bus_type_first</script>")
    List<String> queryDeviceTitle();


    /**
     * 查询当天日期中的故障设备
     * @param projectId
     * @param systemId
     * @return List<String>
     */
    @Select("<script>select device_id from bus_malfunction where to_days(create_time) = to_days(now()) and project_id = #{projectId} and system_id = #{systemId} and status = 0</script>")
    List<String> queryMalfunction(@Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 查询设备具体信息
     * @param typeFirstId
     * @return List<DeviceInfo>
     */
    @Select("<script>select id,name,runtime from bus_device where device_type_id = #{typeFirstId} and project_id = #{projectId} and system_id = #{systemId} </script>")
    List<DeviceInfo> queryDeviceInfo(@Param("typeFirstId") String typeFirstId, @Param("projectId") String projectId, @Param("systemId") String systemId);

    /**
     * 查询一级二级参数及二级名称（echart）
     */
    @Select("<script>select bpf.coding as codingFirst,bps.coding as codingSecond,bps.name as nameSecond " +
            "from bus_title_param btp,bus_param_first bpf,bus_param_second bps where btp.param_first_id = bpf.id " +
            "and btp.param_second_id = bps.id and  btp.title_id = #{titleId}</script>")
    List<EnergyEcharts> queryEnergyEchart(String titleId);

    /**
     * 查询整点原始数据（echart）
     */
    @Select("<script>select create_time as createTime,json from sys_data where to_days(create_time) = to_days(now())" +
            " and  RIGHT(create_time,5)='00:00'</script>")
    List<TimeJson> queryData();
}
