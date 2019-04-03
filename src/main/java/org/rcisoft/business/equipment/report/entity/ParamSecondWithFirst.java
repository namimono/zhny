package org.rcisoft.business.equipment.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 土豆儿
 * @date 2019/4/3 16:27
 * 查询二级参数信息并关联其对应的一级参数信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamSecondWithFirst {

    /**
     * 一级参数ID
     */
    private String firstId;

    /**
     * 一级参数名称
     */
    private String firstName;

    /**
     * 一级参数code
     */
    private String firstCode;

    /**
     * 二级参数ID
     */
    private String secondId;

    /**
     * 二级参数名称
     */
    private String secondName;

    /**
     * 二级参数code
     */
    private String secondCode;

}
