package org.rcisoft.business.equipment.report.service;

import org.rcisoft.business.equipment.report.entity.ReturnSystemData;
import org.rcisoft.business.equipment.report.entity.SystemDataDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/19 15:17
 **/

public interface SystemDataService {

    /**
     * 下载数据文档
     */
    void downlDataDocument(HttpServletResponse response,String paramSecondIds,String proId,String date);

    /**
     * 查询图表数据
     */
    List<Object> queryEchartData(String paramSecondIds,String proId,String date);


    List<ReturnSystemData> listSystemData (SystemDataDto systemDataDto);

}
