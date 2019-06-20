package org.rcisoft.business.whole.head.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusProject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:22 2019/4/8
 */
@Repository
public interface HeadDao {
    /**
     * 查询所有项目
     * @return
     */
    @Select("<script>select p.id, p.name, p.code from bus_project p, sys_user_project_mid m where p.online = 1 and p.id = m.project_id and m.user_id = #{userId}</script>")
    List<BusProject> queryAllProj(@Param("userId") String userId);
}
