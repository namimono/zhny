package org.rcisoft.business.management.distribution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusProject;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfomation extends BusProject {

	/**
	 * 项目能耗
	 */
	private BigDecimal energy;

	/**
	 * 建筑类型
	 */
	private String buildingTypeName;

	/**
	 * 建筑年代
	 */
	private Integer buildingAgeYear;

	/**
	 * 设备年代
	 */
	private Integer equipmentAgeYear;

	/**
	 * 建筑分区
	 */
	private String buildingZoneName;

	/**
	 * 省份
	 */
	private String provinceName;

	/**
	 * 城市
	 */
	private String cityName;

}
