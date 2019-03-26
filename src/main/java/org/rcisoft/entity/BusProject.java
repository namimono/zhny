package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="bus_project" )
public class BusProject {


	/**
	 * 项目主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 项目名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 网关编号，多个，逗号分隔
	 */
   	@Column(name = "phones" )
	private String phones;

	/**
	 * 建筑类型
	 */
   	@Column(name = "building_id" )
	private String buildingId;

	/**
	 * 建筑名称
	 */
	@Column(name = "building_name" )
	private String buildingName;

	/**
	 * 建筑面积
	 */
   	@Column(name = "building_area" )
	private BigDecimal buildingArea;

	/**
	 * 建筑地址
	 */
   	@Column(name = "building_local" )
	private String buildingLocal;

	/**
	 * 建筑坐标
	 */
   	@Column(name = "building_coordinate" )
	private String buildingCoordinate;

	/**
	 * 建筑分区id
	 */
   	@Column(name = "building_zone_id" )
	private String buildingZoneId;

	/**
	 * 业主id，用户表中type=3的用户
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 省id
	 */
   	@Column(name = "province_id" )
	private String provinceId;

	/**
	 * 市id
	 */
   	@Column(name = "city_id" )
	private String cityId;

	/**
	 * 城市code
	 */
   	@Column(name = "code" )
	private String code;

	/**
	 * 建筑年代
	 */
	@Column(name = "building_age" )
	private Date buildingAge;

	/**
	 * 项目创建时间
	 */
   	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 设备年代
	 */
   	@Column(name = "equipment_age" )
	private Date equipmentAge;

	/**
	 * 节能潜力
	 */
   	@Column(name = "energy_potential" )
	private BigDecimal energyPotential;

	/**
	 * 线上团队ID
	 */
   	@Column(name = "online_team_id" )
	private String onlineTeamId;

	/**
	 * 线下团队ID
	 */
   	@Column(name = "offline_team_id" )
	private String offlineTeamId;

	/**
	 * 项目类型，1：自营 or 2：托管
	 */
   	@Column(name = "type" )
	private Integer type;

	/**
	 * 巡检人员，id集合，逗号拼接
	 */
   	@Column(name = "inspect_ids" )
	private String inspectIds;

	/**
	 * 系统id集合，逗号拼接，表示属于此项目的子系统
	 */
   	@Column(name = "system_ids" )
	private String systemIds;

	/**
	 * 是否在线（1：线上  0：项目回收状态）
	 */
   	@Column(name = "online" )
	private Integer online;

	/**
	 * 是否接收数据，0：不接，1：接收
	 */
   	@Column(name = "receive" )
	private Integer receive;

	/**
	 * 节能改造标示位，0：否，1：是
	 */
   	@Column(name = "save_sign" )
	private Integer saveSign;

}
