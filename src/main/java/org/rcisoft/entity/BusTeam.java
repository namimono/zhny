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
@Table ( name ="bus_team" )
public class BusTeam {


	/**
	 * 团队主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 团队名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 资源
	 */
   	@Column(name = "resource" )
	private String resource;

	/**
	 * 上线时间
	 */
   	@Column(name = "online_time" )
	private Date onlineTime;

	/**
	 * 团队介绍
	 */
   	@Column(name = "introduction" )
	private String introduction;

	/**
	 * 团队负责人id
	 */
   	@Column(name = "principal_id" )
	private String principalId;

	/**
	 * 推荐指数 0--5
	 */
   	@Column(name = "recommend" )
	private Integer recommend;

	/**
	 * 项目类型，1：线上，2：线下
	 */
   	@Column(name = "type" )
	private Integer type;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

}
