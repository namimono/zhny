package org.rcisoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="bus_project_saving" )
public class BusProjectSaving {


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
	 * 改造内容
	 */
   	@Column(name = "save_content" )
	private String saveContent;

	/**
	 * 工程造价
	 */
   	@Column(name = "save_cost" )
	private String saveCost;

	/**
	 * 分享期
	 */
   	@Column(name = "save_share" )
	private String saveShare;

	/**
	 * 分享方式
	 */
   	@Column(name = "save_method" )
	private String saveMethod;

	/**
	 * 节能量估算
	 */
   	@Column(name = "save_estimate" )
	private String saveEstimate;

	/**
	 * 工程造价认定人员id（认定员id）
	 */
   	@Column(name = "save_cost_id" )
	private String saveCostId;

	/**
	 * 节能认定人员id（认定员id）
	 */
   	@Column(name = "save_energy_id" )
	private String saveEnergyId;

}
