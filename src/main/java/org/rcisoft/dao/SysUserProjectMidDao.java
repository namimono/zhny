package org.rcisoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.rcisoft.entity.SysUserProjectMid;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/25
 */
@Repository
public interface SysUserProjectMidDao extends Mapper<SysUserProjectMid> {

    /**
     * 新增用户拥有的项目
     * @param sysUserProjectMidList
     * @return Integer
     */
    @Insert("<script>" +
            "<foreach collection = \"sysUserProjectMidList\" item = \"item\" separator=\";\"> " +
            " insert into sys_user_project_mid(id, user_id, project_id) " +
            " values(#{item.id}, #{item.userId}, #{item.projectId}) " +
            "</foreach>" +
            "</script>")
    Integer saveUserProject(@Param("sysUserProjectMidList") List<SysUserProjectMid> sysUserProjectMidList);
}
