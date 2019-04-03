package org.rcisoft.business.common.service;

import org.rcisoft.entity.*;

import java.util.List;

/**
 * Created by JiChao on 2019/3/26.
 * 公共接口
 */
public interface CommonService {

    /**
     * 根据项目id，查询当前项目下所有的一级设备类型
     * @param projectId
     * @return
     */
    List<BusTypeFirst> queryTypeFirst(String projectId);

    /**
     * 查询项目拥有的系统类型
     * @param projectId
     * @return
     */
    List<SysSystem> querySystemForProject(String projectId);

    /**
     * 查询项目下所有设备
     * @param projectId
     * @return
     */
    List<BusDevice> queryDevices(String projectId, String typeFirstId);

    /**
     * 查询项目所有的一级参数
     * @param projectId
     * @return
     */
    List<BusParamFirst> queryParamFirsts(String projectId);

    /**
     * 查询一级参数的二级参数
     * @param paramFirstId
     * @return
     */
    List<BusParamSecond> queryParamSeconds(String paramFirstId);

}
