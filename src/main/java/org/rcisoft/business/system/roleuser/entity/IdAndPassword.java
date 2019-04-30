package org.rcisoft.business.system.roleuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GaoLiwei
 * @date 2019/4/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdAndPassword {

    /**
     * 表Id
     */
    private String id;

    /**
     * 角色标识
     */
    private String flag;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
