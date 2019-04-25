package org.rcisoft.business.system.roleuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GaoLiwei
 * @date 2019/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectName {

    /**
     * 项目Id
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 表示是否已经拥有这个项目（1表示拥有，0表示没有）
     */
    private Integer flag;
}
