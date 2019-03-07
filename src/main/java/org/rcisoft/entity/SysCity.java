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
@Table ( name ="sys_city" )
public class SysCity {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 城市（直辖市的区县）
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 城市code
	 */
   	@Column(name = "coding" )
	private String coding;

	/**
	 * 省份id
	 */
   	@Column(name = "province_id" )
	private String provinceId;

}
