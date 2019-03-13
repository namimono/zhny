package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author 土豆儿
 * @date 2019/3/13 16:31
 *
 * 通过能耗配置查询能耗分类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyTypeConfig {

    /**
     * 能耗分类主键
     */
    private String typeId;

    /**
     * 能耗分类名称
     */
    private String typeName;

    /**
     * 能耗配置主键
     */
    private String configId;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 一级参数id
     */
    private String paramFirstId;

    /**
     * 二级参数id
     */
    private String paramSecondId;

    /**
     * 能耗分类id
     */
    private String energyTypeId;
}
