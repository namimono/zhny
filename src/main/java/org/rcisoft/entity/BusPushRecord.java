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
@Table ( name ="bus_push_record" )
public class BusPushRecord {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 项目主键
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 推送时间
	 */
   	@Column(name = "push_time" )
	private Date pushTime;

	/**
	 * 推送内容
	 */
   	@Column(name = "content" )
	private String content;

}
