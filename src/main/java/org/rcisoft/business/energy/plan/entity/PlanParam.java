package org.rcisoft.business.energy.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/21.
 * 计划参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlanParam {

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 年月日
     */
    private Integer year, month, day;

    /**
     * 年月 2019-3 2019-10
     * or
     * 年月日 2019-3-1 2019-10-10
     */
    private String time;

}
