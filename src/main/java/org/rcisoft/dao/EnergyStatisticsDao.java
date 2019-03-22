package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.energy.compare.entity.CompareParam;
import org.rcisoft.business.energy.overview.entity.OverviewParam;
import org.rcisoft.entity.EnergyStatistics;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by JiChao on 2019/3/15.
 */
@Repository
public interface EnergyStatisticsDao extends Mapper<EnergyStatistics> {

    /** -----------------------------用能概况---------------------------------------- */
    /**
     * 查询当日费用
     * @param overviewParam year, month, day, projectId
     * @return
     */
    @Select("<script>select sum(money_water) money_water, sum(money_elec) money_elec, sum(money_gas) money_gas from energy_statistics where time_year = #{year} and time_month = #{month} and time_day = #{day} and project_id = #{projectId}</script>")
    @ResultType(EnergyStatistics.class)
    EnergyStatistics queryEnergyForDay(OverviewParam overviewParam);

    /**
     * 查询当月费用
     * @param overviewParam year, month, projectId
     * @return
     */
    @Select("<script>select sum(money_water) money_water, sum(money_elec) money_elec, sum(money_gas) money_gas from energy_statistics where time_year = #{year} and time_month = #{month} and project_id = #{projectId}</script>")
    @ResultType(EnergyStatistics.class)
    EnergyStatistics queryEnergyForMonth(OverviewParam overviewParam);

    /**
     * 查询每天经过的时间的能耗
     * @param overviewParam year, month, hour, projectId
     * @return
     */
    @Select("<script>select sum(money_water) money_water, sum(money_elec) money_elec, sum(money_gas) money_gas, time_day from energy_statistics where time_year = #{year} and time_month = #{month} and time_hour &lt; #{hour} and project_id = #{projectId} group by time_day</script>")
    @ResultType(EnergyStatistics.class)
    List<EnergyStatistics> queryRank(OverviewParam overviewParam);

    /**
     * 查询分时费用，24小时数据
     * @param overviewParam year, month, hour, projectId
     * @return
     */
    @Select("<script>select sum(money_water) money_water, sum(money_elec) money_elec, sum(money_gas) money_gas, time_hour from energy_statistics where time_year = #{year} and time_month = #{month} and time_day = #{day} and project_id = #{projectId} group by time_hour order by time_hour asc</script>")
    @ResultType(EnergyStatistics.class)
    List<EnergyStatistics> queryPriceForDay(OverviewParam overviewParam);
    /** -----------------------------用能概况---------------------------------------- */

    /** -----------------------------用能比较---------------------------------------- */
    /**
     * 今日，本月 水电气 用量
     * 没有日期参数，查本月
     * 有日期，查今日
     * @param compareParam year, month, day, projectId
     * @return
     */
    @Select("<script>select sum(energy_water) energy_water, sum(energy_elec) energy_elec, sum(energy_gas) energy_gas " +
            "from energy_statistics " +
            "where time_year = #{year} and time_month = #{month} and project_id = #{projectId} " +
            "<if test = \"day != null\"> and time_day = #{day} </if></script>")
    @ResultType(EnergyStatistics.class)
    EnergyStatistics queryEnergyDayAndMon(CompareParam compareParam);

    /** -----------------------------用能比较---------------------------------------- */

}
