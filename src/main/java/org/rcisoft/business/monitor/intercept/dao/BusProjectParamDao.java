package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.BusProjectParam;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:30 2019/3/18
 */
public interface BusProjectParamDao extends Mapper<BusProjectParam> {

    @Select("<script>select bp.id,bpf.coding as coding_first,bps.coding as coding_second from bus_param_first bpf,bus_param_second bps,bus_project bp " +
            "where bp.id = bpf.project_id and bps.param_first_id = bpf.id and bps.project_id = bp.id</script>")
    BusProjectParam queryParam();
}
