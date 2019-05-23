package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnSystemData {

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 这个设备每天24小时的参数值
     */
    private List<CodeValueAndHour> codeValueAndHours;
}
