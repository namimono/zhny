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
@Table ( name ="sys_system" )
public class SysSystem {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 系统类型名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 排序字段
	 */
   	@Column(name = "sequence" )
	private Integer sequence;

}
