package org.rcisoft.business.monitor.intercept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/5/29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamsResult {

    private DeviceInfomation deviceInfomation;

    private List<Elec> elecList = new ArrayList<>();

    private List<Params> paramList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Elec {

        private String elecFirstCode;

        private String elecSecondCode;

        /**
         * 0：电度，1：功率
         */
        private Integer elecType;
    }

}
