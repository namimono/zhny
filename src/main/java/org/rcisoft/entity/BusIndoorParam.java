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
@Table ( name ="bus_indoor_param" )
public class BusIndoorParam {


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
	 * 室内环境id
	 */
   	@Column(name = "indoor_id" )
	private String indoorId;

	/**
	 * 一级参数编码
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

	/**
	 * 二级参数编码
	 */
   	@Column(name = "param_second_id" )
	private String paramSecondId;

	/**
	 * 类型：1：温度，2：湿度，3：PM2.5，4：CO2
	 */
   	@Column(name = "type" )
	private Integer type;


}
