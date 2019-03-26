package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.base.util.SpecialBatchMapper;
import org.rcisoft.entity.EnergyStandard;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 12:23
 **/
@Repository
public interface EnergyStandardDao extends Mapper<EnergyStandard>, SpecialBatchMapper<EnergyStandard> {

    /**
     * 根据项目id查询能耗标准
     * @param projectId
     * @return
     */
    @Select("<script>select * from energy_standard where project_id = #{projectId} order by energy_type_id</script>")
    @ResultType(EnergyStandard.class)
    List<EnergyStandard> queryEnergyStandard(@Param("projectId") String projectId);

}
