package org.rcisoft.business.whole.head.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusProject;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:22 2019/4/8
 */
public interface HeadDao {
    /**
     * 查询所有项目
     * @return
     */
    @Select("<script>select id,name,code from bus_project</script>")
    List<BusProject> queryAllProj();
}
