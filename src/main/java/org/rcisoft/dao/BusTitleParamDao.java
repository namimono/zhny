package org.rcisoft.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.base.util.SpecialBatchMapper;
import org.rcisoft.business.system.project.entity.TitleParamAndParam;
import org.rcisoft.entity.BusTitleParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/15 15:15
 **/
@Repository
public interface BusTitleParamDao extends Mapper<BusTitleParam>, SpecialBatchMapper<BusTitleParam> {

    /**
     * 根据自定义标题ID查询自定义参数信息
     */
    @Select("SELECT a.id,a.title_id AS 'titleId',a.device_id as 'deviceId',a.param_first_id AS 'paramFirstId',\n" +
            "a.param_second_id AS 'paramSecondId',b.name AS 'paramFirstName',\n" +
            "b.coding AS 'paramFirstCoding',c.name AS 'paramSecondName',\n" +
            "c.coding AS 'paramSecondCoding'\n" +
            "FROM bus_title_param a,bus_param_first b,bus_param_second c\n" +
            "WHERE a.param_first_id = b.id AND a.param_second_id = c.id\n" +
            "AND a.title_id = #{titleId};")
    @ResultType(TitleParamAndParam.class)
    List<TitleParamAndParam> queryTitleParamsInfo(@Param("titleId") String titleId);
}
