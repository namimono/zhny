package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 土豆儿
 * @date 2019/3/19 14:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdaptiveParam {

    /**
     * 项目ID
     */
    private String proId;

    /**
     * 日期
     */
    private String time;

    /**
     *
     */
    private String code;
}
