package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:35 2019/3/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyParam {
    /**
     * 一级参数
     */
    private String codingFirst;
    /**
     * 二级参数
     */
    private String codingSecond;
    /**
     * 能源分类
     */
    private int elecType;
}
