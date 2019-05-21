package org.rcisoft.business.equipment.maintain.service;

import org.rcisoft.business.equipment.maintain.entity.MaintainPlanResult;
import org.rcisoft.business.equipment.maintain.entity.MaintenanceAndDevTypeId;
import org.rcisoft.business.equipment.maintain.entity.ScheduleResult;
import org.rcisoft.entity.BusMaintenance;

import java.util.List;

/**
 * Created by JiChao on 2019/4/1.
 * 设备维护--养护计划
 */
public interface MaintainService {

    /**
     * 养护日程
     * @param projectId
     * @param year
     * @return
     */
    List<ScheduleResult> querySchedule(String projectId, Integer year);

    /**
     * 当日养护计划
     * @param projectId
     * @param year
     * @param month
     * @param day
     * @return
     */
    List<MaintainPlanResult> queryPlan(String projectId, Integer year, Integer month, Integer day);

    /**
     * 根据id查询养护计划
     * @param id
     * @return MaintenanceAndDevTypeId
     */
    MaintenanceAndDevTypeId getMaintenanceAndDevTypeId(String id);

    /**
     * 新增养护计划
     * @param busMaintenance
     * @return
     */
    Integer insertMaintenance(BusMaintenance busMaintenance);

    /**
     * 修改养护计划
     * @param busMaintenance
     * @return
     */
    Integer updateMaintenance(BusMaintenance busMaintenance);

    /**
     * 删除养护计划
     * @param id
     * @return
     */
    Integer deleteMaintenance(String id);

}
