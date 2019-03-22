package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.BusProjectParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:30 2019/3/18
 */
public interface BusProjectParamDao extends Mapper<BusProjectParam> {
    /**
     * 查询设备一二级参数
     * @return
     */
    @Select("<script>select bp.id,bpf.coding as coding_first,bps.coding as coding_second from bus_param_first bpf,bus_param_second bps,bus_project bp " +
            "where bp.id = bpf.project_id and bps.param_first_id = bpf.id and bps.project_id = bp.id</script>")
    BusProjectParam queryParam();

    /**
     * 查询左侧边栏标题名称
     * @return
     */
    @Select("<script>select name from sys_system</script>")
    List<String> queryModelName();

}
