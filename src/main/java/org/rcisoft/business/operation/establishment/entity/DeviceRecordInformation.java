package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  将设备的计划编制记录信息按照十分钟一次处理，保存在这个实体中
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRecordInformation {

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
