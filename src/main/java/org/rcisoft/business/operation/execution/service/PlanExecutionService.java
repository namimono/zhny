package org.rcisoft.business.operation.execution.service;

import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.business.operation.establishment.entity.PlanningDeviceInformation;

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
}
