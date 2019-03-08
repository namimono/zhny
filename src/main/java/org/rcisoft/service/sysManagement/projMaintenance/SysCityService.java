package org.rcisoft.service.sysManagement.projMaintenance;

import org.rcisoft.entity.SysCity;

import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:38 2019/3/8
 */
public interface SysCityService {
    /**
     * 根据城市名称查询城市信息
     * @param name
     * @return
     */
    Map<String,Object> queryCityByName(String city);
}
