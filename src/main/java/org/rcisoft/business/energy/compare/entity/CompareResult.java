package org.rcisoft.business.energy.compare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/3/21.
 * 用能比较返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareResult {

    /**
     * 本月用量
     */
    private List<BigDecimal> energyList = new ArrayList<>();

    /**
     * 比较用量（同比 or 环比）
     */
    private List<BigDecimal> compareList = new ArrayList<>();

    /**
     * 差值
     */
    private List<BigDecimal> diffList = new ArrayList<>();

}
