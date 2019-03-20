package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/18.
 * 参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OverviewParam {

    /**
     * 年
     * 月
     * 日
     * 小时
     */
    private Integer year, month, day, hour;

    /**
     * 项目id
     */
    private String projectId;

}
