package org.rcisoft.business.system.roleuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色的一级权限（菜单）
 * @author GaoLiwei
 * @date 2019/4/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionFirst {

    /**
     * 一级菜单id
     */
    private String id;

    /**
     * 一级菜单名字
     */
    private String name;

    /**
     * 表示是否已经拥有这个菜单的查看权（1表示拥有，0表示没有）
     */
    private Integer flag;

    /**
     * 这个一级菜单下的二级菜单
     */
    List<RolePermissionSecond> rolePermissionSecondList;
}
