package org.rcisoft.service.projMaintenance;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:27
 **/

public interface BasicDataService {

    /**
     *获取水电气24小时单价信息
     */
    List<Map<String,Object>> queryPerHourPrice();
}
