package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author 土豆儿
 * @date 2019/3/15 16:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleParamAndParam {

    /**
     * 主键
     */
    private String id;

    /**
     * 标题表id
     */
    private String titleId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 一级参数id
     */
    private String paramFirstId;

    /**
     * 一级参数名称
     */
    private String paramFirstName;

    /**
     * 一级参数coding
     */
    private String paramFirstCoding;

    /**
     * 二级参数id
     */
    private String paramSecondId;

    /**
     * 二级参数名称
     */
    private String paramSecondName;

    /**
     * 二级参数coding
     */
    private String paramSecondCoding;
}
