package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;
import org.rcisoft.entity.SysData;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    /**
     * 查看该楼层所有房间
     * @param floor
     * @return
     */
    @Select("<script>select id,door FROM bus_indoor where floor = #{floor}</script>")
    List<BusIndoor> queryDoor(int floor);

    /**
     * 根据门牌号查询室内室外温度
     * @param indoorId
     * @return
     */
    @Select("<script>select * from bus_indoor_param where indoor_id = #{indoorId} and project_id = #{proId}</script>")
    List<BusIndoorParam> queryBusIndoorParam(String indoorId,String proId);

    /**
     * 查询当天最新一条json数据
     * @param proId
     * @return
     */
    @Select("<script>select json from sys_data where to_days(create_time) = to_days(now()) and project_id = #{proId} GROUP BY create_time DESC LIMIT 1 </script>")
    String queryJson(String proId);

    /**
     * 查询数据库中的温度
     */
    @Select("<script>select temperature from bus_temperature where coding = #{coding} GROUP BY create_time DESC LIMIT 1</script>")
    BigDecimal queryTemperature(String coding);

    /**
     * 查询数据库中的湿度
     */
    @Select("<script>select humidity from bus_temperature where coding = #{coding} GROUP BY create_time DESC LIMIT 1</script>")
    BigDecimal queryHumidity(String coding);

    /**
     * 查询整点json数据
     */
    @Select("<script>select json,create_time from sys_data " +
            "where TO_DAYS(create_time) = TO_DAYS(NOW()) and RIGHT(create_time,5)='00:00' and project_id = #{proId}</script>")
     List<SysData> queryJsonIndoor(String proId);

    /**
     * 查询一二级参数24小时
     */
    @Select("<script>select * from bus_indoor_param where type = #{type} and indoor_id = #{indoorId}</script>")
    List<BusIndoorParam> queryParamHours(int type,String indoorId);
}
