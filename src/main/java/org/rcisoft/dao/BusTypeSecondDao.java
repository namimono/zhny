package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTypeSecond;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/11 17:31
 **/
@Repository
public interface BusTypeSecondDao extends Mapper<BusTypeSecond> {

    /**
     * 查询二级设备类型列表
     */
    @Select("")
    List<BusTypeSecond> queryTypeSecond();
}
