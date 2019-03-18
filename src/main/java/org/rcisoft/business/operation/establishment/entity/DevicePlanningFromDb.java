package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  接受数据库中查出来的设备计划信息
 * @author GaoLiwei
 * @date 2019/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevicePlanningFromDb {
    /**
     * 设备ID
     */
    String deviceId;

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
     * 第三个主参数名称
     */
    String paramName;

    /**
     * 第四个主参数名称
     */
    String paramName2;

    /**
     * 第一个主参数值
     */
    BigDecimal mainValue;

    /**
     * 第二个主参数值
     */
    BigDecimal mainValue2;

    /**
     * 第一个副参数值
     */
    BigDecimal paramValue;

    /**
     * 第二个副参数值
     */
    BigDecimal paramValue2;

    /**
     * 开始时间
     */
    Date startTime;

    /**
     * 结束时间
     */
    Date endTime;



}
