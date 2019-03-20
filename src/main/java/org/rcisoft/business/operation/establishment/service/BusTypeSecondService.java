package org.rcisoft.business.operation.establishment.service;

import org.rcisoft.entity.BusTypeSecond;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
public interface BusTypeSecondService {


    /**
     *  查出属于某个一级设备类型的二级设备类型
     * @param busTypeSecond
     * @return
     */
    List<BusTypeSecond> listBusTypeSecondByFirstId(BusTypeSecond busTypeSecond);


}
