package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author 土豆儿
 * @date 2019/3/12 9:17
 * 数据配置联表查询实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypeFirstAndSecond {

    /**
     * 一级设备ID
     */
    private String firstId;

    /**
     * 一级设备名称
     */
    private String firstName;

    /**
     * 二级设备ID
     */
    private String secondId;

    /**
     * 二级设备名称
     */
    private String secondName;

    /**
     * 二级设备关联一级设备ID
     */
    private String typeFirstId;

    /**
     * 二级设备url
     */
    private String url;

    /**
     * 二级设备关联系统ID
     */
    private String systemId;
}
