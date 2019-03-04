package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="energy_price" )
public class EnergyPrice {


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
	 * 小时
	 */
   	@Column(name = "per_hour" )
	private Integer perHour;

	/**
	 * 价格
	 */
   	@Column(name = "price" )
	private BigDecimal price;

	/**
	 * 能耗分类id
	 */
   	@Column(name = "energy_type_id" )
	private Integer energyTypeId;

}
