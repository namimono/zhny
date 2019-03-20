package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 14:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClimateAdaptation {

    /**
     * 实际温度
     */
    private List<Object> real;

    /**
     * 最优温度
     */
    private List<Object> optimum;
}
