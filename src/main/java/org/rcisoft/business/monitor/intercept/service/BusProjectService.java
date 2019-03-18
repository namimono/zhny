package org.rcisoft.business.monitor.intercept.service;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 14:01 2019/3/18
 */
public interface BusProjectService {
    Map<String,Object> queryPhones(String id);
    List<Map<String, Object>> queryParam(String id);
}
