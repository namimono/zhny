package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:19 2019/3/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {

    /**
     * 设备id
     */
    private String id;
    /**
     * 设备名称
     */
    private String  name;
    /**
     * 设备运行时间（ms）
     */
    private long runtime;
    /**
     * 运行状态 0:运行正常 1：运行异常
     */
    @Value(value = "0")
    private int status;
    /**
     * 字符串显示运行时长
     */
    private String time;

}
