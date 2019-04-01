package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.TypeFirstAndSecond;
import org.rcisoft.entity.BusTypeSecond;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/11 17:31
 **/
@Repository
public interface BusTypeSecondDao extends Mapper<BusTypeSecond> {

    /**
     * 根据系统类型ID和一级设备类型ID查询二级设备类型信息(处理一、二级设备类型下拉菜单级联格式使用)
     */
    @Select("select a.id AS 'firstId',a.name AS 'firstName',\n" +
            "b.id AS 'secondId',b.name AS 'secondName',b.url,\n" +
            "b.type_first_id AS 'typeFirstId',b.system_id AS 'systemId' \n" +
            "from bus_type_first as a \n" +
            "left join bus_type_second as b \n" +
            "on a.id = b.type_first_id and b.system_id = #{systemId}")
    @ResultType(TypeFirstAndSecond.class)
    List<TypeFirstAndSecond> queryTypeSecondInfo(@Param("systemId") String systemId);
}
