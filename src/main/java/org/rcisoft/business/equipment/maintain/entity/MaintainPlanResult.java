package org.rcisoft.business.equipment.maintain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by JiChao on 2019/4/1.
 * 当日养护计划
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintainPlanResult {

    /**
     * 养护计划id
     */
    private String id;

    /**
     * 时间
     */
    private String time;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 负责人
     */
    private String principal;

}
