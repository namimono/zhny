package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusIndoor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:46 2019/3/29
 */
@Repository
public interface IndoorDao{
    /**
     * 查询所有楼层
     * @return
     */
    @Select("<script>select floor from bus_indoor GROUP BY floor</script>")
    List<String> queryFloor();

    @Select("<script>select id,door FROM bus_indoor where floor = #{floor}</script>")
    List<BusIndoor> queryDoor(int floor);
}
