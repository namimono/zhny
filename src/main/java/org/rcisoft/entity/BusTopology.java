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
@Table ( name ="bus_topology" )
public class BusTopology {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 拓扑图内容，json格式
	 */
   	@Column(name = "content" )
	private String content;

	/**
	 * 项目主键
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 子系统主键
	 */
   	@Column(name = "system_id" )
	private String systemId;

}
