package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/20 9:58
 **/
@Repository
public interface DeviceConfigDao {

    /**
     * 删除设备信息（谨慎！）
     *
     */
    @Options(statementType = StatementType.CALLABLE)
    @Select("call delete_AllByDevId(#{deviceId})")
    int deleteAllByDevId(@Param("deviceId") String deviceId);

    /**
     * 批量更新一级参数
     */
    @Update("<script><foreach collection=\"list\" item=\"list\" index=\"index\" open=\"\" close=\"\" separator=\";\">\n" +
            "UPDATE bus_param_first SET source_id = #{list.sourceId},`name` = #{list.name}," +
            "coding = #{list.coding} WHERE id = #{list.id}\n" +
            "</foreach></script>")
    int updateAllParamFirst(List<BusParamFirst> list);

    /**
     * 批量更新二级参数
     */
    @Update("")
    int updateAllParamSecond(List<BusParamSecond> list);
}
