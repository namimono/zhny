package org.rcisoft.business.equipment.maintain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusMaintenance;

/**
 * @author GaoLiwei
 * @date 2019/5/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceAndDevTypeId extends BusMaintenance {

    /**
     * 设备类型Id
     */
    private String deviceTypeId;

    /**
     * 设备类型名称
     */
    private String deviceTypeName;

}
