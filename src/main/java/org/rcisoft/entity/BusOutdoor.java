package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 12:27 2019/4/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus_outdoor")
public class BusOutdoor {
    /**
     * 主键
     */
    @Column(name = "id")
    @Id
    private String id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private String projectId;

    /**
     * 设备id
     */
    @Column(name = "device_id" )
    private String deviceId;

    /**
     * 一级参数id
     */
    @Column(name = "param_first_id")
    private String paramFirstId;

    /**
     * 二级参数id
     */
    @Column(name = "param_second_id")
    private String paramSecondId;

    /**
     * 类型
     */
    @Column(name = "type")
    private Integer type;
}
