package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 土豆儿
 * @date 2019/3/14 15:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryAndParam {

    /**
     * 参数库主键
     */
    private String libraryId;

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
     * 一级参数代码
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
     * 二级参数代码
     */
    private String paramSecondCoding;

    /**
     * 二级参数来源ID
     */
    private String sourceId;

    /**
     * 是否作为待选参数，1：是，0：否
     */
    private Integer compareSign;

    /**
     * 4个参数时，作为第一个待选参数，1：第一个参数，0：其他参数
     */
    private Integer firstSign;

    /**
     * 设备id一致时，参数的顺序
     */
    private Integer sequence;
}
