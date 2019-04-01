package org.rcisoft.business.equipment.device.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusInspection;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/29.
 * 巡检记录返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InspectionResult extends BusInspection {

    /**
     * 巡检员姓名
     */
    private String inspectorName;

}
