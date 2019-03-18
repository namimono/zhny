package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 土豆儿
 * @date 2019/3/18 11:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariableAndParam {

    /**
     * 主键
     */
    private String id;

    /**
     * 变量（公式中需要替换的值，如：N1+N2中的N1）
     */
    private String variable;

    /**
     * 一级参数表id
     */
    private String paramFirstId;

    /**
     * 二级参数表id
     */
    private String paramSecondId;

    /**
     * 二级参数表coding
     */
    private String paramSecondCoding;

    /**
     * 公式表主键
     */
    private String formulaId;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 创建时间
     */
    private Date createTime;
}
