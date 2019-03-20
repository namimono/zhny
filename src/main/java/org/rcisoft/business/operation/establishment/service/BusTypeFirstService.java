package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.entity.BusTypeFirst;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
public interface BusTypeFirstService {

    /**
     *  查出所有以及设备类型
     * @return
     */
    List<BusTypeFirst> listBusTypeFirst();
}
