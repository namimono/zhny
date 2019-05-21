package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by 土豆儿
 * Time：2019/3/8 10:22
 *
 * 项目简要信息联表查询实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBriefInfo {

    /**
     * 项目主键
     */
    private String proId;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 建筑地址
     */
    private String buildingLocal;

    /**
     * 建筑面积
     */
    private String buildingArea;

    /**
     * 建筑类型
     */
    private String buildTypeName;

    /**
     * 所属业主名称
     */
    private String userName;
    /**
     * 是否在线（1：线上  0：项目回收状态）
     */
    private Integer online;
    /**
     * 是否接收数据，0：不接，1：接收
     */
    private Integer receive;

}
