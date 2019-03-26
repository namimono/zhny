package org.rcisoft.base.util;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/26 15:51
 **/

@RegisterMapper
public interface SpecialBatchMapper<T> {

    /**
     * 批量插入数据库，所有字段都插入，包括主键
     *
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = SpecialBatchProvider.class, method = "insertListUseAllCols")
    int insertListUseAllCols(List<T> recordList);
}
