package org.rcisoft.business.whole.head.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:54 2019/4/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfo {
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市代码
     */
    private String code;
}
