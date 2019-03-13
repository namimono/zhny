package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JiChao on 2019/3/13.
 * 登录页背景图
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name ="sys_image" )
public class SysImage {

    /**
     * 主键
     */
    @Column(name = "id" )
    @Id
    private Integer id;

    /**
     * 图片存储路径
     */
    @Column(name = "url" )
    private String url;

}
