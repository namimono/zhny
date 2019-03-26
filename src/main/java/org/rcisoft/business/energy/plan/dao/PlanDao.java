package org.rcisoft.business.energy.plan.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.energy.plan.entity.MoneyAndTime;
import org.rcisoft.business.energy.plan.entity.PlanParam;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by JiChao on 2019/3/21.
 */
@Repository
public interface PlanDao {

    /**
     * 查询一个月每天的实际金额，只有电气金额（未来可能加上水）
     * @param planParam projectId, year, month
     * @return
     */
    @Select("<script>select sum(money_elec) moneyElec, sum(money_gas) moneyGas, time_day time from energy_statistics where project_id = #{projectId} and time_year = #{year} and time_month = #{month} group by time_day order by time_day asc</script>")
    @ResultType(MoneyAndTime.class)
    List<MoneyAndTime> queryMoneyRealDay(PlanParam planParam);

    /**
     * 查询一个月每天的计划金额，只有电气金额
     * @param planParam projectId, time
     * @return
     */
    @Select("<script>select money_elec moneyElec, money_gas moneyGas, DATE_FORMAT(create_time, \"%e\") time from energy_planning_cost where project_id = #{projectId} and DATE_FORMAT(create_time, \"%Y-%c\") = #{time} order by create_time asc</script>")
    @ResultType(MoneyAndTime.class)
    List<MoneyAndTime> queryMoneyPlanDay(PlanParam planParam);

    /**
     * 查询一年中每个月的实际金额，只有电气金额（未来可能加上水）
     * @param planParam projectId, year
     * @return
     */
    @Select("<script>select sum(money_elec) moneyElec, sum(money_gas) moneyGas, time_month time from energy_statistics where project_id = #{projectId} and time_year = #{year} group by time_month order by time_month asc</script>")
    @ResultType(MoneyAndTime.class)
    List<MoneyAndTime> queryMoneyRealMon(PlanParam planParam);

    /**
     * 查询一年中每个月的计划金额，只有电气金额
     * @param planParam projectId, year
     * @return
     */
    @Select("<script>select sum(money_elec) moneyElec, sum(money_gas) moneyGas, DATE_FORMAT(create_time, \"%c\") time from energy_planning_cost where project_id = #{projectId} and DATE_FORMAT(create_time, \"%Y\") = #{year} group by DATE_FORMAT(create_time, \"%c\") order by create_time asc</script>")
    @ResultType(MoneyAndTime.class)
    List<MoneyAndTime> queryMoneyPlanMon(PlanParam planParam);

    /**
     * 查询每小时实际金额（电气，未来可能+水）
     * @param planParam projectId, year, month, day
     * @return
     */
    @Select("<script>select sum(money_elec) moneyElec, sum(money_gas) moneyGas, time_hour time from energy_statistics where project_id = #{projectId} and time_year = #{year} and time_month = #{month} and time_day = #{day} group by time_hour order by time_hour asc</script>")
    @ResultType(MoneyAndTime.class)
    List<MoneyAndTime> queryMoneyRealHour(PlanParam planParam);

    /**
     * 查询计划金额
     * @param planParam projectId, time
     * @return
     */
    @Select("<script>select start_time, end_time, money_elec, money_gas from energy_planning_record where project_id = #{projectId} and create_time = #{time}</script>")
    @ResultType(EnergyPlanningRecord.class)
    List<EnergyPlanningRecord> queryMoneyPlanHour(PlanParam planParam);

}
