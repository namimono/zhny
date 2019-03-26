package org.rcisoft.business.energy.emission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/25.
 * 参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmissionParam {

    private String projectId;

    private Integer year, month, day;

}
