package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  将设备的计划编制记录的部分信息保存在这个实体中
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRecordInformation {

    /**
     * 计划编制表Id
     */
    private String energyPlanningRecordId;

    /**
     * 表示状态
     *     当设备参数的状态不同时，为3，前台显示红色
     *     当设备参数的状态相同，但是参数值不同时，为2，前台显示蓝色
     *     当设备参数的状态，参数都相同时，为1，前台显示绿色
     */
    Integer status = null;

    /**
     * 开始时间
     */
    Date startTime;

    /**
     * 结束时间
     */
    Date endTime;

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
}
