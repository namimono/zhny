package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

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
}
