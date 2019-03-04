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
@Table ( name ="energy_standard" )
public class EnergyStandard {


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
	 * 能耗分类id
	 */
   	@Column(name = "energy_type_id" )
	private Integer energyTypeId;

	/**
	 * 行业标准
	 */
   	@Column(name = "industry_standard" )
	private BigDecimal industryStandard;

	/**
	 * 建议标准
	 */
   	@Column(name = "suggest_standard" )
	private BigDecimal suggestStandard;

	/**
	 * 国家标准
	 */
   	@Column(name = "country_standard" )
	private BigDecimal countryStandard;

}
