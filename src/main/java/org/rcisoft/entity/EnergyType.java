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
@Table ( name ="energy_type" )
public class EnergyType {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 能耗分类名称
	 */
   	@Column(name = "name" )
	private String name;

}
