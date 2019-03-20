package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 14:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingAdaptation {

    /**
     * 根据公式计算后得出的数据
     */
    private List<Object> building;

    /**
     * 室外温度
     */
    private List<Object> temperature;
}
