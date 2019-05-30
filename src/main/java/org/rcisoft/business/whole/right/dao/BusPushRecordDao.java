package org.rcisoft.business.whole.right.dao;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusPushRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:12 2019/3/13
 */
@Repository
public interface BusPushRecordDao extends Mapper<BusPushRecord> {
    /**
     * 查询消息推送时间及内容
     * @return
     */
    @Select("<script>select push_time,content from bus_push_record</script>")
    @ResultType(BusPushRecord.class)
    BusPushRecord queryContent();
}
