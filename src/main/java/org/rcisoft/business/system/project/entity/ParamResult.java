package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusParamSecond;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/5/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamResult {

    /**
     * 一二级列表
     */
    private List<ParamFirstContainSecond> list = new ArrayList<>();

    /**
     * 固定参数列表
     */
    private List<BusParamSecond> fixedList = new ArrayList<>();

}
