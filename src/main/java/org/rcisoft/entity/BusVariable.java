package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="bus_variable" )
public class BusVariable {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 变量（公式中需要替换的值，如：N1+N2中的N1）
	 */
   	@Column(name = "variable" )
	private String variable;

	/**
	 * 一级参数表id
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

	/**
	 * 二级参数表id
	 */
   	@Column(name = "param_second_id" )
	private String paramSecondId;

	/**
	 * 公式表主键
	 */
   	@Column(name = "formula_id" )
	private String formulaId;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

}
