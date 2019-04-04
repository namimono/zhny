package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
/**
 * Created by JiChao on 2019/4/3.
 * 建筑负荷返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BuildingResult {

    /**
     * 室外温度
     */
    BigDecimal[] tempArray = new BigDecimal[24];

    /**
     * 建筑负荷
     */
    BigDecimal[] buildingArray = new BigDecimal[24];

}
