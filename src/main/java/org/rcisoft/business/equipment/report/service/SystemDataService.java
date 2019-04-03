package org.rcisoft.business.equipment.report.service;

import org.rcisoft.entity.BusParamSecond;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/

public interface SystemDataService {

//    /**
//     * 根据参数来源查询二级参数
//     */
//    List<BusParamSecond> queryParamSecondBySource(String proId, String sourceId);

    /**
     * 下载数据文档
     */
    void downlDataDocument(HttpServletResponse response, String paramSecondIds, String proId, String beginTime, String endTime);

    /**
     * 查询图表数据
     */
}
