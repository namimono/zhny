package org.rcisoft.business.system.project.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.business.system.project.entity.LibraryAndParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/14 15:02
 **/
@Repository
public interface OtherConfigDao extends Mapper<LibraryAndParam> {

    /**
     * 联查一二级参数和参数库信息
     */
    @Select("SELECT a.name AS 'paramFirstName',a.coding AS 'paramFirstCoding',\n" +
            "b.name AS 'paramSecondName',b.coding AS 'paramSecondCoding',b.source_id AS 'sourceId',\n" +
            "c.id AS 'libraryId',c.device_id AS 'deviceId',c.param_first_id AS 'paramFirstId',\n" +
            "c.param_second_id AS 'paramSecondId',c.compare_sign AS 'compareSign',\n" +
            "c.first_sign AS 'firstSign',c.sequence\n" +
            "FROM bus_param_first a,bus_param_second b,bus_param_library c\n" +
            "WHERE a.id = c.param_first_id AND b.id = c.param_second_id\n" +
            "AND c.device_id = #{deviceId} ORDER BY c.sequence ASC;")
    List<LibraryAndParam> queryLibraryAndParam(LibraryAndParam libraryAndParam);
}
