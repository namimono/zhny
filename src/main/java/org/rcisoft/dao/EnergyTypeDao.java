package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.EnergyType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 16:24
 **/
@Repository
public interface EnergyTypeDao extends Mapper<EnergyType> {

    /**
     * 查询能耗分类信息
     */
    @Select("SELECT * FROM energy_type;")
    List<EnergyType> queryEnergyType();
}
