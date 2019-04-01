package org.rcisoft.business.monitor.intercept.service.Impl;

import org.rcisoft.business.monitor.intercept.dao.IndoorDao;
import org.rcisoft.business.monitor.intercept.service.IndoorService;
import org.rcisoft.entity.BusIndoor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:47 2019/3/29
 */
@Service
public class IndoorServiceImpl implements IndoorService {
    @Autowired
    IndoorDao indoorDao;

    @Override
    public List<String> queryFloor() {
        return indoorDao.queryFloor();
    }

    @Override
    public List<BusIndoor> queryDoor(int floor) {
        return indoorDao.queryDoor(floor);
    }
}
