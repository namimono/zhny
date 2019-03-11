package org.rcisoft.vo.sysManagement.projMaintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Create by 土豆儿
 * Time：2019/3/8 11:09
 *
 * 省、城市联表查询实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PositionInfo {

    /**
     * 省份ID
     */
    private String proId;

    /**
     * 省份名称
     */
    private String proName;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市code
     */
    private String coding;

}
