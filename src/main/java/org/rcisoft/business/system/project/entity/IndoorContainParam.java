package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/12 10:29
 * 新增或编辑室内环境信息时，室内环境包含室内环境参数对象list实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndoorContainParam {

    /**
     * 室内环境对象
     */
    private BusIndoor busIndoor;

    /**
     * 室内环境参数对象list
     */
    private List<BusIndoorParam> indoorParams;
}
