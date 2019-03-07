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
@Table ( name ="bus_type_second" )
public class BusTypeSecond {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 类型名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 图片路径
	 */
   	@Column(name = "url" )
	private String url;

	/**
	 * 一级设备类型id
	 */
   	@Column(name = "type_first_id" )
	private String typeFirstId;

	/**
	 * 子系统id
	 */
   	@Column(name = "system_id" )
	private String systemId;

}
