package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/28 16:10
 * 新增一二级参数时，一级参数包含二级参数对象list实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamFirstContainSecond {

    /**
     * 一级参数对象
     */
    private BusParamFirst busParamFirst;

    /**
     * 二级参数对象list
     */
    private List<BusParamSecond> secondary;
}
