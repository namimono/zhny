package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTitle;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:07 2019/3/15
 */
public interface BusTitleDao extends Mapper<BusTitle> {

    @Select("<script>select * from bus_title</script>")
    @ResultType(BusTitle.class)
    List<BusTitle> queryBusTitle();
}
