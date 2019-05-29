package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by JiChao on 2019/5/28.
 * 返回值
 * 设备id，一级、二级code，电度or功率
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamElec {

    private String deviceId;

    private String firstCode;

    private String secondCode;

    private String type;

}
