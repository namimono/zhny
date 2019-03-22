package org.rcisoft.business.energy.compare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/3/20.
 * 能耗标准 返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergyStandardResult {

    /**
     * 建议标准
     * 顺序：水、电、气
     */
    private List<BigDecimal> standardSuggest = new ArrayList<>();

    /**
     * 行业标准
     * 顺序：水、电、气
     */
    private List<BigDecimal> standardIndustry = new ArrayList<>();

    /**
     * 国家标准
     * 顺序：水、电、气
     */
    private List<BigDecimal> standardCountry = new ArrayList<>();

    /**
     * 今年能耗
     * 顺序：水、电、气
     */
    private List<BigDecimal> energyThisYear = new ArrayList<>();

    /**
     * 去年能耗
     * 顺序：水、电、气
     */
    private List<BigDecimal> energyLastYear = new ArrayList<>();

    /**
     * 今年颜色，0：正常，1：超出标准任意
     * 顺序：水、电、气
     */
    private List<String> colorThisYear = new ArrayList<>();

    /**
     * 去年颜色，0：正常，1：超出标准任意
     * 顺序：水、电、气
     */
    private List<String> colorLastYear = new ArrayList<>();

}
