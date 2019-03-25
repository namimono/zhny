package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.energy.overview.entity.OverviewParam;
import org.rcisoft.entity.EnergyPrice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 12:22
 **/
@Repository
public interface EnergyPriceDao extends Mapper<EnergyPrice> {

    /**
     * 根据项目id、时刻查询每小时的价格
     * @param overviewParam projectId：项目id；hour：当前时刻
     * @return
     */
    @Select("<script>select price,energy_type_id from energy_price where project_id = #{projectId} and per_hour = #{hour} </script>")
    @ResultType(EnergyPrice.class)
    List<EnergyPrice> queryPricePerHour(OverviewParam overviewParam);

}
