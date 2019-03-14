package org.rcisoft.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusParamLibrary;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/14 14:18
 **/
@Repository
public interface BusParamLibraryDao extends Mapper<BusParamLibrary> {

    /**
     * 根据设备ID、二级参数ID查询参数库信息
     */
    @Select("SELECT * FROM bus_param_library \n" +
            "WHERE device_id = #{deviceId} AND param_second_id = #{paramSecondId};")
    List<BusParamLibrary> queryParamLibrary(BusParamLibrary busParamLibrary);
}
