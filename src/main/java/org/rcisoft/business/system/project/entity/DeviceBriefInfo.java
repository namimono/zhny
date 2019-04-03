package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author 土豆儿
 * @date 2019/3/11 16:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceBriefInfo {

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private String type;

    /**
     * 设备参数
     */
    private String info;

    /**
     * 型号
     */
    private String model;

    /**
     * 安装日期
     */
    private String installTime;

    /**
     * 设备厂家
     */
    private String factoryName;

    /**
     * 设备位置
     */
    private String location;

    /**
     * 系统名称
     */
    private String systemName;

//    /**
//     * 系统ID
//     */
//    private String systemId;


}
