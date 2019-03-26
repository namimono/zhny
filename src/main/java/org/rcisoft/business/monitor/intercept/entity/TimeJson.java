package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:02 2019/3/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeJson {
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 具体json参数
     */
    private String json;
}
