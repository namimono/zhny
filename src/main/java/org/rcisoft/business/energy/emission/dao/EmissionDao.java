package org.rcisoft.business.energy.emission.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.energy.emission.entity.EmissionParam;
import org.rcisoft.business.energy.emission.entity.EmissionStatisticsResult;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JiChao on 2019/3/25.
 * 能耗管理--用能比较
 */
@Repository
public interface EmissionDao {

    /**
     * 查询累计减排量
     * @param emissionParam projectId，year
     * @return
     */
    @Select("<script>select carbon from energy_carbon_reduction where project_id = #{projectId} and time_year = #{year}</script>")
    @ResultType(BigDecimal.class)
    BigDecimal queryCarbonReduction(EmissionParam emissionParam);

    /**
     * 查询日排放量
     * @param emissionParam projectId，year，month，day
     * @return
     */
    @Select("<script>select sum(carbon) from energy_carbon_real where project_id = #{projectId} and time_year = #{year} and time_month = #{month} and time_day = #{day}</script>")
    @ResultType(BigDecimal.class)
    BigDecimal queryCarbonDay(EmissionParam emissionParam);

    /**
     * 查询月排放量
     * @param emissionParam projectId，year，month
     * @return
     */
    @Select("<script>select sum(carbon) from energy_carbon_real where project_id = #{projectId} and time_year = #{year} and time_month = #{month}</script>")
    @ResultType(BigDecimal.class)
    BigDecimal queryCarbonMonth(EmissionParam emissionParam);

    /**
     * 查询本年排放量
     * @param emissionParam projectId，year
     * @return
     */
    @Select("<script>select sum(carbon) from energy_carbon_real where project_id = #{projectId} and time_year = #{year}</script>")
    @ResultType(BigDecimal.class)
    BigDecimal queryCarbonYear(EmissionParam emissionParam);

    /**
     * 查询日排放强度
     * @param emissionParam projectId, year, month, day
     * @return
     */
    @Select("<script>select sum(carbon) carbon, time_hour time from energy_carbon_real where project_id = #{projectId} and time_year = #{year} and time_month = #{month} and time_day = #{day} group by time_hour order by time_hour asc</script>")
    @ResultType(EmissionStatisticsResult.class)
    List<EmissionStatisticsResult> queryEmissionStatisticsDay(EmissionParam emissionParam);

    /**
     * 查询月排放强度
     * @param emissionParam projectId, year, month
     * @return
     */
    @Select("<script>select sum(carbon) carbon, time_day time from energy_carbon_real where project_id = #{projectId} and time_year = #{year} and time_month = #{month} group by time_day order by time_day asc</script>")
    @ResultType(EmissionStatisticsResult.class)
    List<EmissionStatisticsResult> queryEmissionStatisticsMonth(EmissionParam emissionParam);

}
