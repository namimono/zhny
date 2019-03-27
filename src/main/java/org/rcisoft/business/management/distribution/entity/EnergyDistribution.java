package org.rcisoft.business.management.distribution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyDistribution {

	/**
	 * 项目名称
	 */
	private String ProjName;


	/**
	 * 建筑类型
	 */
	private String buildingType;


	/**
	 * 建筑位置
	 */
	private String buildingLocal;

	/**
	 * 建筑坐标
	 */
	private String buildingCoordinate;


	/**
	 * 建筑年代(date)
	 */
	private Date buildingAge;

	/**
	 * 建筑年代(int)
	 */
	private int buildingYear;

	/**
	 * 建筑面积
	 */
	private BigDecimal buildingArea;

	/**
	 * 暖通设备年限(date)
	 */
	private Date equipmentAge;

	/**
	 * 暖通设备年限(date)
	 */
	private int equipmentYear;

	/**
	 * 所属气候带类型
	 */
	private String buildingZoneType;

	/**
	 * 能耗水平
	 */
	private BigDecimal energyNum;



	/**
	 * 水费用
	 */
	private BigDecimal sumWater;

	/**
	 * 电费用
	 */
	private BigDecimal sumElec;

	/**
	 * 气费用
	 */
	private BigDecimal sumGas;

}
