package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 土豆儿
 * @date 2019/4/22 10:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleAndSysName {

    /**
     * 主键
     */
    private String id;

    /**
     * 标题名称
     */
    private String name;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 子系统id
     */
    private String systemId;

    /**
     * 子系统name
     */
    private String systemName;
}
