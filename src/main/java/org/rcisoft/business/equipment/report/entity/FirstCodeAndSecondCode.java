package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GaoLiwei
 * @date 2019/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstCodeAndSecondCode {

    /**
     * 一级参数code
     */
    private String firstCode;

    /**
     * 二级参数code
     */
    private String secondCode;

    /**
     * 设备名称
     */
    private String deviceName;
}
