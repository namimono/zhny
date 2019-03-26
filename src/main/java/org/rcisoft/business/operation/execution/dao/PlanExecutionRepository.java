package org.rcisoft.business.operation.execution.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.execution.entity.DateAndNum;
import org.rcisoft.business.operation.execution.entity.MoneySum;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/21
 */
@Repository
public interface PlanExecutionRepository {

    /**
     *  根据项目Id,与月份时间，查出这个月每天的计划编制数量
     * @param  conditionDto
     * @return List<DateAndNum>
     */
    @Select("<script>SELECT create_time AS date, COUNT(*) AS  recordNum FROM `energy_planning_record` WHERE  " +
            " DATE_FORMAT(create_time,'%Y-%m') = DATE_FORMAT(#{conditionDto.monthDate},'%Y-%m') " +
            "  AND project_id =#{conditionDto.proId}  " +
            "  GROUP BY create_time ORDER BY  create_time ASC </script>")
    List<DateAndNum> listMonthPlanNum(@Param("conditionDto") ConditionDto conditionDto);


    /**
     *  根据项目Id，与时间，查出当天项目的总花费
     * @param conditionDto
     * @return MoneySum
     */
    @Select("<script>SELECT SUM(money_elec) AS sumMoneyElec, SUM(money_gas) AS sumMoneyGas " +
            " FROM energy_planning_cost WHERE create_time = #{conditionDto.date} AND project_id =#{conditionDto.proId}</script>")
    MoneySum getMoneySum(@Param("conditionDto") ConditionDto conditionDto);

}
