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
@Table( name ="energy_save_reduction" )
public class EnergySaveReduction {

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
     * 年
     */
    @Column(name = "time_year" )
    private Integer timeYear;

    /**
     * 碳减排量
     */
    @Column(name = "money" )
    private BigDecimal money;

}
