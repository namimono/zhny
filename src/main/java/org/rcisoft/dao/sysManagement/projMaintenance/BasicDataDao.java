package org.rcisoft.dao.sysManagement.projMaintenance;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.EnergyPrice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:28
 **/
@Repository
public interface BasicDataDao {

    /**
     *获取水24小时单价信息
     */
    @Select("SELECT per_hour,price FROM energy_price WHERE energy_type_id = '1' AND project_id = #{projectId};")
    List<Map<String,String>> queryWaterPerHourPrice(EnergyPrice energyPrice);

    /**
     *获取电24小时单价信息
     */
    @Select("SELECT per_hour,price FROM energy_price WHERE energy_type_id = '2' AND project_id = #{projectId};")
    List<Map<String,String>> queryElecPerHourPrice(EnergyPrice energyPrice);

    /**
     *获取气24小时单价信息
     */
    @Select("SELECT per_hour,price FROM energy_price WHERE energy_type_id = '3' AND project_id = #{projectId};")
    List<Map<String,String>> queryGasPerHourPrice(EnergyPrice energyPrice);

}
