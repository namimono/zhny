package org.rcisoft.business.energy.emission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by JiChao on 2019/3/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmissionStatisticsResult {

    /**
     * 碳排放量
     */
    private BigDecimal carbon;

    /**
     * 小时 or 日期
     */
    private Integer time;

}
