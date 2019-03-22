package org.rcisoft.business.energy.compare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/20.
 * 能耗管理--用能比较
 * 参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompareParam {

    private Integer year, month, day;

    private String projectId;

}
