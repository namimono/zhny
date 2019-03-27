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
@Table ( name ="bus_temperature" )
public class BusTemperature {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 实际温度
	 */
   	@Column(name = "temperature" )
	private BigDecimal temperature;

	/**
	 * 城市code
	 */
   	@Column(name = "coding" )
	private String coding;

	/**
	 * 湿度
	 */
   	@Column(name = "humidity" )
	private Integer humidity;

	/**
	 * 风向风速
	 */
   	@Column(name = "wind" )
	private String wind;

}
