package org.rcisoft.business.monitor.intercept.service.Impl;

import org.rcisoft.business.monitor.intercept.service.BusTopologyService;
import org.rcisoft.dao.BusTopologyDao;
import org.rcisoft.entity.BusTopology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:59 2019/3/13
 */
@Service
public class BusTopologyServiceImpl implements BusTopologyService {
    @Autowired
    BusTopologyDao busTopologyDao;

    /**
     * 获取拓扑图json
     * @return
     */
    @Override
    public BusTopology queryTopoJson() {
        return busTopologyDao.queryTopoJson();
    }
}
