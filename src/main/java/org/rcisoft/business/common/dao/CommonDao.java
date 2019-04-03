package org.rcisoft.business.common.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/26.
 * 公共接口
 */
@Repository
public interface CommonDao {

    /**
     * 查询本项目中所有存在的一级设备类型
     * @param projectId
     * @return
     */
    @Select("<script>select f.* from bus_device d, bus_type_first f where d.type_first_id = f.id and d.project_id = #{projectId} group by f.id</script>")
    @ResultType(BusTypeFirst.class)
    List<BusTypeFirst> queryTypeFirst(@Param("projectId") String projectId);

    /**
     * 查询项目中的子系统字段
     * @param projectId
     * @return
     */
    @Select("<script>select system_ids from bus_project where id = #{projectId}</script>")
    @ResultType(String.class)
    String querySystemIds(@Param("projectId") String projectId);

    /**
     * 查询项目下的所有设备
     * @param projectId
     * @return
     */
    @Select("<script>select id, name from bus_device where project_id = #{projectId} <if test = \"typeFirstId != null\">and type_first_id = #{typeFirstId}</if></script>")
    @ResultType(BusDevice.class)
    List<BusDevice> queryDevices(@Param("projectId") String projectId, @Param("typeFirstId") String typeFirstId);

    /**
     * 查询项目所有的一级参数
     * @param projectId
     * @return
     */
    @Select("<script>select id, name, coding from bus_param_first where project_id = #{projectId} order by source_id asc</script>")
    @ResultType(BusParamFirst.class)
    List<BusParamFirst> queryParamFirsts(@Param("projectId") String projectId);

    /**
     * 查询一级参数的二级参数
     * @param paramFirstId
     * @return
     */
    @Select("<script>select id, name, coding from bus_param_second where param_first_id = #{paramFirstId}</script>")
    @ResultType(BusParamSecond.class)
    List<BusParamSecond> queryParamSeconds(@Param("paramFirstId") String paramFirstId);

}
