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
	 * id
	 */
	private String id;
	/**
	 * 项目名称
	 */
	private String projname;

	/**
	 * 建筑类型
	 */
	private String buildingType;

	/**
	 * 建筑面积
	 */
	private BigDecimal buildingArea;

	/**
	 * 建筑地址
	 */
	private String buildingLocal;

	/**
	 * 节能潜力
	 */
	private BigDecimal energyPotential;

	/**
	 * 能耗水平
	 */
	private BigDecimal energyCost;

	/**
	 * 项目创建时间
	 */
	private Date createTime;



	/**
	 * 改造内容
	 */
	private String saveContent;

	/**
	 * 工程造价
	 */
	private String saveCost;


	/**
	 * 节能量估算
	 */
	private String saveEstimate;

	/**
	 * 工程造价认定员姓名
	 */
	private String saveCostName;

	/**
	 * 工程造价认定员执业资质
	 */
	private String saveCostQualification;

	/**
	 * 工程造价认定员从业时间
	 */
	private Date saveCostEmploymentTime;

	/**
	 * 工程造价认定员项目业绩
	 */
	private String saveCostPerformance;

	/**
	 * 节能认定员姓名
	 */
	private String saveEnergyName;

	/**
	 * 节能认定员执业资质
	 */
	private String saveEnergyQualification;

	/**
	 * 节能认定员从业时间
	 */
	private Date saveEnergyEmploymentTime;

	/**
	 * 节能认定员项目业绩
	 */
	private String saveEnergyPerformance;

}
