package org.rcisoft.business.operation.establishment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  接受前台参数
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {
    /**
     * 项目Id
     */
    String proId;

    /**
     * 设备Id
     */
    String devId;

    /**
     * 计划编制记录表Id
     */
    String energyPlanningRecordId;

    /**
     * 时间，格式 yyyy-MM-dd
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    Date date;


    /**
     * 月份时间，格式 yyyy-MM
     */
    @JsonFormat(pattern="yyyy-MM",timezone="GMT+8")
    Date monthDate;

    /**
     * 要复制到某一天的时间，格式 yyyy-MM-dd
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    Date copyToDate;
}
