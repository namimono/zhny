package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="energy_statistics" )
public class EnergyStatistics {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 设备id
	 */
   	@Column(name = "device_id" )
	private String deviceId;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 年
	 */
   	@Column(name = "time_year" )
	private Integer timeYear;

	/**
	 * 月
	 */
   	@Column(name = "time_month" )
	private Integer timeMonth;

	/**
	 * 日
	 */
   	@Column(name = "time_day" )
	private Integer timeDay;

	/**
	 * 小时
	 */
   	@Column(name = "time_hour" )
	private Integer timeHour;

	/**
	 * 水能耗
	 */
   	@Column(name = "energy_water" )
	private BigDecimal energyWater;

	/**
	 * 电能耗
	 */
   	@Column(name = "energy_elec" )
	private BigDecimal energyElec;

	/**
	 * 气能耗
	 */
   	@Column(name = "energy_gas" )
	private BigDecimal energyGas;

	/**
	 * 水费用
	 */
   	@Column(name = "money_water" )
	private BigDecimal moneyWater;

	/**
	 * 电费用
	 */
   	@Column(name = "money_elec" )
	private BigDecimal moneyElec;

	/**
	 * 气费用
	 */
   	@Column(name = "money_gas" )
	private BigDecimal moneyGas;

}
