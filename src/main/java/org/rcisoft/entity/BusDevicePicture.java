package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JiChao on 2019/4/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name ="bus_device_picture" )
public class BusDevicePicture {

    /**
     * 主键
     */
    @Column(name = "id" )
    @Id
    private String id;

    /**
     * 项目id
     */
    @Column(name = "id" )
    private String projectId;

    /**
     * 设备图片名称
     */
    @Column(name = "id" )
    private String name;

    /**
     * 图片路径
     */
    @Column(name = "id" )
    private String url;

}
