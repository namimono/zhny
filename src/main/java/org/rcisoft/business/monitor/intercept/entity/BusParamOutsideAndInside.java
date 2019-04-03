package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:20 2019/4/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusParamOutsideAndInside {
    /**
     * 一级参数
     */
    private String codingFirst;
    /**
     * 二级参数
     */
    private String codingSecond;
}
