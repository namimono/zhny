package org.rcisoft.business.whole.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:12 2019/4/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectHome {
    /**
     * 项目主键
     */
    private String Id;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 项目上线时间
     */
    private Date createTime;
    /**
     * 项目建筑位置
     */
    private String buildingLocal;
    /**
     * 项目建筑类型
     */
    private String buildingType;
    /**
     * 项目建筑面积
     */
    private BigDecimal buildingArea;
    /**
     * 碳排放量
     */
    private BigDecimal carbon;
    /**
     * 水费用
     */
    private BigDecimal moneyWater;
    /**
     * 电费用
     */
    private BigDecimal moneyElec;
    /**
     * 气费用
     */
    private BigDecimal moneyGas;
    /**
     * 项目总费用
     */
    private BigDecimal moneySum;
    /**
     * 是否故障 0:正常 1：故障
     */
    private int status;
    /**
     * 是否通讯故障 0：正常 1：故障
     */
    private int communicationStatus;
}
