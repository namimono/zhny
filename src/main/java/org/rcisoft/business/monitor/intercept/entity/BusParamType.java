package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:46 2019/4/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusParamType {
    /**
     * 一级参数
     */
    private String codingFirst;
    /**
     * 二级参数
     */
    private String codingSecond;
    /**
     * 类型
     */
    private Integer type;
}
