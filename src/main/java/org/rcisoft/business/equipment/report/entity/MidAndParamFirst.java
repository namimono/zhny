package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 土豆儿
 * @date 2019/3/25 9:41
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MidAndParamFirst {

    /**
     * 中间表主键
     */
    private String midId;
//
//    /**
//     * 项目id
//     */
//    private String projectId;

//    /**
//     * 设备id
//     */
//    private String deviceId;

    /**
     * 一级参数id
     */
    private String paramFirstId;

    /**
     * 设备id相同时，排序字段
     */
    private Integer sequence;

    /**
     * 设备or计量表or传感器 参数名称
     */
    private String paramName;

    /**
     * 设备or计量表or传感器 参数编码
     */
    private String coding;

//    /**
//     * 是否已经在使用（被关联），1：是，0：否（）
//     */
//    private Integer status;

    /**
     * 子系统id
     */
    private String systemId;

    /**
     * 参数来源id
     */
    private Integer sourceId;
}
