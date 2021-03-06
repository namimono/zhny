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
@Table ( name ="energy_planning_cost" )
public class EnergyPlanningCost {


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
	 * 电能耗（功率）
	 */
   	@Column(name = "energy_elec" )
	private BigDecimal energyElec;

	/**
	 * 气能耗（用气速率）
	 */
   	@Column(name = "energy_gas" )
	private BigDecimal energyGas;

	/**
	 * 电花费
	 */
   	@Column(name = "money_elec" )
	private BigDecimal moneyElec;

	/**
	 * 气花费
	 */
   	@Column(name = "money_gas" )
	private BigDecimal moneyGas;

}
