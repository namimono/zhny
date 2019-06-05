package org.rcisoft.business.monitor.intercept.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.monitor.intercept.entity.BusParamOutsideAndInside;
import org.rcisoft.business.monitor.intercept.entity.BusParamType;
import org.rcisoft.entity.*;
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
     * 查询该项目下的所有楼层
     * @return List<BusIndoor>
     */
    @Select("<script>select * from bus_indoor WHERE project_id = #{projectId} GROUP BY floor</script>")
    List<BusIndoor> queryFloor(@Param("projectId") String projectId);

    /**
     * 查看该项目该楼层所有房间
     * @param floor
     * @return
     */
    @Select("<script>select * FROM bus_indoor where floor = #{floor} AND project_id = #{projectId}</script>")
    List<BusIndoor> queryDoor(@Param("projectId") String projectId, @Param("floor") int floor);

    /**
     * 根据门牌号查询室内参数
     * @param indoorId
     * @return
     */
    @Select("<script>select bpf.coding as codingFirst,bps.coding as codingSecond,bip.type " +
            "  from bus_indoor_param bip,bus_param_first bpf,bus_param_second bps \n" +
            " where  bip.indoor_id = #{indoorId} and bip.param_first_id = bpf.id and bip.param_second_id = bps.id and bip.project_id = #{proId}</script>")
    List<BusParamType> queryBusIndoorParamInside(String indoorId, String proId);

    /**
     * 根据门牌号查询室外参数
     * @return
     */
    @Select("<script>select bpf.coding as codingFirst,bps.coding as codingSecond,bo.type   from bus_outdoor bo,bus_param_first bpf,bus_param_second bps " +
            " where   bo.param_first_id = bpf.id and bo.param_second_id = bps.id and bo.project_id = #{proId}</script>")
    List<BusParamType> queryBusIndoorParamOutside(String proId);

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
     * 查询室内一二级参数24小时
     */
    @Select("<script>select bpf.coding as codingFirst,bps.coding as codingSecond from bus_indoor_param bip,bus_param_first bpf,bus_param_second bps " +
            "where bip.type = #{type} and bip.indoor_id = #{indoorId} and bip.param_first_id = bpf.id and bip.param_second_id = bps.id</script>")
    BusParamOutsideAndInside queryParamHoursInside(int type, String indoorId);

    /**
     * 查询室外一二级参数24小时（pm2.5和CO2）
     */
    @Select("<script>select bpf.coding as codingFirst,bps.coding as codingSecond from bus_outdoor bo,bus_param_first bpf,bus_param_second bps " +
            "where bo.type = #{type} and  bo.param_first_id = bpf.id and bo.param_second_id = bps.id and bo.project_id = #{proId}</script>")
    BusParamOutsideAndInside queryParamHoursOutside(int type,String proId);

    /**
     * 查询室外一二级参数24小时（温度，湿度）
     */
    @Select("<script>select * from bus_temperature where TO_DAYS(create_time)=TO_DAYS(NOW()) and coding = #{coding}</script>")
    List<BusTemperature> queryTempHours(String coding);

    /**
     * 查询室外一二级参数24小时（湿度）
     */
    @Select("<script>select * from bus_temperature where TO_DAYS(create_time)=TO_DAYS(NOW()) and coding = #{coding}</script>")
    List<BusTemperature> queryHumidityHours(String coding);

    /**
     * 根据年份月份查询json数据
     */
    @Select("<script>select json,create_time from sys_data where YEAR(create_time) = #{year} and MONTH(create_time) = #{month} and RIGHT(create_time,5)='00:00'</script>")
    List<SysData> queryJsonByData(int year,int month);

    /**
     * 查询室外一二级参数24根据年月分（温度）
     */
    @Select("<script>select * from bus_temperature where TO_DAYS(create_time)=TO_DAYS(NOW()) and coding = #{coding} and year(create_time) = #{year} " +
            "and month(create_time) = #{month}</script>")
    List<BusTemperature> queryTempDate(String coding,int year,int month);

    /**
     * 查询室外一二级参数24小时根据年月份（湿度）
     */
    @Select("<script>select * from bus_temperature where TO_DAYS(create_time)=TO_DAYS(NOW()) and coding = #{coding} and year(create_time) = #{year} " +
            " and month(create_time) = #{month}</script>")
    List<BusTemperature> queryHumidityDate(String coding,int year,int month);
}
