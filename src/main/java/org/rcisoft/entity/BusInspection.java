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
@Table ( name ="bus_inspection" )
public class BusInspection {


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
	 * 巡检人id
	 */
   	@Column(name = "inspector_id" )
	private String inspectorId;

	/**
	 * 巡检时间
	 */
   	@Column(name = "inspector_time" )
	private Date inspectorTime;

	/**
	 * 巡检内容
	 */
   	@Column(name = "content" )
	private String content;

}
