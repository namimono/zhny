package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  点击设备，显示计划列表用到的信息
 * @author GaoLiwei
 * @date 2019/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanList {

    /**
     * 计划编制记录表Id
     */
    String energyPlanningRecordId;

    /**
     * 设备ID
     */
    String deviceId;

    /**
     * 设备名称
     */
    String deviceName;

    /**
     * 开始时间
     */
    Date startTime;

    /**
     * 结束时间
     */
    Date endTime;


}
