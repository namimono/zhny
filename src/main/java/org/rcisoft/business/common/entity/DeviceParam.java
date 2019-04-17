package org.rcisoft.business.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/4/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeviceParam {

    private String deviceId;

    private String deviceName;

    private List<FirstParam> firstList = new ArrayList<>();

}
