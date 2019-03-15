package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.EnergyTypeConfig;
import org.rcisoft.entity.EnergyConfig;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/13 16:06
 **/
@Repository
public interface EnergyConfigDao extends Mapper<EnergyConfig> {

    /**
     * 根据项目设备等ID查询能耗分类信息
     */
    @Select("SELECT a.id AS 'typeId',a.name AS 'typeName'\n" +
            "FROM energy_type a,energy_config b\n" +
            "WHERE a.id = b.energy_type_id \n" +
            "AND b.project_id = #{projectId} AND b.device_id = #{deviceId} \n" +
            "AND b.param_first_id = #{paramFirstId} AND b.param_second_id = #{paramSecondId};")
    List<EnergyTypeConfig> queryTypeNameByConfig(EnergyTypeConfig energyTypeConfig);
}
