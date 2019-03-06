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
@Table ( name ="bus_malfunction" )
public class BusMalfunction {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 设备id
	 */
   	@Column(name = "device_id" )
	private String deviceId;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 子系统id
	 */
	@Column(name = "system_id" )
	private String systemId;

	/**
	 * 创建时间（故障时间）
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 故障内容
	 */
   	@Column(name = "content" )
	private String content;

	/**
	 * 处理结果
	 */
   	@Column(name = "result" )
	private String result;

	/**
	 * 负责人
	 */
   	@Column(name = "principal" )
	private String principal;

}
