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
@Table ( name ="sys_menu" )
public class SysMenu {


	/**
	 * 菜单主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 菜单名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 上级菜单
	 */
   	@Column(name = "pid" )
	private String pid;

	/**
	 * 菜单地址
	 */
   	@Column(name = "url" )
	private String url;

	/**
	 * 菜单级别
	 */
   	@Column(name = "level" )
	private Integer level;

	/**
	 * 排序
	 */
   	@Column(name = "ordered" )
	private Integer ordered;

}
