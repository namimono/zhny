package org.rcisoft.business.operation.execution.service;

import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.PlanningDeviceInformation;
import org.rcisoft.business.operation.execution.entity.DateAndNum;
import org.rcisoft.business.operation.execution.entity.MoneySum;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/21
 */
public interface PlanExecutionService {

    /**
     *  根据项目Id,时间查出计划执行页面右上角表格显示需要的信息
     * @param conditionDto
     * @return
     */
    List<PlanningDeviceInformation> listDevicePlanningInfoAndStatus(ConditionDto conditionDto);


    /**
     *  查询当前月份每天的计划编制数量
     * @param conditionDto
     * @return List<DateAndNum>
     */
    List<DateAndNum> listMonthPlanNum(ConditionDto conditionDto);

    /**
     *  根据时间，项目Id,查询当天的总花费
     * @param conditionDto
     * @return MoneySum
     */
    MoneySum getMoneySum(ConditionDto conditionDto);
}
