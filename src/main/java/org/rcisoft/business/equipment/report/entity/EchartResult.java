package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 图标返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchartResult {

    /**
     * 公式名称
     */
    private String name;

    /**
     * 公式运算结果
     */
//    private List<String> dataList = new ArrayList<>(24);

    private Double[] data = new Double[24];

}
