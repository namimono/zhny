package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:14 2019/3/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyEcharts {
    /**
     * 一级参数
     */
    private String codingFirst;
    /**
     * 二级参数
     */
    private String codingSecond;
    /**
     * 二级参数名称
     */
    private String nameSecond;
}
