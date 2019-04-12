package org.rcisoft.business.equipment.report.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:05
 **/
@Repository
public interface DeviceReportDao {

    /**
     * 根据月份查询数据信息
     */
    @Select("SELECT * FROM sys_data WHERE YEAR(create_time) = #{year} AND MONTH(create_time) = #{month} " +
            "and project_id = #{proId};")
    List<SysData> querySysDataByMonth(@Param("year") String year,@Param("month") String month,@Param("proId") String proId);

}
