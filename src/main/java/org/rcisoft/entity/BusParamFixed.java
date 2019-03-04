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
@Table ( name ="bus_param_fixed" )
public class BusParamFixed {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 参数名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 参数编码
	 */
   	@Column(name = "coding" )
	private String coding;

	/**
	 * 单位
	 */
   	@Column(name = "unit" )
	private String unit;

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
	 * 系统id
	 */
   	@Column(name = "system_id" )
	private String systemId;

}
