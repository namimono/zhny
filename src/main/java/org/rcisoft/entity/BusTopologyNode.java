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
@Table ( name ="bus_topology_node" )
public class BusTopologyNode {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 节点名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 图片路径
	 */
   	@Column(name = "url" )
	private String url;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

}
