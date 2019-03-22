package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.operation.establishment.entity.ConditionDto;
import org.rcisoft.entity.SysData;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/21
 */
@Repository
public interface SysDataDao extends Mapper<SysData> {

    /**
     *  根据项目ID，时间查出这个项目当天的网管数据
     * @param conditionDto
     * @return
     */
    @Select("<script>SELECT * FROM sys_data WHERE project_id = #{conditionDto.proId} \n" +
            "AND DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(#{conditionDto.date},'%Y-%m-%d')  </script>")
    List<SysData> listSysDataByProIdAndDate(@Param("conditionDto") ConditionDto conditionDto);

     /**
     * 根据时间段和项目ID查询数据
     */
    @Select("SELECT * FROM sys_data WHERE project_id = #{projectId}\n" +
            "AND create_time between #{beginTime} and #{endTime} ORDER BY create_time asc;")
    @ResultType(SysData.class)
    List<SysData> queryDataByProIdAndTime(@Param("projectId") String projectId,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
}
