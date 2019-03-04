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
	 * 一级参数id，第一个参数
	 */
   	@Column(name = "param_first_id_one" )
	private String paramFirstIdOne;

	/**
	 * 二级参数id，第一个参数
	 */
   	@Column(name = "param_second_id_one" )
	private String paramSecondIdOne;

	/**
	 * 第一个参数值
	 */
   	@Column(name = "value_one" )
	private BigDecimal valueOne;

	/**
	 * 一级参数id，第二个参数
	 */
   	@Column(name = "param_first_id_two" )
	private String paramFirstIdTwo;

	/**
	 * 二级参数id，第二个参数
	 */
   	@Column(name = "param_second_id_two" )
	private String paramSecondIdTwo;

	/**
	 * 第二个参数值
	 */
   	@Column(name = "value_two" )
	private BigDecimal valueTwo;

	/**
	 * 一级参数id，第三个参数
	 */
   	@Column(name = "param_first_id_three" )
	private String paramFirstIdThree;

	/**
	 * 二级参数id，第三个参数
	 */
   	@Column(name = "param_second_id_three" )
	private String paramSecondIdThree;

	/**
	 * 第三个参数值
	 */
   	@Column(name = "value_three" )
	private BigDecimal valueThree;

	/**
	 * 一级参数id，第四个参数
	 */
   	@Column(name = "param_first_id_four" )
	private String paramFirstIdFour;

	/**
	 * 二级参数id，第四个参数
	 */
   	@Column(name = "param_second_id_four" )
	private String paramSecondIdFour;

	/**
	 * 第四个参数值
	 */
   	@Column(name = "value_four" )
	private BigDecimal valueFour;

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

}
