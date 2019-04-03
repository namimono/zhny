package org.rcisoft.business.monitor.intercept.service;

import org.rcisoft.business.monitor.intercept.entity.OutsideAndInsideTemp;
import org.rcisoft.entity.BusIndoor;
import org.rcisoft.entity.BusIndoorParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:47 2019/3/29
 */
public interface IndoorService {
    List<String> queryFloor();
    List<BusIndoor> queryDoor(int floor);
    OutsideAndInsideTemp queryIndoorParam(String indoorId, String ProId,String coding);
    Map<String,Object> queryJsonIndoor(String proId, int type, String coding,String indoor);
}
