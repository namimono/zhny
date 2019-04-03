package org.rcisoft.business.equipment.report.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.equipment.report.entity.ParamSecondWithFirst;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:18
 **/
@Repository
public interface SystemDataDao {

    /**
     * 查询二级参数信息及其对应的一级参数信息
     */
    @Select("SELECT a.id AS 'firstId',a.`name` AS 'firstName',a.coding AS 'firstCode',\n" +
            "b.id AS 'secondId',b.`name` AS 'secondName',b.coding AS 'secondCode'\n" +
            "FROM bus_param_first a,bus_param_second b\n" +
            "WHERE b.id IN (${paramSecondIds}) AND b.param_first_id = a.id;")
    List<ParamSecondWithFirst> querySecondWithFirst(@Param("paramSecondIds") String paramSecondId);
}
