package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/5/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Params {

    private String firstCode;

    private String secondCode;

    private String paramName;

    private String paramUnit;

    private Integer faultStatus;

    private BigDecimal minValue;

    private BigDecimal maxValue;

    private String content;

    private Integer energyType;

    private Integer elecType;

    private BigDecimal value;

}
