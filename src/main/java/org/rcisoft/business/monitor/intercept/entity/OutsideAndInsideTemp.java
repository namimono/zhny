package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:53 2019/3/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutsideAndInsideTemp {
    /**
     * 室内温度
     */
    private BigDecimal insideTemp;
    /**
     * 室内PM2.5
     */
    private BigDecimal insidePm;
    /**
     * 室内CO2排放量
     */
    private BigDecimal insideCo2;
    /**
     * 室内湿度
     */
    private BigDecimal insideHumidity;
    /**
     * 室外温度
     */
    private BigDecimal outsideTemp;
    /**
     * 室外PM2.5
     */
    private BigDecimal outsidePm;
    /**
     * 室外二氧化碳排放量
     */
    private BigDecimal outsideCo2;
    /**
     * 室外湿度
     */
    private BigDecimal outsideHumidity;
}
