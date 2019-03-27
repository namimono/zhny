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
@Table ( name ="sys_image" )
public class SysImage {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private Integer id;

	/**
	 * 图片存储路径
	 */
   	@Column(name = "url" )
	private String url;

}
