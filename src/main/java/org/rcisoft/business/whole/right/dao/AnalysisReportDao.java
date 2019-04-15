package org.rcisoft.business.whole.right.dao;

import org.apache.ibatis.annotations.Select;
import org.rcisoft.entity.BusReport;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:08 2019/4/12
 */
@Repository
public interface AnalysisReportDao extends Mapper<BusReport> {
    /**
     * 查询分析报告表列表
     * @return
     */
    @Select("<script>select * from bus_report</script>")
    List<BusReport> queryAnalysisReport();

    @Select("<script>select url from bus_report where time_year = #{year} and time_month = #{month}</script>")
    String queryFileName(int year,int month);
}
