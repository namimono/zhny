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
@Table ( name ="mid_indoor_param_first" )
public class MidIndoorParamFirst {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 室内环境id
	 */
   	@Column(name = "indoor_id" )
	private String indoorId;

	/**
	 * 一级参数id
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

	/**
	 * 室内环境id相同时，排序字段
	 */
   	@Column(name = "sequence" )
	private Integer sequence;

}
