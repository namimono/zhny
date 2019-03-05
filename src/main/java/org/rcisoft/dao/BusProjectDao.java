package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusProject;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 11:32
 **/
@Repository
public interface BusProjectDao extends Mapper<BusProject> {

    /**
     * 查询全部项目表信息
     */
    @Select("SELECT * FROM bus_project;")
    @ResultType(BusProject.class)
    List<Map<String,Object>> queryAllInfo();
}
