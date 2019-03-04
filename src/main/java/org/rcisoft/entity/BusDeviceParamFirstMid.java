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
@Table ( name ="bus_device_param_first_mid" )
public class BusDeviceParamFirstMid {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

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
	 * 设备id相同时，排序字段
	 */
   	@Column(name = "sequence" )
	private Integer sequence;

}
