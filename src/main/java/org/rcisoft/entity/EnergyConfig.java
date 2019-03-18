package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="energy_config" )
public class EnergyConfig {


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
	 * 一级参数id
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

	/**
	 * 二级参数id
	 */
   	@Column(name = "param_second_id" )
	private String paramSecondId;

	/**
	 * 能耗分类id
	 */
   	@Column(name = "energy_type_id" )
	private String energyTypeId;

	/**
	 * 当能耗分类id=2（电）时，0：电度，1：功率，默认是0'
	 */
	@Column(name = "elec_type" )
	private String elecType;

}
