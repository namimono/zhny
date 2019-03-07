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
@Table ( name ="sys_user_project_mid" )
public class SysUserProjectMid {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 用户主键
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 项目主键
	 */
   	@Column(name = "project_id" )
	private String projectId;

}
