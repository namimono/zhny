package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.business.operation.establishment.entity.PlanList;
import org.rcisoft.business.operation.establishment.entity.PlanningDeviceInformation;
import org.rcisoft.business.operation.establishment.entity.ProIdAndDate;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
public interface EnergyPlanningService {

    /**
     *  根据项目Id跟时间查出计划编制设备信息
     * @param proIdAndDate
     * @return
     */
    List<PlanningDeviceInformation> listDevicePlanningRecord(ProIdAndDate proIdAndDate);

    /**
     *  根据设备Id查出设备的计划编制信息
     * @param deviceId
     * @return
     */
    List<PlanList> listPlanList(String deviceId);
}
