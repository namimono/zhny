package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTitle;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:07 2019/3/15
 */
@Repository
public interface BusTitleDao extends Mapper<BusTitle> {

    @Select("<script>select * from bus_title</script>")
    @ResultType(BusTitle.class)
    List<BusTitle> queryBusTitle();

    /**
     * 根据项目ID联表查询自定义标题信息
     * @param projectId
     * @return
     */
    @Select("<script>select * from bus_title where project_id = #{projectId}</script>")
    @ResultType(BusTitle.class)
    List<BusTitle> queryTitleInfo(@Param("projectId") String projectId);
}
