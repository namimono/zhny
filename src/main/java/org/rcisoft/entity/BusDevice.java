package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
	 * 设备类型id
	 */
	@Column(name = "device_type_id" )
	private String deviceTypeId;

	/**
	 * 设备图片id
	 */
	@Column(name = "device_picture_id" )
	private String devicePictureId;

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
	private Integer runtime;

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

	/**
	 * 设备参数库添加数据的方式，0：手动，1：自动
	 */
   	@Column(name = "auto_type" )
	private Integer autoType;

	/**
	 * 排序字段
	 */
	@Column(name = "sequence" )
   	private Integer sequence;

	/**
	 * 是否接收网关数据，0：不接收，1：接收，默认1
	 */
	@Column(name = "receive" )
	private Integer receive;

}
