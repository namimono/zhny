package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.business.operation.establishment.entity.*;

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
     *  根据设备Id,与计划编制记录表Id，查出编辑计划列表时用到的信息
     * @param conditionDto
     * @return
     */
    List<DevicePlanningFromDb> listDevicePlanningByDevIdAndRecId(ConditionDto conditionDto);

    /**
     * 新增计划编辑记录时，根据设备Id，查出需要显示的设备的参数的名称，以及保存时需要用到的参数Id
     * @param conditionDto
     * @return
     */
    List<ParameterNameId> listDevicePlanningByDevId(ConditionDto conditionDto);

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

}
