package org.rcisoft.business.management.evaluateteam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusTeamAssessment {


	/**
	 * 团队主键
	 */
	private String teamId;

	/**
	 * 团队名称
	 */
	private String teamName;

	/**
	 * 资源
	 */
	private String resource;

	/**
	 * 上线时间
	 */
	private Date onlineTime;

	/**
	 * 团队介绍
	 */
	private String introduction;

	/**
	 * 团队负责人id
	 */
	private String principalId;

	/**
	 * 推荐指数 0--5
	 */
	private Integer recommend;

	/**
	 * 项目类型，1：线上，2：线下
	 */
	private Integer type;

	/**
	 * 创建时间
	 */
	private Date teamCreateTime;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 负责人姓名
	 */
	private String principalName;

	/**
	 * 职务
	 */
	private String job;

	/**
	 * 职称
	 */
	private String jobTitle;

	/**
	 * 从业时间
	 */
	private Date employmentTime;

	/**
	 * 荣誉
	 */
	private String honor;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
