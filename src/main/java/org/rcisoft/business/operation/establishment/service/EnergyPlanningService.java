package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.business.operation.establishment.entity.*;
import org.rcisoft.entity.EnergyPlanningRecord;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
public interface EnergyPlanningService {

    /**
     *  根据项目Id跟时间查出计划编制设备信息
     * @param conditionDto
     * @return
     */
    List<PlanningDeviceInformation> listDevicePlanningRecord(ConditionDto conditionDto);

    /**
     *  根据设备Id查出设备的计划编制信息
     * @param conditionDto
     * @return
     */
    List<PlanList> listPlanList(ConditionDto conditionDto);





    /**
     *  根据项目ID,时间，删除当天的计划编制
     * @param conditionDto
     * @return
     */
    Integer deletePlanTheDayByProIdAndDate(ConditionDto conditionDto);


    /**
     *  复制计划到某一天
     * @param conditionDto
     * @return
     */
    Integer copyPlanning(ConditionDto conditionDto);

    /**
     *  根据设备Id,查出设备Id的参数名称
     * @param conditionDto
     * @return
     */
    DeviceParamName listDeviceParamNameByDevId(ConditionDto conditionDto);

    /**
     *  根据计划编制记录查询记录信息
     * @param conditionDto
     * @return
     */
    EnergyPlanningRecord getEnergyPlanningRecordById(ConditionDto conditionDto);

}
