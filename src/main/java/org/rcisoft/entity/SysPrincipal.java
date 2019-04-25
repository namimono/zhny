package org.rcisoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
