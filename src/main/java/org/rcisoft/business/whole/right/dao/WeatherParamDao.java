package org.rcisoft.business.whole.right.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusTemperature;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:07 2019/4/12
 */
@Repository
public interface WeatherParamDao {
    /**
     * 查询天气数据存入excel表格
     * @param proId
     * @return
     */
    @Select("<script>select bt.* from bus_temperature bt " +
            "where coding = (select bp.code from bus_project bp where id = #{proId}) and create_time &gt; #{start} and create_time  &lt;  #{finish} ORDER BY create_time asc</script>")
    List<BusTemperature> downloadWeather(String proId,String start,String finish);

    /**
     * 根据项目id查询城市名称
     * @param proId
     * @return
     */
    @Select("<script>select name from sys_city where coding = " +
            "(select bp.code from bus_project bp where id = #{id})</script>")
    String queryCity(String proId);
}
