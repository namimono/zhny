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
@Table ( name ="bus_param_library" )
public class BusParamLibrary {


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
	 * 是否作为待选参数，1：是，0：否
	 */
   	@Column(name = "compare_sign" )
	private Integer compareSign;

	/**
	 * 4个参数时，作为第一个待选参数，1：第一个参数，0：其他参数
	 */
   	@Column(name = "first_sign" )
	private Integer firstSign;

	/**
	 * 设备id一致时，参数的顺序
	 */
	@Column(name = "sequence" )
	private Integer sequence;

}
