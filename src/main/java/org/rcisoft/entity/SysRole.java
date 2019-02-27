package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by JiChao on 2018/6/5.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_role")
public class SysRole {

    @Id
    private String id;
    private String name;

}
