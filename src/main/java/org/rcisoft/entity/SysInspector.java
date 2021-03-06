package org.rcisoft.entity;

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
@Table ( name ="sys_inspector" )
public class SysInspector {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 用户名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 姓名
	 */
   	@Column(name = "real_name" )
	private String realName;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 微信用
	 */
   	@Column(name = "open_id" )
	private String openId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time" )
   	private Date createTime;

}
