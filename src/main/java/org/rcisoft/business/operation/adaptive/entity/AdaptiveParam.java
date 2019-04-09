package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/4/3.
 * 建筑负荷参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdaptiveParam {

    /**
     * 年月日
     */
    private String time;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 城市code
     */
    private String code;

    /**
     * 类型，1：来自sensor，2：来自公式
     */
    private Integer type;

    /**
     * 一级code，二级code
     */
    private String codingFirst, codingSecond;

    /**
     * 公式
     */
    private String formula;

    /**
     * 变量
     */
    private List<VariableParam> variableList = new ArrayList<>();

}
