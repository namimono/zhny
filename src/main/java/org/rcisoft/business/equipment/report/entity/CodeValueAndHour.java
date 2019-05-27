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
public class CodeValueAndHour {

    /**
     * 小时
     */
    private Integer hour;

    /**
     * 二级参数值
     */
    private String secondCodeValue;

}
