package org.rcisoft.dao.projMaintenance;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:28
 **/
@Mapper
@Repository
public interface BasicDataDao {

    /**
     *获取水电气24小时单价信息
     */
    @Select("")
    List<Map<String,Object>> queryWaterPerHourPrice();

    /**
     *获取水电气24小时单价信息
     */
    @Select("")
    List<Map<String,Object>> queryElecPerHourPrice();

    /**
     *获取水电气24小时单价信息
     */
    @Select("")
    List<Map<String,Object>> queryGasPerHourPrice();
}
