package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rcisoft.entity.BusIndoorParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/4/12 10:38
 **/
@Repository
public interface InOutDoorConfigDao {

    /**
     * 查询室内环境相同的条数
     */
    @Select("SELECT COUNT(*) FROM bus_indoor WHERE floor = #{floor} and door = #{door} and project_id = #{proId}")
    int queryIndoorRepeatNum(@Param("floor") int floor,@Param("door")int door,@Param("proId")String proId);

    /**
     * 批量更新室内环境参数
     */
    @Update("<script><foreach collection=\"list\" item=\"list\" index=\"index\" open=\"\" close=\"\" separator=\";\">\n" +
            "UPDATE bus_indoor_param SET project_id = #{list.projectId},\n" +
            "indoor_id = #{list.indoorId},param_first_id = #{list.paramFirstId},param_second_id = #{list.paramSecondId},\n" +
            "type = #{list.type} WHERE id = #{list.id}</foreach></script>\n")
    int updateAllIndoorParam(List<BusIndoorParam> list);
}
