package org.rcisoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="bus_param_second" )
public class BusParamSecond {


	/**
	 * 主键
	 */
   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 一级参数表id
	 */
   	@Column(name = "param_first_id" )
	private String paramFirstId;

	/**
	 * 项目id
	 */
   	@Column(name = "project_id" )
	private String projectId;

	/**
	 * 子系统id
	 */
   	@Column(name = "system_id" )
	private String systemId;

	/**
	 * 设备id
	 */
   	@Column(name = "device_id" )
	private String deviceId;

	/**
	 * 参数来源id，也可作为排序字段
	 */
   	@Column(name = "source_id" )
	private Integer sourceId;

	/**
	 * 参数名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 参数编码
	 */
   	@Column(name = "coding" )
	private String coding;

	/**
	 * 单位
	 */
   	@Column(name = "unit" )
	private String unit;

	/**
	 * 固定参数的值
	 */
	@Column(name = "value" )
	private BigDecimal value;

	/**
	 * 一级id相同时的排序字段
	 */
   	@Column(name = "sequence" )
	private Integer sequence;

	/**
	 * 是否作故障判断，0：否，1：是
	 */
   	@Column(name = "fault_status" )
	private Integer faultStatus;

	/**
	 * 故障：最小值
	 */
   	@Column(name = "min_value" )
	private BigDecimal minValue;

	/**
	 * 故障：最大值
	 */
   	@Column(name = "max_value" )
	private BigDecimal maxValue;

	/**
	 * 故障内容
	 */
   	@Column(name = "content" )
	private String content;


	/**
	 * 能耗分类id
	 */
   	@Column(name = "energy_type_id" )
	private Integer energyTypeId;

	/**
	 * 当能耗分类id=2（电）时，0：电度，1：功率，默认是0
	 */
   	@Column(name = "elec_type" )
	private Integer elecType;


	/**
	 * 1代表主参数1；2代表主参数2；3代表副参数；4代表副参数2。当只有2个或3个参数的时候，数值不变。
	 */
   	@Column(name = "first_sign" )
	private Integer firstSign;

	/**
	 * 拓扑图显示标志位
	 */
	@Column(name = "show_status" )
   	private Integer showStatus;

}
