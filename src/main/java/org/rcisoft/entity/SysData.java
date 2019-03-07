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
@Table ( name ="sys_data" )
public class SysData {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 时间，10分钟一条
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * json数据
	 */
   	@Column(name = "json" )
	private String json;

	/**
	 * 项目主键
	 */
   	@Column(name = "project_id" )
	private String projectId;

}
