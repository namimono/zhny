package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name ="energy_save_plan" )
public class EnergySavePlan {

    /**
     * 主键
     */
    @Column(name = "id" )
    @Id
    private String id;

    /**
     * 项目id
     */
    @Column(name = "project_id" )
    private String projectId;

    /**
     * 月
     */
    @Column(name = "time_month" )
    private Integer timeMonth;

    /**
     * 日
     */
    @Column(name = "time_day" )
    private Integer timeDay;

    /**
     * 碳排放量
     */
    @Column(name = "money" )
    private BigDecimal money;

}
