package org.rcisoft.business.energy.overview.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.energy.overview.entity.EnergyStatisticsSplit;
import org.rcisoft.business.energy.overview.entity.OverviewParam;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by JiChao on 2019/3/19.
 * 能耗管理--用能概况
 */
@Repository
public interface OverviewDao {

    /**
     * 能耗拆分，水电气查询
     * @param overviewParam year, month, day, projectId
     * @param column 列名
     * @return
     */
    @Select("<script>select sum(${column}) energy, device_id from energy_statistics where time_year = #{overviewParam.year} and time_month = #{overviewParam.month} and time_day = #{overviewParam.day} and project_id = #{overviewParam.projectId} group by device_id</script>")
    @ResultType(EnergyStatisticsSplit.class)
    List<EnergyStatisticsSplit> queryEnergySplit(@Param("overviewParam") OverviewParam overviewParam, @Param("column") String column);

}
