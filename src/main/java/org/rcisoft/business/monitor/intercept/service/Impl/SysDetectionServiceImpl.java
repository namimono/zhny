package org.rcisoft.business.monitor.intercept.service.Impl;

import org.rcisoft.business.monitor.intercept.service.SysDetectionService;
import org.rcisoft.dao.BusTitleDao;
import org.rcisoft.dao.BusTopologyDao;
import org.rcisoft.entity.BusTitle;
import org.rcisoft.entity.BusTopology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:59 2019/3/13
 */
@Service
public class SysDetectionServiceImpl implements SysDetectionService {
    @Autowired
    BusTopologyDao busTopologyDao;
    @Autowired
    BusTitleDao busTitleDao;

    /**
     * 获取拓扑图json
     * @return
     */
    @Override
    public BusTopology queryTopoJson() {
        return busTopologyDao.queryTopoJson();
    }

    @Override
    public List<BusTitle> queryBusTitle() {
        return busTitleDao.queryBusTitle();
    }
}
