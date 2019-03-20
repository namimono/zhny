package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	 * 1代表主参数1；2代表主参数2；3代表副参数；4代表副参数2。当只有2个或3个参数的时候，数值不变。
	 */
	@Column(name = "sequence" )
   	private Integer sequence;

}
