package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.entity.BusDeviceType;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/17
 */
public interface BusDeviceTypeService {

    /**
     *  查出设备类型
     * @return List<BusDeviceType>
     */
    List<BusDeviceType> listBusDeviceType();
}
