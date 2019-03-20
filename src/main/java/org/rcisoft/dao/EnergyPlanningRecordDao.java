package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.rcisoft.entity.EnergyPlanningRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/15
 */
@Repository
public interface EnergyPlanningRecordDao extends Mapper<EnergyPlanningRecord> {

    /**
     *  批量增加EnergyPlanningDevice
     * @param energyPlanningRecordList
     * @return
     */
    @Insert("<script><foreach collection = \"energyPlanningRecordList\" item = \"item\" separator=\";\"> " +
            " insert into energy_planning_record(id, project_id, device_id, main_first_id, main_second_id, main_value, " +
            " main_first_id2, main_second_id2, main_value2, param_value, param_value2, create_time, start_time, end_time) " +
            " values(#{item.id}, #{item.projectId}, #{item.deviceId}, #{item.mainFirstId}, #{item.mainSecondId}, #{item.mainValue}, " +
            " #{item.mainFirstId2}, #{item.mainSecondId2}, #{item.mainValue2}, #{item.paramValue}, #{item.paramValue2}, " +
            " #{item.createTime}, #{item.startTime}, #{item.endTime}) " +
            "</foreach></script>")
    Integer saveListEnergyPlanningRecord(@Param("energyPlanningRecordList") List<EnergyPlanningRecord> energyPlanningRecordList);
}
