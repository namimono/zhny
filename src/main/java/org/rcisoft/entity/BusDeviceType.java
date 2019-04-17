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
@Table( name ="bus_device_type" )
public class BusDeviceType {

    /**
     * 主键
     */
    @Column(name = "id" )
    @Id
    private String id;

    /**
     * 设备类型名称
     */
    @Column(name = "name" )
    private String name;

}
