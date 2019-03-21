package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:52 2019/3/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceParam {
    /**
     * 二级参数名称
     */
    private String nameSecond;
    /**
     * 一级参数
     */
    private String codingFirst;
    /**
     * 二级参数
     */
    private String codingSecond;

}
