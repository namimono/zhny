package org.rcisoft.business.whole.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:15 2019/4/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetail {
    /**
     * 项目主键
     */
    private String id;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 上线时间
     */
    private Date createTime;
    /**
     * 建筑位置
     */
    private String buildingLocal;
    /**
     * 建筑类型
     */
    private String buildingType;
    /**
     * 建筑面积
     */
    private BigDecimal buildingArea;
}
