package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.EnergyPlanningRecord;

/**
 *  接受数据库中查出来的设备计划信息
 * @author GaoLiwei
 * @date 2019/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevicePlanningFromDb extends EnergyPlanningRecord {

    /**
     * 设备名称
     */
    String deviceName;


    /**
     * 第一个主参数名称
     */
    String mainName;

    /**
     * 第二个主参数名称
     */
    String mainName2;

    /**
     * 第一个副参数名称
     */
    String paramName;

    /**
     * 第二个副参数名称
     */
    String paramName2;



}
