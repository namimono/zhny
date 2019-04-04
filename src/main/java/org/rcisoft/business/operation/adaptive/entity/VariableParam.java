package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

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

}
