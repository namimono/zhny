package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTopology;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:52 2019/3/13
 */
public interface BusTopologyDao extends Mapper<BusTopology> {
    /**
     * 查看拓扑图的json
     * @return
     */
    @Select("<script>select * from bus_topology</script>")
    @ResultType(BusTopology.class)
    BusTopology queryTopoJson();
}
