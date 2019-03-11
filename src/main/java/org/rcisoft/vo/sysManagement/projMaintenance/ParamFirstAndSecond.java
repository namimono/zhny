package org.rcisoft.vo.sysManagement.projMaintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author 土豆儿
 * @date 2019/3/11 10:11
 *
 * 数据配置联表查询实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParamFirstAndSecond {

    /**
     * 一级参数编码
     */
    private String firstCoding;

    /**
     * 一级参数名称
     */
    private String firstName;

    /**
     * 一级参数所属系统
     */
    private String systemId;

    /**
     * 二级参数编码
     */
    private String secondCoding;

    /**
     * 二级参数名称
     */
    private String secondName;

    /**
     * 二级参数单位
     */
    private String unit;
}
