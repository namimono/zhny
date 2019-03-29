package org.rcisoft.business.equipment.fault.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.fault.entity.FaultCountResult;
import org.rcisoft.business.equipment.fault.entity.FaultResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/29.
 * 设备维护--故障统计
 */
@Repository
public interface FaultDao {

    /**
     * 故障数量统计
     * @param projectId
     * @param typeFirstId
     * @param time
     * @return
     */
    @Select("<script>select count(1) `count`, date_format(m.create_time, \"%e\") time " +
            "from bus_malfunction m, bus_device d " +
            "where m.device_id = d.id and m.project_id = #{projectId} " +
            "<if test = \"typeFirstId != 0\">and d.type_first_id = #{typeFirstId} </if>" +
            "and date_format(m.create_time, \"%Y-%c\") = #{time} " +
            "group by date_format(m.create_time, \"%e\") " +
            "order by m.create_time asc</script>")
    @ResultType(FaultCountResult.class)
    List<FaultCountResult> queryFaultCount(@Param("projectId") String projectId, @Param("typeFirstId") String typeFirstId, @Param("time") String time);

    /**
     * 查询故障内容
     * @param projectId
     * @param typeFirstId
     * @param time
     * @return
     */
    @Select("<script>select m.*, d.name deviceName, f.name typeFirstName " +
            "from bus_malfunction m, bus_device d, bus_type_first f " +
            "where m.device_id = d.id " +
            "and d.type_first_id = f.id " +
            "and m.project_id = #{projectId} " +
            "<if test = \"typeFirstId != 0\">and d.type_first_id = #{typeFirstId} </if>" +
            "and date_format(m.create_time, \"%Y-%c\") = #{time} " +
            "order by m.create_time</script>")
    @ResultType(FaultResult.class)
    List<FaultResult> queryFaults(@Param("projectId") String projectId, @Param("typeFirstId") String typeFirstId, @Param("time") String time);

}
