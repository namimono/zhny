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
@Table ( name ="sys_authenticator" )
public class SysAuthenticator {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 认定员姓名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 执业资质
	 */
   	@Column(name = "qualification" )
	private String qualification;

	/**
	 * 从业时间
	 */
   	@Column(name = "employment_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date employmentTime;

	/**
	 * 项目业绩
	 */
   	@Column(name = "performance" )
	private String performance;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

}
