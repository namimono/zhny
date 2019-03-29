package org.rcisoft.business.monitor.intercept.service;

import org.rcisoft.entity.BusIndoor;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:47 2019/3/29
 */
public interface IndoorService {
    List<String> queryFloor();
    List<BusIndoor> queryDoor(int floor);
}
