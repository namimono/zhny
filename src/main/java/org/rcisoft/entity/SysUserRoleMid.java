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
@Table ( name ="sys_user_role_mid" )
public class SysUserRoleMid {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 用户id
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 角色id
	 */
   	@Column(name = "role_id" )
	private String roleId;

}
