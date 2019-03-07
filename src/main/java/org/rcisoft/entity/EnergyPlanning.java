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
@Table ( name ="energy_planning" )
public class EnergyPlanning {


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
	 * 一级参数id，有两个参数中间使用 / 分隔
	 */
   	@Column(name = "param_first_ids" )
	private String paramFirstIds;

	/**
	 * 二级参数id，有两个参数中间使用 / 分隔
	 */
   	@Column(name = "param_second_ids" )
	private String paramSecondIds;

	/**
	 * 数值，有两个参数中间使用 / 分隔
	 */
   	@Column(name = "param_values" )
	private String paramValues;

	/**
	 * 其他数值，有两个参数中间使用 / 分隔
	 */
   	@Column(name = "other_values" )
	private String otherValues;

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
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 计划开始时间
	 */
   	@Column(name = "start_time" )
	private Date startTime;

	/**
	 * 计划结束时间
	 */
   	@Column(name = "end_time" )
	private Date endTime;

}
