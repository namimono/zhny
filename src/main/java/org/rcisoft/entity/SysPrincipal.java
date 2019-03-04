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
@Table ( name ="sys_principal" )
public class SysPrincipal {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 负责人姓名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 职务
	 */
   	@Column(name = "job" )
	private String job;

	/**
	 * 职称
	 */
   	@Column(name = "job_title" )
	private String jobTitle;

	/**
	 * 从业时间
	 */
   	@Column(name = "employment_time" )
	private Date employmentTime;

	/**
	 * 荣誉
	 */
   	@Column(name = "honor" )
	private String honor;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

}
