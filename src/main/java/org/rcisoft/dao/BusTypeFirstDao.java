package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTypeFirst;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/11 17:32
 **/
@Repository
public interface BusTypeFirstDao extends Mapper<BusTypeFirst> {

    /**
     * 查询一级设备类型列表
     */
    @Select("")
    List<BusTypeFirst> queryTypeFirst();
}
