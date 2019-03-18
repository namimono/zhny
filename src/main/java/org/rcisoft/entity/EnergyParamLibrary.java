package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="energy_param_library" )
public class EnergyParamLibrary {


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
	 * 第一个主参数值
	 */
   	@Column(name = "main_value" )
	private BigDecimal mainValue;

	/**
	 * 第二个主参数值（一共只有2或3个参数时，为空）
	 */
   	@Column(name = "main_value2" )
	private BigDecimal mainValue2;

	/**
	 * 第一个副参数值
	 */
   	@Column(name = "param_value" )
	private BigDecimal paramValue;

	/**
	 * 第二个副参数值（一共只有2个参数时，为空）
	 */
   	@Column(name = "param_value2" )
	private BigDecimal paramValue2;


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
