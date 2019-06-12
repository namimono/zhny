package org.rcisoft.business.monitor.intercept.service;

import org.rcisoft.business.monitor.intercept.entity.OutsideAndInsideTemp;
import org.rcisoft.entity.BusIndoor;

import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 15:47 2019/3/29
 */
public interface IndoorService {
    /**
     * 查询项目的楼层
     *
     * @param projectId
     * @return List<String>
     */
    List<BusIndoor> queryFloor(String projectId);

    /**
     * 查询项目的房间
     *
     * @param projectId
     * @param floor
     * @return List<BusIndoor>
     */
    List<BusIndoor> queryDoor(String projectId, int floor);

    OutsideAndInsideTemp queryIndoorParam(String indoorId, String ProId, String coding);

    Map<String, Object> queryJsonIndoor(String proId, int type, String coding, String indoor);

    /**
     * 查询某个月份每一天的环境参数
     * @param proId
     * @param type
     * @param coding
     * @param year
     * @param month
     * @param indoorId
     * @return Map<String, Object>
     */
    Map<String, Object> MonthParamContrast(String proId, int type, String coding, int year, int month, String indoorId);
}
