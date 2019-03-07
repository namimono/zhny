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
@Table ( name ="bus_param_first" )
public class BusParamFirst {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 设备or计量表or传感器 参数名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 设备or计量表or传感器 参数编码
	 */
   	@Column(name = "coding" )
	private String coding;

	/**
	 * 是否已经在使用（被关联），1：是，0：否（）
	 */
   	@Column(name = "status" )
	private Integer status;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 子系统id
	 */
   	@Column(name = "system_id" )
	private String systemId;

	/**
	 * 参数来源id
	 */
   	@Column(name = "source_id" )
	private Integer sourceId;

}
