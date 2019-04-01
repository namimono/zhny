package org.rcisoft.business.equipment.device.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusDevice;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/29.
 * 设备信息返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeviceResult extends BusDevice {

    /**
     * 一级系统类型
     */
    private String typeFirstName;

    /**
     * 厂家名称
     */
    private String factoryName;

    /**
     * 子系统名称
     */
    private String systemName;

}
