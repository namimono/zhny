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
@Table ( name ="sys_source" )
public class SysSource {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 编码（这个字段没用）
	 */
   	@Column(name = "coding" )
	private String coding;

}
