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
@Table ( name ="sys_role_menu_mid" )
public class SysRoleMenuMid {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 角色主键
	 */
   	@Column(name = "role_id" )
	private String roleId;

	/**
	 * 菜单主键
	 */
   	@Column(name = "menu_id" )
	private String menuId;

}
