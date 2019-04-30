package org.rcisoft.business.monitor.intercept.service;

import org.rcisoft.entity.BusTitle;
import org.rcisoft.entity.BusTopology;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:56 2019/3/13
 */
public interface SysDetectionService {
    BusTopology queryTopoJson();

    /**
     * 根据系统类型查出当前项目下的标签
     * @param systemId
     * @param projectId
     * @return List<BusTitle>
     */
    List<BusTitle> queryBusTitle(String projectId, String systemId);
}
