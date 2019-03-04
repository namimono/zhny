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
@Table ( name ="bus_param_second" )
public class BusParamSecond {


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
	 * 一级id相同时的排序字段
	 */
   	@Column(name = "sequence" )
	private Integer sequence;

	/**
	 * 一级参数表id
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

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
	 * 参数来源id，也可作为排序字段
	 */
   	@Column(name = "source_id" )
	private Integer sourceId;

}
