package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JiChao on 2019/3/18.
 * 今日、昨日分时运行费用
 * echarts图表数据
 * 昨日 + 今日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceForDay {

    /**
     * 今日24小时
     */
    private List<BigDecimal> listToday;

    /**
     * 昨日24小时
     */
    private List<BigDecimal> listYestoday;

}
