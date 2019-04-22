package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.TitleAndSysName;
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
    @Select("select a.id,a.`name`,a.project_id AS 'projectId',\n" +
            "a.system_id AS 'systemId',b.`name` AS 'systemName'\n" +
            "from bus_title a LEFT JOIN sys_system b\n" +
            "ON a.system_id = b.id\n" +
            "where a.project_id = #{projectId}")
    @ResultType(TitleAndSysName.class)
    List<TitleAndSysName> queryTitleInfo(@Param("projectId") String projectId);

    /**
     * 根据项目ID联表查询自定义标题信息
     * @param projectId,systemId
     * @return
     */
    @Select("select a.id,a.`name`,a.project_id AS 'projectId',\n" +
            "a.system_id AS 'systemId',b.`name` AS 'systemName'\n" +
            "from bus_title a LEFT JOIN sys_system b\n" +
            "ON a.system_id = b.id\n" +
            "where a.project_id = #{projectId} and a.system_id = #{systemId};")
    @ResultType(TitleAndSysName.class)
    List<TitleAndSysName> queryTitleInfoBySys(@Param("projectId") String projectId,@Param("systemId") String systemId);

}
