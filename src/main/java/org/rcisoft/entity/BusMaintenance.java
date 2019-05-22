package org.rcisoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="bus_maintenance" )
public class BusMaintenance {


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
	 * 设备主键
	 */
   	@Column(name = "device_id" )
	private String deviceId;

	/**
	 * 子系统id
	 */
   	@Column(name = "system_id" )
	private String systemId;

	/**
	 * 负责人
	 */
   	@Column(name = "principal" )
	private String principal;

	/**
	 * 工作内容
	 */
   	@Column(name = "content" )
	private String content;

	/**
	 * 计划时间
	 */
   	@Column(name = "plan_time" )
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date planTime;

}
