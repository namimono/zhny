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
@Table ( name ="mid_param_second_indoor_type" )
public class MidParamSecondIndoorType {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 项目id
	 */
	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 二级参数id
	 */
   	@Column(name = "param_second_id" )
	private String paramSecondId;

	/**
	 * 室内监控参数类型id
	 */
   	@Column(name = "indoor_type_id" )
	private String indoorTypeId;

}
