package org.rcisoft.business.equipment.fault.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/29.
 * 故障数量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FaultCountResult {

    /**
     * 数量
     */
    private Integer count;

    /**
     * 日期
     */
    private Integer time;

}
