package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="energy_carbon_real" )
public class EnergyCarbonReal {


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
	 * 创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 年
	 */
   	@Column(name = "time_year" )
	private Integer timeYear;

	/**
	 * 月
	 */
   	@Column(name = "time_month" )
	private Integer timeMonth;

	/**
	 * 日
	 */
   	@Column(name = "time_day" )
	private Integer timeDay;

	/**
	 * 小时
	 */
   	@Column(name = "time_hour" )
	private Integer timeHour;

	/**
	 * 碳排放量
	 */
   	@Column(name = "carbon" )
	private BigDecimal carbon;

}
