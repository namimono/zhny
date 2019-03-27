package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.entity.BusDevice;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
public interface BusDeviceService {

    /**
     *  根据一级设备类型，二级设备类型找出设备
     * @param busDevice
     * @return
     */
    List<BusDevice> listBusDevice(BusDevice busDevice);
}
