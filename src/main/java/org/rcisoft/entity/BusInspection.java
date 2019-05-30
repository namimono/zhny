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
@Table ( name ="bus_inspection" )
public class BusInspection {


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
	 * 设备id
	 */
   	@Column(name = "device_id" )
	private String deviceId;

	/**
	 * 巡检人id
	 */
   	@Column(name = "inspector_id" )
	private String inspectorId;

	/**
	 * 巡检时间
	 */
   	@Column(name = "inspector_time" )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date inspectorTime;

	/**
	 * 巡检内容
	 */
   	@Column(name = "content" )
	private String content;

}
