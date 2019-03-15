package org.rcisoft.business.management.evaluateproj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssessment {


	/**
	 * 项目主键
	 */
	private String id;

	/**
	 * 项目名称
	 */
	private String name;

	/**
	 * 网关编号，多个，逗号分隔
	 */
	private String phones;

	/**
	 * 建筑类型
	 */
	private String buildingId;

	/**
	 * 建筑面积
	 */
	private BigDecimal buildingArea;

	/**
	 * 建筑地址
	 */
	private String buildingLocal;

	/**
	 * 建筑坐标
	 */
	private String buildingCoordinate;

	/**
	 * 建筑分区id
	 */
	private String buildingZoneId;

	/**
	 * 业主id，用户表中type=3的用户
	 */
	private String userId;

	/**
	 * 省id
	 */
	private String provinceId;

	/**
	 * 市id
	 */
	private String cityId;

	/**
	 * 城市code
	 */
	private String code;

	/**
	 * 建筑年代
	 */
	private Date buildingAge;

	/**
	 * 项目创建时间
	 */
	private Date createTime;

	/**
	 * 设备年代
	 */
	private Date equipmentAge;

	/**
	 * 节能潜力
	 */
	private BigDecimal energyPotential;

	/**
	 * 线上团队ID
	 */
	private String onlineTeamId;

	/**
	 * 线下团队ID
	 */
	private String offlineTeamId;

	/**
	 * 项目类型，1：自营 or 2：托管
	 */
	private Integer type;

	/**
	 * 巡检人员，id集合，逗号拼接
	 */
	private String inspectIds;

	/**
	 * 系统id集合，逗号拼接，表示属于此项目的子系统
	 */
	private String systemIds;

	/**
	 * 是否在线（1：线上  0：项目回收状态）
	 */
	private Integer online;

	/**
	 * 是否接收数据，0：不接，1：接收
	 */
	private Integer receive;

	/**
	 * 节能改造标示位，0：否，1：是
	 */
	private Integer saveSign;


	/**
	 * 项目主键
	 */
	private String projectId;

	/**
	 * 改造内容
	 */
	private String saveContent;

	/**
	 * 工程造价
	 */
	private String saveCost;

	/**
	 * 分享期
	 */
	private String saveShare;

	/**
	 * 分享方式
	 */
	private String saveMethod;

	/**
	 * 节能量估算
	 */
	private String saveEstimate;

	/**
	 * 工程造价认定人员id（认定员id）
	 */
	private String saveCostId;

	/**
	 * 节能认定人员id（认定员id）
	 */
	private String saveEnergyId;

}
