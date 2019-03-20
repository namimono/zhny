package org.rcisoft.business.operation.establishment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterNameId {


    /**
     * 第一个主参数名称
     */
    String mainName;

    /**
     * 第二个主参数名称
     */
    String mainName2;

    /**
     * 第一个副参数名称
     */
    String paramName;

    /**
     * 第二个副参数名称
     */
    String paramName2;

    /**
     * 第一个主参数值的一级参数Id
     */
    BigDecimal mainFirstId;

    /**
     * 第一个主参数值的二级参数Id
     */
    BigDecimal mainSecondId;

    /**
     * 第二个主参数值的一级参数Id
     */
    BigDecimal mainFirstId2;

    /**
     * 第二个主参数值的二级参数Id
     */
    BigDecimal mainSecondId2;




}
