package org.rcisoft.business.equipment.fault.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusMalfunction;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FaultResult extends BusMalfunction {

    /**
     * 设备名称
     */
    public String deviceName;

    /**
     * 设备类型名称
     */
    public String typeFirstName;

}
