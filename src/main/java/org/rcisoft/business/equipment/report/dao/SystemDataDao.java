package org.rcisoft.business.equipment.report.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.report.entity.ParamSecondWithFirst;
import org.rcisoft.entity.SysData;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:18
 **/
@Repository
public interface SystemDataDao {

    /**
     * 查询二级参数信息及其对应的一级参数信息
     */
    @Select("SELECT a.id AS 'firstId',a.`name` AS 'firstName',a.coding AS 'firstCode',\n" +
            "b.id AS 'secondId',b.`name` AS 'secondName',b.coding AS 'secondCode'\n" +
            "FROM bus_param_first a,bus_param_second b\n" +
            "WHERE b.id IN (${paramSecondIds}) AND b.param_first_id = a.id;")
    List<ParamSecondWithFirst> querySecondWithFirst(@Param("paramSecondIds") String paramSecondId);


    /**
     * 根据项目ID，时间查出这个项目当天的网关数据
     *
     * @param projectId
     * @param date
     * @return List<SysData>
     */
    @Select("<script>SELECT * FROM sys_data WHERE project_id = #{projectId} \n" +
            "AND DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(#{date},'%Y-%m-%d')  </script>")
    List<SysData> listDataByProIdAndDate(@Param("projectId") String projectId, @Param("date") Date date);
}