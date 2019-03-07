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
@Table ( name ="sys_indoor_type" )
public class SysIndoorType {


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
	 * 类型，1：室内，2：室外
	 */
   	@Column(name = "type" )
	private Integer type;

	/**
	 * 类型相同时的排序字段
	 */
   	@Column(name = "sequence" )
	private Integer sequence;

}
