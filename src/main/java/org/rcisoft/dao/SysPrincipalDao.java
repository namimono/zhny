package org.rcisoft.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.SysPrincipal;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by JiChao on 2019/3/13.
 * 团队负责人
 */
@Repository
public interface SysPrincipalDao extends Mapper<SysPrincipal> {

    /**
     * 查询团队负责人id、姓名 列表
     * @return
     */
    @Select("<script>select id, name from sys_principal</script>")
    @ResultType(SysPrincipal.class)
    List<SysPrincipal> queryPrincipalList();

}
