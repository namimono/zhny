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
@Table ( name ="bus_device" )
public class BusDevice {


	/**
	 * 设备主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;


	/**
	 * 设备名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 子系统类型id
	 */
   	@Column(name = "system_id" )
	private String systemId;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 厂家id
	 */
   	@Column(name = "factory_id" )
	private String factoryId;

	/**
	 * 一级设备类型id
	 */
   	@Column(name = "type_first_id" )
	private String typeFirstId;

	/**
	 * 二级设备类型id
	 */
   	@Column(name = "type_second_id" )
	private String typeSecondId;

	/**
	 * 设备位置
	 */
   	@Column(name = "location" )
	private String location;

	/**
	 * 型号
	 */
   	@Column(name = "model" )
	private String model;

	/**
	 * 设备信息（1期的设备参数）
	 */
   	@Column(name = "info" )
	private String info;

	/**
	 * 安装日期
	 */
   	@Column(name = "install_time" )
	private Date installTime;

	/**
	 * 运行时长
	 */
   	@Column(name = "runtime" )
	private BigDecimal runtime;

	/**
	 * 二维码存放路径
	 */
   	@Column(name = "qrcode_url" )
	private String qrcodeUrl;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

}