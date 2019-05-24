package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/4/3.
 * 公式参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VariableParam {

    /**
     * 一级code，二级code
     */
    private String codingFirst, codingSecond;

    /**
     * 变量
     */
    private String variable;

    /**
     * 公式id
     */
    private String formulaId;

}
