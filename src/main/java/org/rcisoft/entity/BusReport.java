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
@Table ( name ="bus_report" )
public class BusReport {


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
	 * 创建日期
	 */
	@Column(name = "create_time")
   	private String createTime;

	/**
	 * 年份
	 */
   	@Column(name = "time_year" )
	private Integer timeYear;

	/**
	 * 月份
	 */
   	@Column(name = "time_month" )
	private Integer timeMonth;

	/**
	 * 文件存储路径
	 */
   	@Column(name = "url" )
	private String url;

}
