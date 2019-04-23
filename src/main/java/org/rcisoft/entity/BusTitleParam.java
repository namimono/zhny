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
@Table ( name ="bus_title_param" )
public class BusTitleParam {


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
	 * 标题表id
	 */
   	@Column(name = "title_id" )
	private String titleId;

	/**
	 * 设备id
	 */
	@Column(name = "device_id" )
	private String deviceId;

	/**
	 * 一级参数id
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

	/**
	 * 二级参数id
	 */
   	@Column(name = "param_second_id" )
	private String paramSecondId;

}
