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
@Table ( name ="energy_planning_record" )
public class EnergyPlanningRecord {


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
	 * 第一个主参数，一级参数id
	 */
   	@Column(name = "main_first_id" )
	private String mainFirstId;

	/**
	 * 第一个主参数，二级参数id
	 */
   	@Column(name = "main_second_id" )
	private String mainSecondId;

	/**
	 * 第一个主参数值
	 */
   	@Column(name = "main_value" )
	private BigDecimal mainValue;

	/**
	 * 第二个主参数，一级参数id
	 */
   	@Column(name = "main_first_id2" )
	private String mainFirstId2;

	/**
	 * 第二个主参数，二级参数id
	 */
   	@Column(name = "main_second_id2" )
	private String mainSecondId2;

	/**
	 * 第二个主参数值
	 */
   	@Column(name = "main_value2" )
	private BigDecimal mainValue2;

	/**
	 * 第一个副参数值
	 */
   	@Column(name = "param_value" )
	private BigDecimal paramValue;

	/**
	 * 第二个副参数值
	 */
   	@Column(name = "param_value2" )
	private BigDecimal paramValue2;

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
