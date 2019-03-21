package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysData;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/21 11:13
 **/
@Repository
public interface SysDataDao extends Mapper<SysData> {

    /**
     * 根据时间段和项目ID查询数据
     */
    @Select("SELECT * FROM sys_data WHERE project_id = #{projectId}\n" +
            "AND create_time between #{beginTime} and #{endTime} ORDER BY create_time asc;")
    @ResultType(SysData.class)
    List<SysData> queryDataByProIdAndTime(@Param("projectId") String projectId,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
}
