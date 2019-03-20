package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.rcisoft.entity.EnergyPlanningDevice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Repository
public interface EnergyPlanningDeviceDao extends Mapper<EnergyPlanningDevice> {

    /**
     *  批量增加EnergyPlanningDevice
     * @param energyPlanningDeviceList
     * @return
     */
    @Insert("<script><foreach collection = \"energyPlanningDeviceList\" item = \"item\" separator=\";\"> " +
            " insert into energy_planning_device(id, project_id, device_id, create_time) " +
            " values(#{item.id}, #{item.projectId}, #{item.deviceId}, #{item.createTime}) " +
            "</foreach></script>")
    Integer saveListEnergyPlanningDevice(@Param("energyPlanningDeviceList") List<EnergyPlanningDevice> energyPlanningDeviceList);


}
