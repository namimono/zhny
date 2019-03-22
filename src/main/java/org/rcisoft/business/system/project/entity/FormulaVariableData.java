package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 土豆儿
 * @date 2019/3/21 10:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormulaVariableData {

//    /**
//     * 公式名称
//     */
//    private String name;
//
//    /**
//     * 公式内容
//     */
//    private String formula;
//
//    /**
//     * 项目id
//     */
//    private String projectId;
//
//    /**
//     * 公式创建时间
//     */
//    private Date formulaCreateTime;

    /**
     * 变量表主键
     */
    private String variableId;

    /**
     * 变量（公式中需要替换的值，如：N1+N2中的N1）
     */
    private String variable;

//    /**
//     * 一级参数表id
//     */
//    private String paramFirstId;

    /**
     * 一级参数code
     */
    private String paramFirstCoding;

//    /**
//     * 二级参数表id
//     */
//    private String paramSecondId;

    /**
     * 二级参数code
     */
    private String paramSecondCoding;

    /**
     * 公式表主键
     */
    private String formulaId;

//    /**
//     * 变量创建时间
//     */
//    private Date variableCreateTime;
}
